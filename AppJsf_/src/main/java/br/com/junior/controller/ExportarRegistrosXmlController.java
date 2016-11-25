package br.com.junior.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.junior.repository.PessoaRepository;
import br.com.junior.repository.model.PessoaModel;

@Named
@RequestScoped
public class ExportarRegistrosXmlController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	transient private PessoaRepository pessoaRepository;

	private StreamedContent streamedContent;

	public StreamedContent getStreamedContent() {
		this.downloadArquivoXmlPessoa();
		return streamedContent;
	}

	public void downloadArquivoXmlPessoa() {
		// TODO Auto-generated method stub
		File arquivoXml = this.gerarXmlPessoas();

		InputStream inputStream;

		try {
			inputStream = new FileInputStream(arquivoXml.getPath());
			streamedContent = new DefaultStreamedContent(inputStream, "application/xml", arquivoXml.getPath());
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private File gerarXmlPessoas() {
		// TODO Auto-generated method stub
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		List<PessoaModel> pessoaModels = pessoaRepository.getPessoas();

		Element elementPessoas = new Element("Pessoas");

		pessoaModels.forEach(pessoa -> {
			Element element = new Element("Pessoa");
			element.addContent(new Element("codigo").setText(pessoa.getCodigo().toString()));
			element.addContent(new Element("nome").setText(pessoa.getNome()));
			element.addContent(new Element("sexo").setText(pessoa.getSexo().substring(0, 1)));
			element.addContent(new Element("dataCadastro").setText(pessoa.getDataCadastro().format(dateTimeFormatter)));
			element.addContent(new Element("email").setText(pessoa.getEmail()));
			element.addContent(new Element("endereco").setText(pessoa.getEndereco()));
			element.addContent(new Element("origemCadastro").setText(pessoa.getOrigemCadastro()));
			element.addContent(new Element("usuarioCadastro").setText(pessoa.getUsuarioModel().getUsuario()));
			elementPessoas.addContent(element);
		});

		Document document = new Document(elementPessoas);
		XMLOutputter xmlGerado = new XMLOutputter();

		String nomeArquivo = "pessoas_".concat(java.util.UUID.randomUUID().toString()).concat(".xml");
		File arquivo = new File("d://xml/".concat(nomeArquivo));
		try {
			OutputStream os = new FileOutputStream(arquivo);
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF8");
			xmlGerado.output(document, osw);
			os.close();
			osw.close();
			return arquivo;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
