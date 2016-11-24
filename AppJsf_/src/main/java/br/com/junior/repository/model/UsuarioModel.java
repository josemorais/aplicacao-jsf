package br.com.junior.repository.model;

import java.io.Serializable;

/**
 * Classe que irá ser utilizada pelo managerBean para receber os dados que são
 * informados na página
 * 
 * @author junior
 *
 */
public class UsuarioModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer codigo;
	private String usuario;
	private String senha;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
