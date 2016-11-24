package br.com.junior.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.junior.repository.entity.UsuarioEntity;
import br.com.junior.repository.model.UsuarioModel;
import br.com.junior.uteis.Uteis;

public class UsuarioRepository implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioEntity validaUsuario(UsuarioModel usuarioModel) {
		try {
			/*Query que vai ser executada (UsuarioEntity.findUser) */
			Query query = Uteis.getJpaEntityManager().createNamedQuery("UsuarioEntity.findUser");
			
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());
			
			return (UsuarioEntity) query.getSingleResult();
			
		} catch (Exception e) {
			return null;
		}
	}

}
