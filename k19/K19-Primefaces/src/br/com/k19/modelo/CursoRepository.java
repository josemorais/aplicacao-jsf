package br.com.k19.modelo;

import java.util.ArrayList;
import java.util.List;

public class CursoRepository {

	private static List<Curso> cursos = new ArrayList<Curso>();

	static {
		cursos.add(new Curso(1L, "K01", "L�gicadePrograma��o", "http://www.k19.com.br/css/img/k01-logo-small.png"));

		cursos.add(new Curso(2L, "K02", "DesenvolvimentoWebcomHTML,CSSeJavaScript",
				"http://www.k19.com.br/css/img/k02-logo-small.png"));

		cursos.add(new Curso(3L, "K03", "SQLeModeloRelacional", "http://www.k19.com.br/css/img/k03-logo-small.png"));

		cursos.add(
				new Curso(4L, "K11", "Orienta��oaObjetosemJava", "http://www.k19.com.br/css/img/k11-logo-small.png"));
		cursos.add(new Curso(5L, "K12", "DesenvolvimentoWebcomJSF2eJPA2",
				"http://www.k19.com.br/css/img/k12-logo-small.png"));

		cursos.add(new Curso(6L, "K21", "Persist�nciacomJAP2eHibernate",
				"http://www.k19.com.br/css/img/k21-logo-small.png"));

		cursos.add(new Curso(7L, "K22", "DesenvolvimentoWebAvan�adocomEJB,JSFeCDI",
				"http://www.k19.com.br/css/img/k22-logo-small.png"));

		cursos.add(new Curso(8L, "K23", "Integra��odeSistemascomWebservices,JMSeEJB",
				"http://www.k19.com.br/css/img/k23-logo-small.png"));

		cursos.add(new Curso(9L, "K31", "C#eOrienta��oaObjetos", "http://www.k19.com.br/css/img/k31-logo-small.png"));

		cursos.add(new Curso(10L, "K32", "DesenvolvimentoWebcomASP.NETMVC",
				"http://www.k19.com.br/css/img/k32-logo-small.png"));

		cursos.add(new Curso(11L, "K41", "DesenvolvimentoMobilecomAndroid",
				"http://www.k19.com.br/css/img/k41-logo-small.png"));

		cursos.add(new Curso(12L, "K51", "DesignPatternsemJava", "http://www.k19.com.br/css/img/k51-logo-small.png"));
		cursos.add(new Curso(13L, "K52", "DesenvolvimentoWebcomStruts",
				"http://www.k19.com.br/css/img/k52-logo-small.png"));
	}

	public List<Curso> getCursos() {
		return cursos;
	}

}
