package br.com.k19.controle;

import br.com.k19.modelo.Estado;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
public class CadastroBean {

	private String nome;
	private String cidade;
	private String siglaDoEstadoEscolhido;

	private Estado estadoSelecionado = new Estado();
	private List<Estado> estados = new ArrayList<Estado>();

	public CadastroBean() {
		Estado sp = new Estado();
		sp.setSigla("SP");
		sp.setNome("São Paulo");
		sp.getCidades().add("São Paulo");
		sp.getCidades().add("Campinas");

		Estado rj = new Estado();
		rj.setSigla("RJ");
		rj.setNome("Rio de Janeiro");
		rj.getCidades().add("Rio de Janeiro");
		rj.getCidades().add("Niterói");

		Estado rn = new Estado();
		rn.setSigla("RN");
		rn.setNome("Rio Grande do Norte");
		rn.getCidades().add("Natal");
		rn.getCidades().add("Mossoró");

		this.estados.add(sp);
		this.estados.add(rj);
		this.estados.add(rn);
	}

	public void mudaEstado(ValueChangeEvent event) {
		this.siglaDoEstadoEscolhido = event.getNewValue().toString();
		for (Estado e : this.estados) {
			if (e.getSigla().equals(this.siglaDoEstadoEscolhido)) {
				this.estadoSelecionado = e;
				break;
			}
		}
		FacesContext.getCurrentInstance().renderResponse();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getSiglaDoEstadoEscolhido() {
		return siglaDoEstadoEscolhido;
	}

	public void setSiglaDoEstadoEscolhido(String siglaDoEstadoEscolhido) {
		this.siglaDoEstadoEscolhido = siglaDoEstadoEscolhido;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
}
