package edu.bcm.dldcc.big.nursa.util.restapi.nursa;

import java.util.List;

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
import edu.bcm.dldcc.big.nursa.model.common.Journal_;

/**
 * @author mcowiti
 */
@Stateless
@Path("/pubmed/journals")
public class JournalEndpoint
{
	@PersistenceContext(unitName = "NURSA")
	private EntityManager em;
   
   
   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") String id)
   {
	   return this.getJournalById(id);
   }
   
   @GET
   @Path("/jsonp/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findByIdAsJsonp(@PathParam("id") String id)
   {
      return this.getJournalById(id);
   }
   
   @GET
   @Produces("application/json")
   public List<Journal> listAll(@QueryParam("start") Integer startPosition, @DefaultValue ("25") @QueryParam("max") Integer maxResult)
   {
	   if(maxResult > 100)
		   maxResult=100;
	  return this.getAllJournals(startPosition, maxResult);
   }
   
   @GET
   @Produces("application/json")
   @Path("/jsonp/")
   public List<Journal> listAllAsJsonp(@QueryParam("start") Integer startPosition, @DefaultValue ("25") @QueryParam("max") Integer maxResult)
   {
	   if(maxResult > 100)
		   maxResult=100;
	  return this.getAllJournals(startPosition, maxResult);
   }
   
   private Response getJournalById(String issn){
	   Journal entity=getJournal(issn);
	      
	      if (entity == null)
	      {
	         return Response.status(Status.NOT_FOUND).build();
	      }
	      return Response.ok(entity).build();
   }
   
   private Journal getJournal(String issn){
	   CriteriaBuilder cb = em.getCriteriaBuilder();
     	CriteriaQuery<Journal> cq = cb.createQuery(Journal.class);
		Root<Journal> journal = cq.from(Journal.class);
		cq.where(cb.equal(journal.get(Journal_.issn),issn)); 
		cq.select(journal);
		return (Journal)em.createQuery(cq).getSingleResult();
   }
   
   private List<Journal> getAllJournals(Integer start,Integer end){
	   CriteriaBuilder cb = em.getCriteriaBuilder();
     	CriteriaQuery<Journal> cq = cb.createQuery(Journal.class);
		Root<Journal> journal = cq.from(Journal.class);
		cq.select(journal);
		return (List<Journal>)em.createQuery(cq).setFirstResult(start).setMaxResults(end).getResultList();
   }
   
   private List<Journal> getAllJournalsByJPQL(Integer start,Integer end){
	   
	   TypedQuery<Journal> findAllQuery = em.createQuery("SELECT DISTINCT j FROM Journal j ORDER BY j.issn", Journal.class);
	      if (start != null)
	      {
	         findAllQuery.setFirstResult(start);
	      }
	      if (end != null)
	      {
	         findAllQuery.setMaxResults(end);
	      }
	      final List<Journal> results = findAllQuery.getResultList();
	      return results;
   }
   
   private Journal getJournalByJPQL(String issn){
	   
	   TypedQuery<Journal> findByIdQuery = em.createQuery("SELECT DISTINCT j FROM Journal j WHERE j.issn = :entityId ORDER BY j.issn", Journal.class);
	      findByIdQuery.setParameter("entityId", issn);
	      Journal entity;
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

   

}