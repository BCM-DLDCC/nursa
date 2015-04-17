package edu.bcm.dldcc.big.nursa.util.restapi.nursa;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.bcm.dldcc.big.nursa.model.common.Article;
import edu.bcm.dldcc.big.nursa.model.common.Article_;
import edu.bcm.dldcc.big.nursa.model.common.Journal;
import edu.bcm.dldcc.big.nursa.util.restapi.client.NcbiRestMaxConnectionsException;
import edu.bcm.dldcc.big.nursa.util.restapi.client.PubmedArticleBean;


/**
 * Returns NURSA3 and Transcriptomine articles
 * Searches for cached articles before attempt to Call NCBI pubmed REST API
 * Articles may be obtained from NCBI REST API via manual XML extractions or (if Server  supports for JAX-RS v2), via JAX-RS client framework
 * @author mcowiti
 */
@Stateless
@Path("/pubmed/articles")
public class ArticleEndpoint extends BaseEndPoint
{
	private static Logger log = Logger.getLogger("edu.bcm.dldcc.big.nursa.util.restapi.nursa.ArticleEndpoint");
	
	@PersistenceContext(unitName = "NURSA")
	private EntityManager em;
   
	@Inject
	private PubmedArticleBean pubmedArticleBean;
	
  
   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") String id)
   {
      return getArticleById(id);
   }
   
   @GET
   @Path("/jsonp/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findByIdAsJsonp(@PathParam("id") String id)
   {
      return getArticleById(id);
   }
   
   private Response getArticleById(String id){
	   
	   if(!isNumericPubmed(id)){
		   return Response.status(Status.BAD_REQUEST).build();
	   }
	      
	   Article entity=findArticle(id);
	   
	      if(entity == null)
	      {
	    	  boolean cacheArticle=true;
	    	  boolean useJAXRS2Client=false;
	    	  boolean downloadAbstract=false; //Only utilized for xml extraction mode
	    	  try {
	    		   entity=pubmedArticleBean.getArticle(id,useJAXRS2Client,downloadAbstract);
	    		   if(entity == null)
	    		   {
	    			   log.log(Level.WARNING,"No Article data found for pubmed: "+id);
						return Response.status(Status.NOT_FOUND).build();
	    		   }
	    			   
	    		   if(cacheArticle){
	    			   persistArticle(entity);
	    			   log.log(Level.FINE, "Article persisted");
	    		   }
	    		   
				} catch (NcbiRestMaxConnectionsException e) {
					log.log(Level.WARNING,"Reached limit for  NCBI calls, sending client status of: "+Status.SERVICE_UNAVAILABLE);
					return Response.status(Status.NOT_FOUND).build();
				}
	      }
	      
	      entity.setAuthorsCited(pubmedArticleBean.getCitationAuthors(entity.getAuthorsList()));
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
   
   private void persistArticle(Article pubmed){
	   Journal journal=em.find(Journal.class, pubmed.getJournal().getIssn());
	   if(journal == null)
	   {
		   em.persist(pubmed.getJournal());
	   }else{
		   pubmed.setJournal(journal);
	   }
	   em.persist(pubmed);
   }
   
}