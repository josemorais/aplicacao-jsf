package br.com.k19.controle;

import br.com.k19.modelo.Instrutor;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class InstrutorBean {

	private List<Instrutor> instrutores = new ArrayList<Instrutor>();

	public InstrutorBean() {
		Instrutor rafael = new Instrutor();
		rafael.setNome("Rafael Cosentino");
		rafael.setDataDeNascimento("30/10/1984");

		Instrutor marcelo = new Instrutor();
		marcelo.setNome("Marcelo Martins");
		marcelo.setDataDeNascimento("02/04/1985");

		this.instrutores.add(rafael);
		this.instrutores.add(marcelo);
	}

	public List<Instrutor> getInstrutores() {
		return instrutores;
	}

	public void setInstrutores(List<Instrutor> instrutores) {
		this.instrutores = instrutores;
	}
}
