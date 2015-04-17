package edu.bcm.dldcc.big.nursa.util.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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

import edu.bcm.dldcc.big.nursa.model.common.DOI;
import edu.bcm.dldcc.big.nursa.util.qualifier.NoConversationDatabase;

@WebFilter(filterName = "DoiRedirect", urlPatterns = { "*.jsf" }, dispatcherTypes = { DispatcherType.REQUEST })
public class DoiRedirect implements Filter {

	@Inject
	@NoConversationDatabase
	private EntityManager noConvoEntityManager;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String doiId = request.getParameter("doi");
        if ((doiId != null) && (!doiId.equals(""))) {
                String requestURI = request.getRequestURI();
                String baseURL = request.getContextPath();
                String qs = request.getQueryString();

                DOI doi = noConvoEntityManager.find(DOI.class, doiId);

                if (doi != null) {
                	//make sure we're using an absolute URL
                	String redirectURL = baseURL + (doi.getUrl().charAt(0)=='/'?doi.getUrl():"/"+doi.getUrl());
                	String compareURL = requestURI+"?"+URLDecoder.decode(qs,"UTF-8");

                	
                	//Don't redirect if are already at the correct place.
                        if (!redirectURL.equals(compareURL)) {
                            System.out.println("REDIRECT" + redirectURL);
                                response.sendRedirect(redirectURL);
                        }

                }
        }

        chain.doFilter(req, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
