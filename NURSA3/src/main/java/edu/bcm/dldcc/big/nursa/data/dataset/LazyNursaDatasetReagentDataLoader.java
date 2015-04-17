package edu.bcm.dldcc.big.nursa.data.dataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import edu.bcm.dldcc.big.nursa.model.Molecule;
import edu.bcm.dldcc.big.nursa.model.search.DatasetMoleculeResult;
import edu.bcm.dldcc.big.nursa.model.search.DatasetReagentResult;
import edu.bcm.dldcc.big.nursa.model.search.DatasetReagentResult_;

/**
 * Lazy fetch the dataset reagents
 * Setting lazy load on Entity is not good enough for large collections such as these
 * @author mcowiti
 *
 */
public class LazyNursaDatasetReagentDataLoader extends LazyDataModel<DatasetReagentResult> {

	
	private static final long serialVersionUID = 8077082333273820278L;

	private EntityManager objectEntityManager;

	private List<SortMeta> storedSort;
	
	private Map<String, Integer> moleculeBreakdown = new HashMap<String, Integer>();
	
	private String datasetDoi;

	public LazyNursaDatasetReagentDataLoader() {
		super();
	}

	public LazyNursaDatasetReagentDataLoader(EntityManager objectEntityManager,
			String datasetDoi) {
		super();
		this.objectEntityManager = objectEntityManager;
		this.datasetDoi=datasetDoi;
	}

	@Override
	public List<DatasetReagentResult> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		SortMeta sm = new SortMeta();
		sm.setSortField(sortField);
		sm.setSortOrder(sortOrder);
		List<SortMeta> multiSortMeta = new ArrayList<SortMeta>();
		multiSortMeta.add(sm);
		return load(first, pageSize, multiSortMeta, filters);
	}

	public List<DatasetReagentResult> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		
		// Setup
		CriteriaBuilder cb = objectEntityManager.getCriteriaBuilder();

		
		CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
		Root<DatasetReagentResult> molRootCount = cqCount.from(DatasetReagentResult.class);
		cqCount.where(createRestrictions(cb, molRootCount));
		cqCount.select(cb.countDistinct(molRootCount));
		this.setRowCount(objectEntityManager.createQuery(cqCount).getSingleResult().intValue());
		
	
		// Result Page Query with order
		
		CriteriaQuery<DatasetReagentResult> cQuery = cb.createQuery(DatasetReagentResult.class);
		Root<DatasetReagentResult> molRoot = cQuery.from(DatasetReagentResult.class);
		
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
		Query returnQuery = objectEntityManager.createQuery(cQuery);
		returnQuery.setFirstResult(first);
		returnQuery.setMaxResults(pageSize);
		
		return returnQuery.getResultList();

	}
	
	private Predicate createRestrictions(CriteriaBuilder cb, Root<DatasetReagentResult> molRoot) {
		
		
		Predicate restrictions = cb.equal(molRoot.get(DatasetReagentResult_.datasetDoi), this.datasetDoi); 

		return restrictions;
	}

	/**
	 * UI sorts on name, type, speciesName, source
	 * @return
	 */
	private List<SortMeta> createInitialSort() {
		List<SortMeta> initialSort = new ArrayList<SortMeta>();

		
		SortMeta nameSort = new SortMeta();
		nameSort.setSortField("name");
		nameSort.setSortOrder(SortOrder.ASCENDING);
		initialSort.add(nameSort);
		
		SortMeta typeSort = new SortMeta();
		typeSort.setSortField("type");
		typeSort.setSortOrder(SortOrder.ASCENDING);
		initialSort.add(typeSort);
		
		SortMeta speciesSort = new SortMeta();
		speciesSort.setSortField("speciesCommonName");
		speciesSort.setSortOrder(SortOrder.ASCENDING);
		initialSort.add(speciesSort);
		
		SortMeta sourceSort = new SortMeta();
		sourceSort.setSortField("source");
		sourceSort.setSortOrder(SortOrder.ASCENDING);
		initialSort.add(sourceSort);

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
	public DatasetReagentResult getRowData(String rowKey) {
		return null;

	}

	public Object getRowKey(Molecule object) {
		return null;

	}


	public Map<String, Integer> getMoleculeBreakdown() {
		return moleculeBreakdown;
	}

	public void setMoleculeBreakdown(Map<String, Integer> moleculeBreakdown) {
		this.moleculeBreakdown = moleculeBreakdown;
	}

}
