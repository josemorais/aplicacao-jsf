package br.com.junior.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.junior.repository.PessoaRepository;
import br.com.junior.repository.model.PessoaModel;

@Named
@ViewScoped
public class ConsultarPessoaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaModel pessoaModel;
	
	@Inject
	transient private PessoaRepository pessoaRepository;

	private List<PessoaModel> pessoas;

	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}

	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	@PostConstruct
	public void init() {
		this.pessoas = pessoaRepository.getPessoas();
	}

	public void prepararRegistro(PessoaModel pessoaModel) {
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1));
		this.pessoaModel = pessoaModel;
	}

	public void atualizar() {
		this.pessoaRepository.alterar(this.pessoaModel);
		this.init();
	}

	public void excluir(PessoaModel pessoaModel) {
		this.pessoaRepository.excluir(pessoaModel.getCodigo());
		this.pessoas.remove(pessoaModel);
	}

}
