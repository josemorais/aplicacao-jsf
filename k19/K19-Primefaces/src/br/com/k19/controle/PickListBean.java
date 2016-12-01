package br.com.k19.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import br.com.k19.modelo.Curso;
import br.com.k19.modelo.CursoRepository;

@ManagedBean
public class PickListBean {

	private DualListModel<Curso> cursos;

	public PickListBean() {
		List<Curso> source = new CursoRepository().getCursos();
		List<Curso> target = new ArrayList<Curso>();

		this.cursos = new DualListModel<>(source, target);
	}

	public void onTransfer(TransferEvent event) {
		StringBuilder builder = new StringBuilder();

		for (Object obj : event.getItems()) {
			// Integer id = Integer.valueOf(obj.toString());
			// Curso c = this.cursos.getSource().get(id - 1);
			builder.append("1<br/>");
		}
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		if (event.isAdd()) {
			msg.setSummary("Cursos selecionados");
		} else {
			msg.setSummary("Cursos removidos");
		}
		msg.setDetail(builder.toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public DualListModel<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(DualListModel<Curso> cursos) {
		this.cursos = cursos;
	}

}
