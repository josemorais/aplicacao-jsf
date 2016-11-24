package br.com.junior.controller;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.junior.repository.PessoaRepository;
import br.com.junior.repository.model.PessoaModel;
import br.com.junior.uteis.Uteis;

@Named
@RequestScoped
public class CadastrarPessoaController {

	@Inject
	private PessoaModel pessoaModel;

	@Inject
	private UsuarioController usuarioController;

	@Inject
	private PessoaRepository pessoaRepository;

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	public void salvar() {

		pessoaModel.setUsuarioModel(this.usuarioController.getUsuarioSession());
		pessoaModel.setOrigemCadastro("I");

		pessoaRepository.salvar(pessoaModel);

		pessoaModel = null;

		Uteis.mostrarMensagemInfo("Registro cadastrado com sucesso");

	}

	public void uploadRegistros() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			if (StringUtils.isEmpty(this.file.getFileName())) {
				Uteis.mostrarMensagem("Nenhum arquivo selecionado");
				return;
			}

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(this.file.getInputstream());
			Element element = document.getDocumentElement();
			NodeList nodes = element.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element elementPessoa = (Element) node;

					String nome = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0)
							.getNodeValue();
					String sexo = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0)
							.getNodeValue();
					String email = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0)
							.getNodeValue();
					String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0)
							.getNodeValue();

					PessoaModel newPessoaModel = new PessoaModel();

					newPessoaModel.setUsuarioModel(this.usuarioController.getUsuarioSession());
					newPessoaModel.setEmail(email);
					newPessoaModel.setEndereco(endereco);
					newPessoaModel.setNome(nome);
					newPessoaModel.setOrigemCadastro("X");
					newPessoaModel.setSexo(sexo);

					// SALVANDO UM REGISTRO QUE VEIO DO ARQUIVO XML
					pessoaRepository.salvar(newPessoaModel);

				}
			}
			Uteis.mostrarMensagemInfo("Registros cadastrados com sucesso!");

		} catch (ParserConfigurationException e) {

			e.printStackTrace();

		} catch (SAXException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
