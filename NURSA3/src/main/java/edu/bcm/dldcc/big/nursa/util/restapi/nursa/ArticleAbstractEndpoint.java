package edu.bcm.dldcc.big.nursa.util.restapi.nursa;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import edu.bcm.dldcc.big.nursa.model.common.Article;
import edu.bcm.dldcc.big.nursa.model.common.Article_;
import edu.bcm.dldcc.big.nursa.model.common.Journal;
import edu.bcm.dldcc.big.nursa.util.restapi.client.NcbiRestMaxConnectionsException;
import edu.bcm.dldcc.big.nursa.util.restapi.client.PubmedAbstractBean;
import edu.bcm.dldcc.big.nursa.util.restapi.client.PubmedArticleBean;


/**
 * Abstract may only be downloaded via XML extraction, 
 * since the JAXB Objects generated from XML schema, are unable to automatically
 * render an  object named Abstract from the NCBI Xml (ie Abstract/AbstractText)
 * @author mcowiti
 */
@Stateless
@Path("/pubmed/abstract")
public class ArticleAbstractEndpoint extends BaseEndPoint
{
	private static Logger log = Logger.getLogger("edu.bcm.dldcc.big.nursa.util.restapi.nursa.ArticleAbstractEndpoint");
	
	@PersistenceContext(unitName = "NURSA")
	private EntityManager em;
   
	@Inject 
	private PubmedAbstractBean pubmedAbstractBean;
	
	@Inject
	private PubmedArticleBean pubmedArticleBean;
	
  
   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") String id)
   {
      return getAbstractById(id);
   }
   
   @GET
   @Path("/jsonp/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findByIdAsJsonp(@PathParam("id") String id)
   {
      return getAbstractById(id);
   }
   
   private Response getAbstractById(String id){
	   
	   if(!isNumericPubmed(id)){
		   return Response.status(Status.BAD_REQUEST).build();
	   }
	   
	   Article entity=findArticle(id);
	   
	      
	      if (entity == null)
	      {
	    	  entity=downloadArticleForAbstract(id);
	    	  if(entity == null)
	    		  return Response.status(Status.NOT_FOUND).build();
	      }
	      
	      if(entity.getAbstractBlurb() != null && !entity.getAbstractBlurb().equals(""))
	      {
	    	  return Response.ok(entity).build();
	      }
	      
	      String abstractText=null;
			try {
				abstractText = pubmedAbstractBean.getAbstractText(id);
			} catch (NcbiRestMaxConnectionsException e) {
				log.log(Level.WARNING,"Reached limit for  NCBI calls, sending client status of: "+Status.SERVICE_UNAVAILABLE);
				return Response.status(Status.NOT_FOUND).build();
			}
			
	      if(abstractText != null && !abstractText.trim().equals(""))
	      {
	    	  entity.setAbstractBlurb(abstractText);
	    	  em.merge(entity);
	      }
	      
	      return Response.ok(entity).build();
   }
   
   private Article findArticle(String id)
   {
	   Article entity;
	      try
	      {
	          CriteriaBuilder cb = em.getCriteriaBuilder();
		      	CriteriaQuery<Article> cq = cb.createQuery(Article.class);
				Root<Article> article = cq.from(Article.class);
				cq.where(cb.equal(article.get(Article_.pubmedId),id)); 
				cq.select(article);
				entity = em.createQuery(cq).getSingleResult();
	      }
	      catch (NoResultException nre)
	      {
	         entity = null;
	      }
	      return entity;
   }
   
   private Article findByJPQL(String id){
	   TypedQuery<Article> findByIdQuery = em.createQuery("SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.journal WHERE a.pubmedId = :entityId ORDER BY a.pubmedId", Article.class);
	      findByIdQuery.setParameter("entityId", id);
	      Article entity;
	      try
	      {
	         entity = findByIdQuery.getSingleResult();
	      }
	      catch (NoResultException nre)
	      {
	         entity = null;
	      }
	      return entity;
   }
   
   private Article downloadArticleForAbstract(String id){
	 
	  log.log(Level.FINE,"Seeking abstract,but the Article "+id+ " was not found in Cache, will download article  ...");
 	  boolean cacheArticle=true;
 	  boolean useJAXRS2Client=false;
 	  boolean downloadAbstract=true; //only useful for xml extraction
 	 Article entity=null;
 	  try {
 		    entity=pubmedArticleBean.getArticle(id,useJAXRS2Client,downloadAbstract);
 		   if(cacheArticle)
 			   persistArticle(entity);
 		   
		} catch (NcbiRestMaxConnectionsException e) {
			log.log(Level.WARNING,"Reached limit for  NCBI calls, sending client status of: "+Status.SERVICE_UNAVAILABLE);
			return null;
		}
 	  return entity;
   }
   
   private void persistArticle(Article entity){
	   Journal journal=em.find(Journal.class, entity.getJournal().getIssn());
	   if(journal == null){
		   em.persist(entity.getJournal());
	   }else{
		   entity.setJournal(journal);
	   }
	   em.persist(entity);
   }
   
}