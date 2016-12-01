package br.com.k19.controle;

import javax.faces.bean.ManagedBean;

import br.com.k19.modelo.Funcionario;

@ManagedBean
public class FuncionarioBean {

	private Funcionario funcionario = new Funcionario();

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}	

}
