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
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Jsonp filter for clients requesting jsonp
 * @author mcowiti
 *
 */

@WebFilter(filterName = "jsonpFilter", urlPatterns = { "/rest/pubmed/articles/jsonp/*","/rest/pubmed/abstract/jsonp/*" }, dispatcherTypes = { DispatcherType.REQUEST })
public class JsonpFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        
        if(isJSONPRequest(request))
        {
          ServletOutputStream out = response.getOutputStream();

          out.println(getCallbackMethod(request) + "(");
          chain.doFilter(request, response);
          out.println(");");

          response.setContentType("text/javascript");
        }
        else
        {
          chain.doFilter(request, response);
        }
	}
	
	private String getCallbackMethod(HttpServletRequest httpRequest)
	  {
	    return httpRequest.getParameter("callback");
	  }

	  private boolean isJSONPRequest(HttpServletRequest httpRequest)
	  {
	    String callbackMethod = getCallbackMethod(httpRequest);
	    return (callbackMethod != null && callbackMethod.length() > 0);
	  }

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
