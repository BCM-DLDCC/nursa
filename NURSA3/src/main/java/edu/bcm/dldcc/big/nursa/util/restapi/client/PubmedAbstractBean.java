package edu.bcm.dldcc.big.nursa.util.restapi.client;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import edu.bcm.dldcc.big.nursa.model.common.Reference;

/**
 * SLSB  to retrieve  pubmed abstracts
 * The pubmed Abstract is either cached or pulled from NCBI
 * @author mcowiti
 *
 */

@Stateless
@Named("pubmedAbstractBean")
@LocalBean
public class PubmedAbstractBean {

	@Inject private NcbiApiCallsTractorBean ncbiApiCallsTractorBean;
	
	@Inject 
	private EntityManager objectEntityManager;
	 
	private static Logger log = Logger.getLogger("edu.bcm.dldcc.big.nursa.util.restapi.client.PubmedAbstractBean");
	 
	//move these to database config
	private String pubmedRestUrl="http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&retmode=xml&email=support@nursa.org&id=";
	private final static String UTF_ENCODING="UTF-8";
	private final static String REQ_ACCEPT_HEADER="application/xml";
	private final static String ABSTRACT_TEXT ="/PubmedArticleSet/PubmedArticle/MedlineCitation/Article/Abstract/AbstractText";
	private final static String ABSTRACT_LABEL="Label";
	
	private String abstractText;
	
	
	public void prepareAbstract(Reference reference){
		this.abstractText=this.getPubmedAbstract(reference);
	}
	
	/**
	 * 
	 * Look for pubmed  abstract.
	 * Call Utilized by Nursa3 pubmed UI
	 * First check cached. If none, call ncbi API and cache results in persistence
	 * @param reference
	 * @return
	 */
	public String getPubmedAbstract(Reference reference ){
		
		
		if(reference == null || reference.getArticle() == null){
			log.log(Level.INFO,"@getPubmedAbstract null reference object"); 
			return null;
		}
		
		if(reference.getArticle().getAbstractBlurb() != null){
			return reference.getArticle().getAbstractBlurb();
		}
		
		String abstratctText=null;
		try {
			abstratctText=getAbstractText(reference.getPubmedId());
		} catch (NcbiRestMaxConnectionsException e) {
			log.log(Level.WARNING,"The abstract service is currently busy for paper: "+reference.getPubmedId());
			return "The abstract service is currently busy. Please try again shortly. Thank you for your patience.";
		}
		
		if(abstratctText == null || abstratctText.trim().equals("")){
			log.log(Level.WARNING,"No abstract available  for paper: "+reference.getPubmedId());
			return "No Abstract is available at this time for the pubmed "+reference.getPubmedId();
		}
		
		// cache
		reference.getArticle().setAbstractBlurb(abstratctText);
		try{
			objectEntityManager.merge(reference);
		}catch (Exception e){
			log.log(Level.SEVERE,"Failed to persist abstract for paper: "+reference.getPubmedId());
		}
		
		return abstratctText;
	}
	
	/**
	 * Get Abstract Text
	 * Call Utilized by Transcriptomine though REST WS call
	 * @param pmid
	 * @return
	 * @throws NcbiRestMaxConnectionsException
	 */
	public String getAbstractText(String pmid) 
			throws NcbiRestMaxConnectionsException{
		
		String abstractText=null;
		
		try {
			
			if(!ncbiApiCallsTractorBean.mayMakeNewNCPIAPICall())
			{
				log.warning("The Abstract Service is currently busy. Please try again in a couple of  seconds. Thank you for your patience. "+pmid);
				throw new NcbiRestMaxConnectionsException("The Abstract Service is currently busy. Please try again in a couple of  seconds. Thank you for your patience. "+pmid);
				// FUTURE need schedule/use event for time sychronous receive . UI wait max total 8 seconds fro data
			}
			
			abstractText = getAbstractFromNcbi(pmid);
			ncbiApiCallsTractorBean.setCount(ncbiApiCallsTractorBean.getCount()-1);
			
		} catch (Exception e) {
			log.warning("There was a problem locating this Abstract. No Abstract is available at this time for pubmed "+pmid);
			return null;
		}
		
		return abstractText;
	}
	
	
	
	
	/**
	 * Call NCBI API via #HttpURLConnection
	 * Check that at the instance of makign call, there are no other calls out there. If there are, wait
	 * @param pubmed
	 * @return
	 * @throws Exception
	 */
	private String getAbstractFromNcbi(String pubmed) throws Exception{
		
		String query = String.format("%s", URLEncoder.encode(pubmed, UTF_ENCODING));
		
		long b=System.currentTimeMillis();
		HttpURLConnection conn =null;
		
		try{
			
			conn = (HttpURLConnection) (new URL(pubmedRestUrl + query)).openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept",REQ_ACCEPT_HEADER );
	 
			if (conn.getResponseCode() != 200) 
			{
				log.log(Level.SEVERE, "Failed : HTTP error code : "+ conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
	 
			InputStream xml=conn.getInputStream();
			
			
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expression = ABSTRACT_TEXT;
			InputSource inputSource = new InputSource(xml);
			
			
			Node node=null;
			Element element=null;
			NodeList nodeList=null;
			StringBuilder sb= new StringBuilder();
			nodeList=(NodeList) xpath.evaluate(expression, inputSource, XPathConstants.NODESET);
			if(nodeList != null && nodeList.getLength() > 0)
			{
				for(int i = 0; i < nodeList.getLength(); i++) 
				{
					node=nodeList.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE) 
					{
						element=((Element)node);
						if(element.hasAttributes())
						{
							sb.append(element.getAttribute(ABSTRACT_LABEL)).append(" : ");
						}
						sb.append(element.getTextContent()).append("\n\n");
					}
				}
			}
			
			conn.disconnect();
			log.info("NCBI abstract REST API call time(ms)="+(System.currentTimeMillis()-b));
			
			return sb.toString();
		}finally{
			//Wanna restrict # of connections: what if finally called after another call went in?
			//conn.disconnect();
		}
	}
	
	
	
	public String initialCitation(Reference reference){
		StringBuilder sb= new StringBuilder();
		
		if(reference == null)
			return null;
		
		if(reference.getArticle() == null || reference.getArticle().getAuthorsList() == null)
			return null;
		
		String authors=getCitationAuthors(reference.getArticle().getAuthorsList());
		
		sb.append(authors).append(" ").append("(").append(reference.getArticle().getPublishYear()).append(")").append(" ")
		   .append(reference.getArticle().getArticleTitle()).append(" ").append(reference.getArticle().getJournal().getISOAbbreviation()).append(" ");
		return sb.toString();
	}
	
	public String getCitationAuthors(String authorsList){
		   StringBuilder sb= new StringBuilder();
		   String[] auths=authorsList.split(",");
		   
		   boolean etAled=false;
		   String authors=null;
		   int count=00;
			  for(String author:auths){
				  if(++count < 7)
				  {
					  sb.append(author).append(", ");
				  }else{
					  sb.append(" ").append("et al.");
					  etAled=true;
					  break;
				  }
			  }
			  
			  authors=sb.toString();
			  if(!etAled){
				  authors=authors.substring(0, authors.length()-1);
			  }
			  return sb.toString();
		 }

	public String getAbstractText() {
		return abstractText;
	}
	
}
