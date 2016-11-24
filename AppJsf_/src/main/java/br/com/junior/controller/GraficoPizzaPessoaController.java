package br.com.junior.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.junior.repository.PessoaRepository;

@Named
@RequestScoped
public class GraficoPizzaPessoaController {

	@Inject
	private PessoaRepository pessoaRepository;

	private PieChartModel pieChartModel;

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	@PostConstruct
	public void init() {
		this.pieChartModel = new PieChartModel();
		this.montarGrafico();
	}

	private void montarGrafico() {
		// TODO Auto-generated method stub
		Hashtable<String, Integer> hashtable = this.pessoaRepository.getOrigemPessoa();

		hashtable.forEach((chave, valor) -> {
			pieChartModel.set(chave, valor);
		});
		
		pieChartModel.setTitle("Total de pessoas cadastradas por tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");

	}
}
