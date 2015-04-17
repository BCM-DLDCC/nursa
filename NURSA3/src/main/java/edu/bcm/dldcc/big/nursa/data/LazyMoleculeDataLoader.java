package edu.bcm.dldcc.big.nursa.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.omnifaces.util.Ajax;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import edu.bcm.dldcc.big.nursa.model.Molecule;
import edu.bcm.dldcc.big.nursa.model.common.TranslationalAutoSuggest;
import edu.bcm.dldcc.big.nursa.model.core.GOTerm;
import edu.bcm.dldcc.big.nursa.model.search.MoleculeResult;
import edu.bcm.dldcc.big.nursa.model.search.MoleculeResult_;
import edu.bcm.dldcc.big.nursa.model.search.MoleculeSearch;

public class LazyMoleculeDataLoader extends LazyDataModel<MoleculeResult> {

	private static final long serialVersionUID = 2205773006081141938L;

	private MoleculeSearch moleculeSearch;

	private EntityManager objectEntityManager;

	private List<SortMeta> storedSort;
	
	private Map<String, Integer> moleculeBreakdown = new HashMap<String, Integer>();

	public LazyMoleculeDataLoader() {
		super();
	}

	public LazyMoleculeDataLoader(EntityManager objectEntityManager,
			MoleculeSearch moleculeSearch) {
		super();
		this.objectEntityManager = objectEntityManager;
		this.moleculeSearch = moleculeSearch;
	}

	@Override
	public List<MoleculeResult> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		SortMeta sm = new SortMeta();
		sm.setSortField(sortField);
		sm.setSortOrder(sortOrder);
		List<SortMeta> multiSortMeta = new ArrayList<SortMeta>();
		multiSortMeta.add(sm);
		return load(first, pageSize, multiSortMeta, filters);
	}

	public List<MoleculeResult> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		
		// Setup
		CriteriaBuilder cb = objectEntityManager.getCriteriaBuilder();

		// Count Query
		
//		CriteriaQuery<Long> cqSize = cb.createQuery(Long.class);
//		Root<MoleculeResult> molRootCount = cqSize.from(MoleculeResult.class);
//		cqSize.where(createRestrictions(cb, molRootCount));
//		cqSize.select(cb.countDistinct(molRootCount));
//		this.setRowCount(objectEntityManager.createQuery(cqSize).getSingleResult().intValue());
		
		CriteriaQuery<Tuple> cqCount = cb.createTupleQuery();
		
		Root<MoleculeResult> molRootCount = cqCount.from(MoleculeResult.class);
		cqCount.multiselect(molRootCount.get(MoleculeResult_.type), cb.countDistinct(molRootCount));
		cqCount.where(createRestrictions(cb, molRootCount));
		cqCount.groupBy(molRootCount.get(MoleculeResult_.type));
		
		int total = 0;
		moleculeBreakdown = new HashMap<String, Integer>();
		for (Tuple tuple : objectEntityManager.createQuery(cqCount).getResultList()) {
			moleculeBreakdown.put((String) tuple.get(0), ((Long) tuple.get(1)).intValue());
			total = total + ((Long) tuple.get(1)).intValue();
		}
		this.setRowCount(total);
		
		// Result Page Query with order
		
		CriteriaQuery<MoleculeResult> cQuery = cb.createQuery(MoleculeResult.class);
		Root<MoleculeResult> molRoot = cQuery.from(MoleculeResult.class);
		
		// Order
		List<Order> sortOrder = new ArrayList<Order>();
		
		// Defaults to initial sort
		if ((multiSortMeta == null) && (this.storedSort == null)) {
			multiSortMeta = createInitialSort();
		} else if ((multiSortMeta == null) && (this.storedSort != null)) {
			multiSortMeta = this.storedSort;
		}
		this.storedSort = multiSortMeta;

		for (SortMeta sm : multiSortMeta) {
			Order newOrder = createOrder(sm, molRoot, cb);
			if (newOrder != null) {
				sortOrder.add(newOrder);
			}
		}

		if (sortOrder.size() > 0) {
			cQuery.orderBy(sortOrder);
		}
		
		

		cQuery.where(createRestrictions(cb, molRoot));
		cQuery.distinct(true);
		cQuery.select(molRoot);

		// Restrict returns
		TypedQuery<MoleculeResult> returnQuery = objectEntityManager.createQuery(cQuery);
		returnQuery.setFirstResult(first);
		returnQuery.setMaxResults(pageSize);
		
		/*omnifaces to set the rowcount in the molFilter since it winds up being
		 * a step behind otherwise
		 */
		String nrTotal = "0";
		String crTotal = "0";
		String ligandTotal = "0";
		
		
		
		
		if(moleculeBreakdown.containsKey("Nuclear Receptor")) {
			nrTotal = moleculeBreakdown.get("Nuclear Receptor").toString();
		}
		if(moleculeBreakdown.containsKey("Coregulator")) {
			crTotal = moleculeBreakdown.get("Coregulator").toString();
		}
		if(moleculeBreakdown.containsKey("Ligand")) {
			ligandTotal = moleculeBreakdown.get("Ligand").toString();
		}
		Ajax.oncomplete("$('#molTotal').text(' "+ this.getRowCount() + "');$('#nrTotal').text(' "+ nrTotal + "')");
		Ajax.oncomplete("$('#crTotal').text(' "+ crTotal + "')");
		Ajax.oncomplete("$('#ligandTotal').text(' "+ ligandTotal + "')");
		
		return returnQuery.getResultList();

	}
	
	private Predicate createRestrictions(CriteriaBuilder cb, Root<MoleculeResult> molRoot) {
		Predicate restrictions = cb.conjunction();

		// Filter
		if (!this.moleculeSearch.getMoleculeTypes().isEmpty()) {
			restrictions = cb.and(
					restrictions,
					molRoot.get(MoleculeResult_.type).in(
							this.moleculeSearch.getMoleculeTypes()));

		}
		
		if (!this.moleculeSearch.getGoTerms().isEmpty()) {
			ListJoin<MoleculeResult, GOTerm>  molGOTerm = molRoot.join(MoleculeResult_.goterms);
			
			restrictions = cb.and(restrictions, molGOTerm.in(this.moleculeSearch.getGoTerms()));
			
		}
		if (!this.moleculeSearch.getDiseases().isEmpty()) {
			Join<MoleculeResult, TranslationalAutoSuggest>  molDisease = molRoot.join(MoleculeResult_.diseases);
			restrictions = cb.and(restrictions, molDisease.in(this.getMoleculeSearch().getDiseases()));
		}
		
		return restrictions;
	}

	private List<SortMeta> createInitialSort() {
		List<SortMeta> initialSort = new ArrayList<SortMeta>();

		// Type
		SortMeta typeSort = new SortMeta();
		typeSort.setSortField("type");
		typeSort.setSortOrder(SortOrder.ASCENDING);
		initialSort.add(typeSort);

		// MoleculeSynonym
		SortMeta symbolSort = new SortMeta();
		symbolSort.setSortField("symbol");
		symbolSort.setSortOrder(SortOrder.ASCENDING);
		initialSort.add(symbolSort);

		// Name
		SortMeta nameSort = new SortMeta();
		nameSort.setSortField("name");
		nameSort.setSortOrder(SortOrder.ASCENDING);
		initialSort.add(nameSort);

		return initialSort;
	}

	@SuppressWarnings("unchecked")
	private Order createOrder(SortMeta sm, Root<?> root, CriteriaBuilder cb) {
		String sortField = sm.getSortField();
		SortOrder sortOrder = sm.getSortOrder();

		if (sortField != null) {
			Path<?> path = null;
			if (!sortField.contains(".")) {
				path = root.get(sortField);
			} else {
				path = root;
				for (String part : sortField.split("\\.")) {
					path = path.get(part);
				}
			}
			if (sortOrder == SortOrder.ASCENDING) {
				return cb.asc(cb.upper((Expression<String>) path));
			} else if (sortOrder == SortOrder.DESCENDING) {
				return cb.desc(cb.upper((Expression<String>) path));
			} else if (sortOrder == SortOrder.UNSORTED) {
				return null;
			}
		}
		return null;
	}

	@Override
	public MoleculeResult getRowData(String rowKey) {
		// TODO: Add Return
		return null;

	}

	public Object getRowKey(Molecule object) {
		// TODO: Add Return
		return null;

	}

	public MoleculeSearch getMoleculeSearch() {
		return moleculeSearch;
	}

	public void setMoleculeSearch(MoleculeSearch moleculeSearch) {
		this.moleculeSearch = moleculeSearch;
	}

	public Map<String, Integer> getMoleculeBreakdown() {
		return moleculeBreakdown;
	}

	public void setMoleculeBreakdown(Map<String, Integer> moleculeBreakdown) {
		this.moleculeBreakdown = moleculeBreakdown;
	}

}
