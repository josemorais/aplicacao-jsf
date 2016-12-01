package br.com.k19.controle;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean
public class ExtratoBean {

	private Date dataInicial;
	private Date dataFinal;

	public void validaDatas(ComponentSystemEvent event) {

		UIComponent source = event.getComponent();
		UIInput dataInicialInput = (UIInput) source.findComponent("id_data_inicial");
		UIInput dataFinalInput = (UIInput) source.findComponent("id_data_final");

		if (dataInicialInput.isValid() && dataFinalInput.isValid()) {

			Date dataInicialEscolhida = (Date) dataInicialInput.getLocalValue();
			Date dataFinalEscolhida = (Date) dataFinalInput.getLocalValue();

			if (dataFinalEscolhida.before(dataInicialEscolhida)) {
				FacesMessage message = new FacesMessage("A data final não pode vir antes da data inicial");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(source.getClientId(), message);
				context.renderResponse();
			}
		}
	}

	public void geraExtrato() {
		FacesMessage message = new FacesMessage("extrato gerado com sucesso");
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
}
