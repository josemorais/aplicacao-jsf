package br.com.junior.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * ESSE FILTER VAI SER CHAMADO TODA VEZ QUE FOR REALIZADO UMA REQUISIÇÃO PARA O
 * FACES SERVLET
 */
@WebFilter(servletNames = { "Faces Servlet" })
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory;

	private String nomeUnidadePersistencia = "unit_app";

	/**
	 * Default constructor.
	 */
	public JPAFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.entityManagerFactory.close();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/* Criando o entityManager */
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();

		/* Adiciona o entityManager na requisição */
		request.setAttribute("entityManager", entityManager);

		/* Iniciando a transação */
		entityManager.getTransaction().begin();

		/* Iniciando o Faces Servlet */
		chain.doFilter(request, response);

		try {
			/* Se não houver erro na operação executa o commit */
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			/* Caso aconteça algum erro na operação executa o rollback */
			entityManager.getTransaction().rollback();
		} finally {
			/* Finaliza o entityManager */
			entityManager.close();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// Cria o entityManagerFactory baseado nos parâmetros definidos no
		// persistence.xml
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.nomeUnidadePersistencia);
	}

}
