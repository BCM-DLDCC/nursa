package edu.bcm.dldcc.big.nursa.util.restapi.client;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import edu.bcm.dldcc.big.nursa.model.common.Article;
import edu.bcm.dldcc.big.nursa.model.common.Journal;
import edu.bcm.dldcc.big.nursa.util.restapi.client.jaxb.PubmedArticleSet;
import edu.bcm.dldcc.big.nursa.util.restapi.client.jaxb.PubmedArticleSet.PubmedArticle;
import edu.bcm.dldcc.big.nursa.util.restapi.client.jaxb.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.AuthorList.Author;


/**
 * Download Transcriptomine (non NURSA3) articles
 * @author mcowiti
 *
 */

@Stateless
@Named
@LocalBean
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PubmedArticleBean {

	private static Logger log = Logger.getLogger("edu.bcm.dldcc.big.nursa.util.restapi.client.PubmedArticleBean");
	
	@Inject 
	private NcbiApiCallsTractorBean ncbiApiCallsTractorBean;
	
	private String pubmedUrl="http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&retmode=xml&email=support@nursa.org&id=";
	private final static int AUTHORS_MAX_LEN=1000;
	private static final int ET_AL_AT_BEFORE_SEVEN=7;
	
	private final static String UTF_ENCODING="UTF-8";
	private final static String REQ_ACCEPT_HEADER="application/xml";
	private final static String ARTICLE_NODE ="/PubmedArticleSet/PubmedArticle/MedlineCitation/Article";
	private XPath xpath =XPathFactory.newInstance().newXPath();
	
	
	
	
	public Article getArticle(String pmid,boolean useJAXRS2,boolean downloadAbstract) 
			throws NcbiRestMaxConnectionsException{
		
		Article article=null;
		try {
			
			if(!ncbiApiCallsTractorBean.mayMakeNewNCPIAPICall())
			{
				log.warning("The Abstract Service is currently busy. Please try again in a couple of  seconds. Thank you for your patience. "+pmid);
				throw new NcbiRestMaxConnectionsException("The Abstract Service is currently busy. Please try again in a couple of  seconds. Thank you for your patience. "+pmid);
				// FUTURE IMPROVEMENT need schedule/use event for time sychronous receive . UI wait max total 8 seconds fro data
			}
			
			article = (useJAXRS2)?
						downloadPubmedArticleFromEutilsByJAXRSClient(pmid):
						downloadPubmedArticleFromEutilsByXmlExtraction(pmid,downloadAbstract);
			
			ncbiApiCallsTractorBean.setCount(ncbiApiCallsTractorBean.getCount()-1);
			
		} catch (Exception e) {
			log.warning("There was a problem locating this Article. No Article is available at this time for pubmed =:"+pmid+" problem is "+e.getMessage());
			e.printStackTrace();
			return null;
		}
		
		return article;
	}
	
	
	
	private Article downloadPubmedArticleFromEutilsByJAXRSClient(String pmid)
	{
			if(pmid == null){
		   		return null;
		   	}
		   	
			   PubmedArticleSet pubmedArticleSet= callEutilsPubmedREST(pmid);//callEUtilsPubmedRESTByRestEasyClient(pmid);
			   
			   long beg=System.currentTimeMillis();
			  
			   if(pubmedArticleSet == null){
				   log.warning("# NO NCBI pubmed articles found=");
				   return null;
			   }
			   
			   edu.bcm.dldcc.big.nursa.util.restapi.client.jaxb.PubmedArticleSet.PubmedArticle.MedlineCitation.Article ncbiArticle;
			  String pubmedId; 
			  Article nursaArticle=null;
			  String authorsList=null;
			  for(PubmedArticle article:pubmedArticleSet.getPubmedArticle())
			  {
				  pubmedId=Integer.toString(article.getMedlineCitation().getPMID().getValue().intValue());
				  ncbiArticle=article.getMedlineCitation().getArticle();
				  
				  if(ncbiArticle == null){
					  log.log(Level.SEVERE,"***** Missing MedlineCitation article for pubmed "+pubmedId);
					  continue;
				  }
				  Journal journal=getJournal(ncbiArticle);
				  if(journal == null){
					  log.log(Level.SEVERE,"***** Missing MedlineCitation article Journal info for pubmed= "+pubmedId);
					  continue;
				  }
				  
				  if(ncbiArticle.getAuthorList() == null || ncbiArticle.getAuthorList().getAuthor() == null){
					  log.log(Level.SEVERE,"***** Missing Authors  info for pubmed= "+pubmedId);
					  continue;
				  }
				  
				  nursaArticle=getNursaPubmedArticle(ncbiArticle);
				  authorsList=getAuthors(ncbiArticle);
				  
				  setupArticleAuthors(nursaArticle,authorsList);

				  nursaArticle.setPubmedId(pubmedId);
				  nursaArticle.setJournal(journal);
				 
			}
			  log.info("Total Article processing/update time ms="+(System.currentTimeMillis()-beg));
			  return nursaArticle;
		}
	
	
	
	/**
	 * Call NCBI Rest via HttpURLConnection
	 * Extraction of Article from XML
	 * @param pubmed
	 * @param cacheArticle
	 * @return Article
	 */
	private Article downloadPubmedArticleFromEutilsByXmlExtraction(String pubmed,boolean downloadAbstract)
			throws Exception{
		
		
		String query = String.format("%s", URLEncoder.encode(pubmed, UTF_ENCODING));
		
		long b=System.currentTimeMillis();
		HttpURLConnection conn =null;
		
		
		try{
			conn = (HttpURLConnection) (new URL(pubmedUrl + query)).openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept",REQ_ACCEPT_HEADER );
	 
			if (conn.getResponseCode() != 200) 
			{
				log.log(Level.SEVERE, "Failed : HTTP error code : "+ conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
	 
			InputStream xml=conn.getInputStream();
			
			String expression = ARTICLE_NODE;
			InputSource inputSource = new InputSource(xml);
			
			Node root = (Node) xpath.evaluate("/", inputSource, XPathConstants.NODE);
			
			Node articleNode=null;
			articleNode=(Node) xpath.evaluate(expression, root, XPathConstants.NODE);
			if(articleNode == null)
			{ 
				log.log(Level.SEVERE,"***** Missing MedlineCitation article for pubmed "+pubmed);
				conn.disconnect();
				return null;
			}
			
			Article article=extractArticle(root);
			article.setPubmedId(pubmed);
			
			Journal journal=extractJournal(root,article);
			if(journal == null)
			{
				conn.disconnect();
				 log.log(Level.SEVERE,"***** Missing MedlineCitation article Journal info for pubmed= "+pubmed);
				return null;
			}
			
			List<Author> authors=extractAurthorsList(root);
			if(authors ==null){
				conn.disconnect();
				log.log(Level.SEVERE,"***** Missing Authors  info for pubmed= "+pubmed);
				return null;
			}
			
			setupArticleAuthors(article,getAuthorsString(authors));
			article.setJournal(journal);
			
			if(downloadAbstract)
			{
				article.setAbstractBlurb(extractAbstract(root));
			}
			conn.disconnect();
			log.info("NCBI Article REST API call time(ms)="+(System.currentTimeMillis()-b));
			
			return article;
			
		}finally{
			try {
				conn.disconnect();
			} catch (Exception e) {
				
			}
		}
	}
	
	private void setupArticleAuthors(Article article,String authorsList){
		String authorsCite=getCitationAuthors(authorsList);
		article.setAuthorsList(authorsList);
		article.setAuthorsCited(authorsCite);
	}
	
	
	private Article  extractArticle(Node inputSource) 
			throws XPathExpressionException{
		
		String titleExpression="/PubmedArticleSet/PubmedArticle/MedlineCitation/Article/ArticleTitle";
		String pageExpression="/PubmedArticleSet/PubmedArticle/MedlineCitation/Article/Pagination/MedlinePgn";
		
		Article article= new Article();
		
		Node node=(Node) xpath.evaluate(titleExpression, inputSource, XPathConstants.NODE);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			article.setArticleTitle(((Element)node).getTextContent());
		}
		node=(Node) xpath.evaluate(pageExpression, inputSource, XPathConstants.NODE);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			article.setPagination(((Element)node).getTextContent());
		}
		return article;
	}
	
	private List<Author>  extractAurthorsList(Node inputSource) 
			throws XPathExpressionException{
		
		String authorExpression="/PubmedArticleSet/PubmedArticle/MedlineCitation/Article/AuthorList/Author";
		
		NodeList nodeList=(NodeList) xpath.evaluate(authorExpression, inputSource, XPathConstants.NODESET);
		if(nodeList == null || nodeList.getLength() == 0)
			return null;
		
		Node node=null;
		Element element=null;
		List<Author> authors= new ArrayList<Author>();
		Author author=null;
		
		for(int i = 0; i < nodeList.getLength(); i++) 
		{
			node=nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) 
			{
				element=((Element)node);
				 author= new Author();
				 author.setLastName(((Element)element.getElementsByTagName("LastName").item(0)).getTextContent());
				 author.setInitials(((Element)element.getElementsByTagName("Initials").item(0)).getTextContent());
				 authors.add(author);
			}
		}
		
		return authors;
	}
	
	
	
	/**
	 * Extract Journal information
	 * @param articleNode
	 * @param xpath
	 * @param inputSource
	 * @param article
	 * @return
	 * @throws XPathExpressionException
	 */
	private Journal  extractJournal(Node inputSource, Article article) 
			throws XPathExpressionException{
		Journal journal= new Journal();
	
		String issnExpression="/PubmedArticleSet/PubmedArticle/MedlineCitation/Article/Journal/ISSN";
		String volExpression="/PubmedArticleSet/PubmedArticle/MedlineCitation/Article/Journal/JournalIssue/Volume";
		String issueExpression="/PubmedArticleSet/PubmedArticle/MedlineCitation/Article/Journal/JournalIssue/Issue";
		String pubYearExpression="/PubmedArticleSet/PubmedArticle/MedlineCitation/Article/Journal/JournalIssue/PubDate/Year";
		
		String titleExpression="/PubmedArticleSet/PubmedArticle/MedlineCitation/Article/Journal/Title";
		String isoExpression="/PubmedArticleSet/PubmedArticle/MedlineCitation/Article/Journal/ISOAbbreviation";
		
		Node node=(Node) xpath.evaluate(issnExpression, inputSource, XPathConstants.NODE);
		if(node == null)
		{
			log.log(Level.SEVERE,"No ISSN node ");
			return null;
		}
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			journal.setIssn(((Element)node).getTextContent());
		}
		
		node=(Node) xpath.evaluate(titleExpression, inputSource, XPathConstants.NODE);
		if(node.getNodeType() == Node.ELEMENT_NODE) 
		{
			journal.setTitle(((Element)node).getTextContent());
		}
		
		node=(Node) xpath.evaluate(isoExpression, inputSource, XPathConstants.NODE);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			journal.setISOAbbreviation(((Element)node).getTextContent());
		}
		
		node=(Node) xpath.evaluate(volExpression, inputSource, XPathConstants.NODE);
		if(node!= null)
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				article.setVolume(((Element)node).getTextContent());
			}
		
		node=(Node) xpath.evaluate(issueExpression, inputSource, XPathConstants.NODE);
		if(node!=null)
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				article.setIssue(((Element)node).getTextContent());
			}
		
		node=(Node) xpath.evaluate(pubYearExpression, inputSource, XPathConstants.NODE);
		if(node!=null)
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				article.setPublishYear(((Element)node).getTextContent());
			}
		return journal;
	}
	 
	private String  extractAbstract(Node root) 
			throws XPathExpressionException{
		
		String expression="/PubmedArticleSet/PubmedArticle/MedlineCitation/Article/Abstract/AbstractText";
		String ABSTRACT_LABEL="Label";
		
		Node node=null;
		Element element=null;
		NodeList nodeList=null;
		StringBuilder sb= new StringBuilder();
		nodeList=(NodeList) xpath.evaluate(expression, root, XPathConstants.NODESET);
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
		return sb.toString();
	}
	
	/**
	 * Use JAX-RS v2 Client framework
	 * @param pmid
	 * @return
	 */
	private PubmedArticleSet callEutilsPubmedREST(String pmid){
		  
		//set GET with params
		PubmedArticleSet pubmedArticleSet=null;
		try{ 
			Client client = ClientBuilder.newClient();
		    pubmedArticleSet = client.target(pubmedUrl+pmid)
		           .request(MediaType.TEXT_XML)
		           .get(PubmedArticleSet.class);
		   
		}catch (Exception e){
			 log.log(Level.SEVERE,"Error getting NCBI article= "+e.getMessage());
		}
		 return pubmedArticleSet;
	}
	
	
	private String getAuthors(edu.bcm.dldcc.big.nursa.util.restapi.client.jaxb.PubmedArticleSet.PubmedArticle.MedlineCitation.Article art){
		   
		   if(art.getAuthorList() == null || art.getAuthorList().getAuthor() == null)
		   {
			   return null;
		   }
		   
		   List<Author> auths=art.getAuthorList().getAuthor();
		   return getAuthorsString(auths);
	}
	
	private String getAuthorsString(List<Author> auths){
		StringBuilder sb= new StringBuilder();
		   String authors;
		   
		for(Author author:auths){
				  sb.append(author.getLastName()).append(" ").append(author.getInitials()).append(",");
		   }
			  
		   authors=sb.toString().substring(0, sb.toString().length()-1);
		   String lastly;
		   if(authors.length() > AUTHORS_MAX_LEN){
			  lastly=authors.substring(0,authors.lastIndexOf(",", AUTHORS_MAX_LEN-7));
			  authors=new StringBuilder(lastly).append(" ").append("et al.").toString();
		   }
		  
		   return authors;
	}
	
	private Article getNursaPubmedArticle(edu.bcm.dldcc.big.nursa.util.restapi.client.jaxb.PubmedArticleSet.PubmedArticle.MedlineCitation.Article art){
		   
		edu.bcm.dldcc.big.nursa.util.restapi.client.jaxb.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.Journal journal=art.getJournal();
		   Article pubmed= new Article();
		   
		   pubmed.setArticleTitle(art.getArticleTitle());
		   pubmed.setPublishYear(Short.toString(journal.getJournalIssue().getPubDate().getYear()));
		   pubmed.setPagination(art.getPagination().getMedlinePgn());
		   
		   if(journal.getJournalIssue().getVolume() != null || !journal.getJournalIssue().getVolume().trim().equals(""))
			   pubmed.setVolume(journal.getJournalIssue().getVolume());
		   return pubmed;
	}
	
	private Journal getJournal(edu.bcm.dldcc.big.nursa.util.restapi.client.jaxb.PubmedArticleSet.PubmedArticle.MedlineCitation.Article art){
		   Journal journal= new Journal();
		   if(art.getJournal() == null || art.getJournal().getISSN() == null)
		   {
			   return null;
		   }
		   journal.setIssn(art.getJournal().getISSN().getValue());
		   journal.setTitle(art.getJournal().getTitle());
		   journal.setISOAbbreviation(art.getJournal().getISOAbbreviation());
		   return journal;
		}

	/**
	    * Implement (Nursa) citation Authors standard
	    * @param authorsList
	    * @return
	    */
	   public String getCitationAuthors(String authorsList){
		   StringBuilder sb= new StringBuilder();
		   String[] auths=authorsList.split(",");
		   
		   boolean etAled=false;
		   String authors=null;
		   int count=00;
			  for(String author:auths)
			  {
				  if(++count < ET_AL_AT_BEFORE_SEVEN)
				  {
					  sb.append(author).append(",");
				  }else
				  {
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
	        
}
