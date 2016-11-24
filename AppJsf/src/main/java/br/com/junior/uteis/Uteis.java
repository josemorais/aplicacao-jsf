package br.com.junior.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

public class Uteis {

	/* Método responsável por recuperar o entityManager criado no JpaFilter */
	public static EntityManager getJpaEntityManager() {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		return (EntityManager) request.getAttribute("entityManager");
	}

	public static void mostrarMensagem(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Alerta", mensagem));
	}

	public static void mostrarMensagemAtencao(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
	}

	public static void mostrarMensagemInfo(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}

}
