package edu.bcm.dldcc.big.nursa.util.filter;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bcm.dldcc.big.nursa.model.core.Gene;
import edu.bcm.dldcc.big.nursa.model.core.Gene_;
import edu.bcm.dldcc.big.nursa.model.filter.NURSAFilter;
import edu.bcm.dldcc.big.nursa.model.filter.NURSAFilter_;
import edu.bcm.dldcc.big.nursa.util.qualifier.NoConversationDatabase;

@WebFilter(filterName = "cfmRedirect", urlPatterns = { "*.cfm" }, dispatcherTypes = { DispatcherType.REQUEST })
public class ColdFusionFilter implements Filter {

	@Inject
	@NoConversationDatabase
	private EntityManager noConvoEntityManager;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		String baseURL = request.getContextPath() + "/";
		String requestURI = request.getRequestURI().replace(baseURL, "");

		String redirectURL = null;

		String molType = request.getParameter("molType");
		if ((molType != null) && (molType.equalsIgnoreCase("gene"))) {
			String identifier = request.getParameter("gene_id");
			redirectURL = getGeneRedirectURL(identifier);

		} else if (molType != null) {
			String identifier = request.getParameter("molId");
			//Not all parameters coming in are capitalized in that way.
			if (identifier == null) {
				for (String name : request.getParameterMap().keySet()) {
					if (name.equalsIgnoreCase("molid")) {
						identifier = request.getParameter(name);
						break;
					}
				}
			}
			redirectURL = getRedirectURL(requestURI, molType, identifier);
		} else {
			String doi = request.getParameter("doi");
			if (doi != null) {
				redirectURL = getRedirectURL(requestURI, null, doi);
			}
		}

		// If no redirect URL given go to index
		if (redirectURL == null) {
			redirectURL = "./index.html";
		}

		response.sendRedirect(redirectURL);
	}

	private String getGeneRedirectURL(String entrezID) {
		CriteriaBuilder cb = noConvoEntityManager.getCriteriaBuilder();
		CriteriaQuery<Gene> geneCriteria = cb.createQuery(Gene.class);
		Root<Gene> geneRoot = geneCriteria.from(Gene.class);
		geneCriteria
				.where(cb.equal(geneRoot.get(Gene_.entrezGeneId), entrezID));

		geneCriteria.select(geneRoot);
		List<Gene> genes = noConvoEntityManager.createQuery(geneCriteria)
				.getResultList();
		if (!genes.isEmpty()) {
			return genes.get(0).getDoi().getUrl();
		}

		return null;
	}

	private String getRedirectURL(String request, String molType,
			String identifier) {
		CriteriaBuilder cb = noConvoEntityManager.getCriteriaBuilder();
		CriteriaQuery<NURSAFilter> criteria = cb.createQuery(NURSAFilter.class);
		Root<NURSAFilter> filter = criteria.from(NURSAFilter.class);
		Predicate restrictions = cb.equal(filter.get(NURSAFilter_.request),
				request);

		if (molType != null) {
			restrictions = cb.and(restrictions,
					cb.equal(filter.get(NURSAFilter_.molType), molType));
		}
		if (identifier != null) {
			restrictions = cb.and(restrictions,
					cb.equal(filter.get(NURSAFilter_.identifier), identifier));
		}
		criteria.where(restrictions);
		criteria.select(filter);
		try {
			NURSAFilter result = noConvoEntityManager.createQuery(criteria)
					.getSingleResult();

			if (result != null) {
				// If the result has a DOI
				if (result.getDoi() != null) {
					// If the DOI has a URL that isn't blank or null use that
					// URL
					if ((result.getDoi().getUrl() != null)
							&& (!result.getDoi().getUrl().isEmpty())) {
						return result.getDoi().getUrl();
					} else {
						// else create the URL using the doi and the the
						// redirect page
						return result.getRedirect() + "?doi="
								+ result.getDoi().getDoi();
					}

				} else {
					// If the result doesn't have a doi then just return the
					// redirect page
					return result.getRedirect();
				}
			}
		} catch (NoResultException e) {

		}

		return null;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
