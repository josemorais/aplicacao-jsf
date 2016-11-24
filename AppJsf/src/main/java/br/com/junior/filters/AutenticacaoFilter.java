package br.com.junior.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.junior.repository.model.UsuarioModel;

/**
 * Servlet Filter implementation class AutenticacaoFilter
 */
@WebFilter("/sistema/*")
public class AutenticacaoFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AutenticacaoFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpSession httpSession = ((HttpServletRequest) request).getSession();

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (httpServletRequest.getRequestURI().indexOf("index.xhtml") <= -1) {

			UsuarioModel usuarioModel = (UsuarioModel) httpSession.getAttribute("usuarioAutenticado");

			if (usuarioModel == null) {

				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.xhtml");

			} else {

				chain.doFilter(request, response);
			}
		} else {

			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
