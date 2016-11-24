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
 * ESSE FILTER VAI SER CHAMADO TODA VEZ QUE FOR REALIZADO UMA REQUISI��O PARA O
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

		/* Adiciona o entityManager na requisi��o */
		request.setAttribute("entityManager", entityManager);

		/* Iniciando a transa��o */
		entityManager.getTransaction().begin();

		/* Iniciando o Faces Servlet */
		chain.doFilter(request, response);

		try {
			/* Se n�o houver erro na opera��o executa o commit */
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			/* Caso aconte�a algum erro na opera��o executa o rollback */
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
		// Cria o entityManagerFactory baseado nos par�metros definidos no
		// persistence.xml
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.nomeUnidadePersistencia);
	}

}
