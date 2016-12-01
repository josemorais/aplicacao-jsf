package br.com.k19.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.k19.modelo.Curso;
import br.com.k19.modelo.CursoRepository;

@ManagedBean
public class DataTableBean {

	private List<Curso> cursosSelecionados;
	private List<Curso> cursosFiltrados;

	public List<Curso> getCursos() {
		return new CursoRepository().getCursos();
	}

	public List<Curso> getCursosSelecionados() {
		return cursosSelecionados;
	}

	public void setCursosSelecionados(List<Curso> cursosSelecionados) {
		this.cursosSelecionados = cursosSelecionados;
	}

	public List<Curso> getCursosFiltrados() {
		return cursosFiltrados;
	}

	public void setCursosFiltrados(List<Curso> cursosFiltrados) {
		this.cursosFiltrados = cursosFiltrados;
	}

}
