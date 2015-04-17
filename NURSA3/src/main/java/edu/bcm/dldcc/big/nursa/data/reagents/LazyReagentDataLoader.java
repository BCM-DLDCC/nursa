package edu.bcm.dldcc.big.nursa.data.reagents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.omnifaces.util.Ajax;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import edu.bcm.dldcc.big.nursa.model.common.MoleculeAutoSuggest;
import edu.bcm.dldcc.big.nursa.model.search.ReagentResult;
import edu.bcm.dldcc.big.nursa.model.search.ReagentResult_;
import edu.bcm.dldcc.big.nursa.model.search.ReagentSearch;

public class LazyReagentDataLoader extends LazyDataModel<ReagentResult> {

	private static final long serialVersionUID = 7625203008398806277L;

	private ReagentSearch reagentSearch;

	private EntityManager objectEntityManager;

	private List<SortMeta> storedSort;
	
	private Map<String, Integer> reagentBreakdown = new HashMap<String, Integer>();

	public LazyReagentDataLoader() {
		super();
	}

	public LazyReagentDataLoader(EntityManager objectEntityManager,
			ReagentSearch reagentSearch) {
		super();
		this.objectEntityManager = objectEntityManager;
		this.reagentSearch = reagentSearch;
	}

	@Override
	public List<ReagentResult> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		SortMeta sm = new SortMeta();
		sm.setSortField(sortField);
		sm.setSortOrder(sortOrder);
		List<SortMeta> multiSortMeta = new ArrayList<SortMeta>();
		multiSortMeta.add(sm);
		return load(first, pageSize, multiSortMeta, filters);
	}

	public List<ReagentResult> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {

		// Setup
		CriteriaBuilder cb = objectEntityManager.getCriteriaBuilder();

		// Count Query
		CriteriaQuery<Tuple> cqCount = cb.createTupleQuery();

		Root<ReagentResult> reagentRootCount = cqCount.from(ReagentResult.class);
		cqCount.multiselect(reagentRootCount.get(ReagentResult_.type),
				cb.countDistinct(reagentRootCount));
		cqCount.where(createRestrictions(cb, reagentRootCount));
		cqCount.groupBy(reagentRootCount.get(ReagentResult_.type));

		int total = 0;
		reagentBreakdown = new HashMap<String, Integer>();
		for (Tuple tuple : objectEntityManager.createQuery(cqCount)
				.getResultList()) {
			reagentBreakdown.put((String) tuple.get(0),
					((Long) tuple.get(1)).intValue());
			total = total + ((Long) tuple.get(1)).intValue();
		}
		this.setRowCount(total);

		// Result Page Query with order

		CriteriaQuery<ReagentResult> cQuery = cb
				.createQuery(ReagentResult.class);
		Root<ReagentResult> reagentRoot = cQuery.from(ReagentResult.class);

		// Order
		List<Order> sortOrder = new ArrayList<Order>();

		// Defaults to initial sort
		if ((multiSortMeta == null) && (this.storedSort == null)) {
			multiSortMeta = createInitialSort();
		} else if ((multiSortMeta == null) && (this.storedSort != null)) {
			multiSortMeta = this.storedSort;
		}
		this.storedSort = multiSortMeta;

		boolean makeDistinct = true;
		for (SortMeta sm : multiSortMeta) {
			Order newOrder = createOrder(sm, reagentRoot, cb);
			if ((makeDistinct) && (sm.getSortField() != null) && (sm.getSortField().contains("."))) {
				makeDistinct = false;
			}
			if (newOrder != null) {
				sortOrder.add(newOrder);
			}
		}

		if (makeDistinct) {
			cQuery.distinct(true);
		}
		

		if (sortOrder.size() > 0) {
			cQuery.orderBy(sortOrder);
		}

		cQuery.where(createRestrictions(cb, reagentRoot));
		
		cQuery.select(reagentRoot);

		// Restrict returns
		TypedQuery<ReagentResult> returnQuery = objectEntityManager
				.createQuery(cQuery);
		returnQuery.setFirstResult(first);
		returnQuery.setMaxResults(pageSize);

		/*
		 * omnifaces to set the rowcount in the molFilter since it winds up
		 * being a step behind otherwise
		 */
		//Animal Model
		//Antibody
		//Embryonic Stem Cell
		//Primer
		//Plasmid
		String animalModelTotal = "0";
		String antibodyTotal = "0";
		String scTotal = "0";
		String primerTotal = "0";
//		String plasmidTotal = "0";

		if (reagentBreakdown.containsKey("Animal Model")) {
			animalModelTotal = reagentBreakdown.get("Animal Model").toString();
		}
		if (reagentBreakdown.containsKey("Antibody")) {
			antibodyTotal = reagentBreakdown.get("Antibody").toString();
		}
		if (reagentBreakdown.containsKey("Cell Line")) {
			scTotal = reagentBreakdown.get("Cell Line").toString();
		}
		if (reagentBreakdown.containsKey("Primer")) {
			primerTotal = reagentBreakdown.get("Primer").toString();
		}
//		if (reagentBreakdown.containsKey("Plasmid")) {
//			plasmidTotal = reagentBreakdown.get("Plasmid").toString();
//		}
		
		Ajax.oncomplete("$('#reagentTotal').text(' " + this.getRowCount() + "');");
		
		Ajax.oncomplete("$('#animalModelTotal').text(' " + animalModelTotal + "')");
		Ajax.oncomplete("$('#antibodyTotal').text(' " + antibodyTotal + "')");
		Ajax.oncomplete("$('#cellLineTotal').text(' " + scTotal + "')");
		Ajax.oncomplete("$('#primerTotal').text(' " + primerTotal + "')");
//		Ajax.oncomplete("$('#plasmidTotal').text(' " + plasmidTotal + "')");
		
		return returnQuery.getResultList();

	}

	private List<SortMeta> createInitialSort() {
		List<SortMeta> initialSort = new ArrayList<SortMeta>();

		// Type
		SortMeta typeSort = new SortMeta();
		typeSort.setSortField("type");
		typeSort.setSortOrder(SortOrder.ASCENDING);
		initialSort.add(typeSort);


		// Name
		SortMeta nameSort = new SortMeta();
		nameSort.setSortField("name");
		nameSort.setSortOrder(SortOrder.ASCENDING);
		initialSort.add(nameSort);

		return initialSort;
	}
	
	private Predicate createRestrictions(CriteriaBuilder cb, Root<ReagentResult> reagentRoot) {
		Predicate restrictions = cb.conjunction();

		// Filter
		if (!this.reagentSearch.getReagentTypes().isEmpty()) {
			restrictions = cb.and(
					restrictions,
					reagentRoot.get(ReagentResult_.type).in(
							this.reagentSearch.getReagentTypes()));

		}
		if (!this.reagentSearch.getSpecies().isEmpty()) {
			restrictions = cb.and(restrictions,
					reagentRoot.get(ReagentResult_.species).in(this.reagentSearch.getSpecies()));
		}
		if (!this.reagentSearch.getMolecules().isEmpty()) {
			List<String> moleculeDOI = new ArrayList<String>();
			for(MoleculeAutoSuggest mas : this.reagentSearch.getMolecules()) {
				if(mas != null) {
					moleculeDOI.add(mas.getDoi());
				}
			}
			if(!moleculeDOI.isEmpty()) {
			restrictions = cb.and(restrictions,
					reagentRoot.get(ReagentResult_.symbolDOI).in(moleculeDOI));
			}
		}
		
		
		
		return restrictions;
	}

	@SuppressWarnings("unchecked")
	private Order createOrder(SortMeta sm, Root<ReagentResult> reagentRoot, CriteriaBuilder cb) {
		String sortField = sm.getSortField();
		SortOrder sortOrder = sm.getSortOrder();

		if (sortField != null) {
			Path<?> path = null;
			if (!sortField.contains(".")) {
				path = reagentRoot.get(sortField);
			} else {
				path = createPath(reagentRoot, sortField);
			}
			
			Expression exp;
			if (path.getJavaType().equals(Integer.class)) {
				exp = (Expression<Integer>) path;
			} else {
				exp = cb.upper((Expression<String>) path);
			}

			if (sortOrder == SortOrder.ASCENDING) {
				return cb.asc(exp);
			} else if (sortOrder == SortOrder.DESCENDING) {
				return cb.desc(exp);
			} else if (sortOrder == SortOrder.UNSORTED) {
				return null;
			}
		}
		return null;
	}
	

	private Path<?> createPath(Root<ReagentResult> root, String stringPath) {
		Path<?> finalPath = root;
		
		// generate a JPA path from the string key
		Join<?, ?> path = null;// = root.get;

		if (stringPath.contains(".")) {
			String[] parts = stringPath.split("\\.");
			path = root.join(parts[0]);
			for (int i = 1; i <= parts.length - 2; i++) {
				path = path.join(parts[i]);
			}
			finalPath = path.get(parts[parts.length - 1]);
		} else {
			finalPath = root.join(stringPath);
		}

		return finalPath;
	}

	


	@Override
	public ReagentResult getRowData(String rowKey) {
		// TODO: Add Return
		return null;

	}

	public Object getRowKey(ReagentResult object) {
		// TODO: Add Return
		return null;

	}

	public ReagentSearch getReagentSearch() {
		return reagentSearch;
	}

	public void setReagentSearch(ReagentSearch reagentSearch) {
		this.reagentSearch = reagentSearch;
	}

	/**
	 * @return the reagentBreakdown
	 */
	public Map<String, Integer> getReagentBreakdown() {
		return reagentBreakdown;
	}

	/**
	 * @param reagentBreakdown the reagentBreakdown to set
	 */
	public void setReagentBreakdown(Map<String, Integer> reagentBreakdown) {
		this.reagentBreakdown = reagentBreakdown;
	}

}
