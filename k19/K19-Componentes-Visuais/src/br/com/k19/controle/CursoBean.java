package br.com.k19.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.k19.modelo.Curso;

@ManagedBean
@SessionScoped
public class CursoBean {

	private List<Curso> cursos = new ArrayList<Curso>();
	private Curso curso = new Curso();

	public void adiciona() {
		this.cursos.add(this.curso);
		FacesMessage message = new FacesMessage("Curso adicionado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		this.curso = new Curso();
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
