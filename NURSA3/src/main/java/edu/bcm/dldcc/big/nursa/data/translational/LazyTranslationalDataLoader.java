package edu.bcm.dldcc.big.nursa.data.translational;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.omnifaces.util.Ajax;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import edu.bcm.dldcc.big.nursa.model.common.MoleculeAutoSuggest;
import edu.bcm.dldcc.big.nursa.model.common.TranslationalAutoSuggest;
import edu.bcm.dldcc.big.nursa.model.search.TranslationalResult;
import edu.bcm.dldcc.big.nursa.model.search.TranslationalResult_;
import edu.bcm.dldcc.big.nursa.model.search.TranslationalSearch;

public class LazyTranslationalDataLoader extends
		LazyDataModel<TranslationalResult> {

	private static final long serialVersionUID = 7625203008398806277L;

	private TranslationalSearch translationalSearch;

	private EntityManager objectEntityManager;

	private List<SortMeta> storedSort;

	private Map<String, Integer> translationalBreakdown = new HashMap<String, Integer>();

	public LazyTranslationalDataLoader() {
		super();
	}

	public LazyTranslationalDataLoader(EntityManager objectEntityManager,
			TranslationalSearch translationalSearch) {
		super();
		this.objectEntityManager = objectEntityManager;
		this.translationalSearch = translationalSearch;
	}

	@Override
	public List<TranslationalResult> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, String> filters) {
		SortMeta sm = new SortMeta();
		sm.setSortField(sortField);
		sm.setSortOrder(sortOrder);
		List<SortMeta> multiSortMeta = new ArrayList<SortMeta>();
		multiSortMeta.add(sm);
		return load(first, pageSize, multiSortMeta, filters);
	}

	public List<TranslationalResult> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {

		// Setup
		CriteriaBuilder cb = objectEntityManager.getCriteriaBuilder();

		// Count Query
		CriteriaQuery<Tuple> cqCount = cb.createTupleQuery();

		Root<TranslationalResult> translationalRootCount = cqCount
				.from(TranslationalResult.class);
		cqCount.multiselect(
				translationalRootCount.get(TranslationalResult_.type),
				cb.countDistinct(translationalRootCount));
		cqCount.where(createRestrictions(cb, translationalRootCount));
		cqCount.groupBy(translationalRootCount.get(TranslationalResult_.type));

		int total = 0;
		translationalBreakdown = new HashMap<String, Integer>();
		for (Tuple tuple : objectEntityManager.createQuery(cqCount)
				.getResultList()) {
			translationalBreakdown.put((String) tuple.get(0),
					((Long) tuple.get(1)).intValue());
			total = total + ((Long) tuple.get(1)).intValue();
		}
		this.setRowCount(total);

		// Result Page Query with order

		CriteriaQuery<TranslationalResult> cQuery = cb
				.createQuery(TranslationalResult.class);
		Root<TranslationalResult> translationalRoot = cQuery
				.from(TranslationalResult.class);

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
			Order newOrder = createOrder(sm, translationalRoot, cb);
			if ((makeDistinct) && (sm.getSortField() != null)
					&& (sm.getSortField().contains("."))) {
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

		cQuery.where(createRestrictions(cb, translationalRoot));

		cQuery.select(translationalRoot);

		// Restrict returns
		TypedQuery<TranslationalResult> returnQuery = objectEntityManager
				.createQuery(cQuery);
		returnQuery.setFirstResult(first);
		returnQuery.setMaxResults(pageSize);

		String clinicalTrialTotal = "0";
		String diseaseTotal = "0";
		String drugTotal = "0";

		if (translationalBreakdown.containsKey("Clinical Trial")) {
			clinicalTrialTotal = translationalBreakdown.get("Clinical Trial")
					.toString();
		}
		if (translationalBreakdown.containsKey("Disease")) {
			diseaseTotal = translationalBreakdown.get("Disease").toString();
		}
		if (translationalBreakdown.containsKey("Drug")) {
			drugTotal = translationalBreakdown.get("Drug").toString();
		}

		Ajax.oncomplete("$('#translationalTotal').text('" + this.getRowCount()
				+ "')");

		Ajax.oncomplete("$('#clinicalTrialTotal').text(' " + clinicalTrialTotal
				+ "')");
		Ajax.oncomplete("$('#diseaseTotal').text(' " + diseaseTotal + "')");
		Ajax.oncomplete("$('#drugTotal').text(' " + drugTotal + "')");
		List<TranslationalResult> results = returnQuery.getResultList();
		return results;

	}

	private Predicate createRestrictions(CriteriaBuilder cb,
			Root<TranslationalResult> translationalRoot) {
		Predicate restrictions = cb.conjunction();
		
		List<String> dois = new LinkedList<String>();

		// Filter
		if (!this.translationalSearch.getTranslationalTypes().isEmpty()) {
			restrictions = cb.and(
					restrictions,
					translationalRoot.get(TranslationalResult_.type).in(
							this.translationalSearch.getTranslationalTypes()));
		}
		// Molecules
		if (!this.translationalSearch.getMolecules().isEmpty()) {
			Join<TranslationalResult, MoleculeAutoSuggest> mas = translationalRoot
					.join(TranslationalResult_.molecules, JoinType.LEFT);
			restrictions = cb.and(restrictions,
					mas.in(translationalSearch.getMolecules()));
		}
		// Diseases
		if (!this.translationalSearch.getDiseases().isEmpty()) {
			Join<TranslationalResult, TranslationalAutoSuggest> tas = translationalRoot
					.join(TranslationalResult_.diseases, JoinType.LEFT);
			restrictions = cb.and(restrictions,
					tas.in(translationalSearch.getDiseases()));
			
			
			
			for(TranslationalAutoSuggest diseaseTAS : translationalSearch.getDiseases()) {
				dois.add(diseaseTAS.getDoi());
			}
			
			
		}
		// Drugs
		if (!this.translationalSearch.getDrugs().isEmpty()) {
			Join<TranslationalResult, TranslationalAutoSuggest> tas = translationalRoot
					.join(TranslationalResult_.drugs, JoinType.LEFT);
			restrictions = cb.and(restrictions,
					tas.in(translationalSearch.getDrugs()));
			
			
			for(TranslationalAutoSuggest drugsTAS : translationalSearch.getDrugs()) {
				dois.add(drugsTAS.getDoi());
			}
			
		}
		if(!dois.isEmpty()) {
			restrictions = cb.or(restrictions, translationalRoot.get(TranslationalResult_.doi).in(dois));

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
	public TranslationalResult getRowData(String rowKey) {
		// TODO: Add Return
		return null;

	}

	public Object getRowKey(TranslationalResult object) {
		// TODO: Add Return
		return null;

	}

	public TranslationalSearch getTranslationalSearch() {
		return translationalSearch;
	}

	public void setTranslationalSearch(TranslationalSearch translationalSearch) {
		this.translationalSearch = translationalSearch;
	}

	/**
	 * @return the translationalBreakdown
	 */
	public Map<String, Integer> getTranslationalBreakdown() {
		return translationalBreakdown;
	}

	/**
	 * @param translationalBreakdown
	 *            the translationalBreakdown to set
	 */
	public void setTranslationalBreakdown(
			Map<String, Integer> translationalBreakdown) {
		this.translationalBreakdown = translationalBreakdown;
	}

}
