package br.com.k19.modelo;

public class Curso {

	private Long id;
	private String nome;
	private String sigla;;
	private String logo;

	public Curso(Long id, String nome, String sigla, String logo) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.logo = logo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
