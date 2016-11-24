package br.com.junior.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.junior.repository.entity.PessoaEntity;
import br.com.junior.repository.entity.UsuarioEntity;
import br.com.junior.repository.model.PessoaModel;
import br.com.junior.repository.model.UsuarioModel;
import br.com.junior.uteis.Uteis;

public class PessoaRepository {

	@Inject
	PessoaEntity pessoaEntity;

	EntityManager entityManager;

	public void salvar(PessoaModel pessoaModel) {
		entityManager = Uteis.getJpaEntityManager();

		pessoaEntity.setDataCadastro(LocalDateTime.now());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setSexo(pessoaModel.getSexo());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				pessoaModel.getUsuarioModel().getCodigo());

		pessoaEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(pessoaEntity);

		pessoaEntity = new PessoaEntity();
	}

	public List<PessoaModel> getPessoas() {
		List<PessoaModel> pessoasModels = new ArrayList<>();

		entityManager = Uteis.getJpaEntityManager();
		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> colecao = query.getResultList();

		PessoaModel pessoaModel = null;

		for (PessoaEntity pessoaEntity : colecao) {
			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());

			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro());
			pessoaModel.setEmail(pessoaEntity.getEmail());
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			pessoaModel.setNome(pessoaEntity.getNome());

			if (pessoaEntity.getOrigemCadastro().equals("X"))
				pessoaModel.setOrigemCadastro("XML");
			else
				pessoaModel.setOrigemCadastro("INPUT");

			if (pessoaEntity.getSexo().equals("M"))
				pessoaModel.setSexo("Masculino");
			else
				pessoaModel.setSexo("Feminino");

			UsuarioEntity usuarioEntity = pessoaEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			pessoaModel.setUsuarioModel(usuarioModel);

			pessoasModels.add(pessoaModel);
		}

		return pessoasModels;
	}

	public PessoaEntity getPessoa(Integer codigo) {
		entityManager = Uteis.getJpaEntityManager();
		return entityManager.find(PessoaEntity.class, codigo);
	}

	public void alterar(PessoaModel pessoaModel) {
		entityManager = Uteis.getJpaEntityManager();

		PessoaEntity pessoaEntity = this.getPessoa(pessoaModel.getCodigo());

		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		entityManager.merge(pessoaEntity);

	}

	public void excluir(int codigo) {
		entityManager = Uteis.getJpaEntityManager();

		PessoaEntity pessoaEntity = this.getPessoa(codigo);
		entityManager.remove(pessoaEntity);
	}

	public Hashtable<String, Integer> getOrigemPessoa() {
		Hashtable<String, Integer> hashtable = new Hashtable<>();

		entityManager = Uteis.getJpaEntityManager();

		Query query = entityManager.createNamedQuery("PessoaEntity.groupByOrigemCadastro");

		@SuppressWarnings("unchecked")
		Collection<Object[]> colecao = query.getResultList();

		for (Object[] objects : colecao) {

			String tipoPessoa = (String) objects[0];
			int total = ((Long) objects[1]).intValue();
			if (tipoPessoa.equals("X")) {
				tipoPessoa = "XML";
			} else {
				tipoPessoa = "INPUT";
			}
			hashtable.put(tipoPessoa, total);

		}
		return hashtable;
	}

}
