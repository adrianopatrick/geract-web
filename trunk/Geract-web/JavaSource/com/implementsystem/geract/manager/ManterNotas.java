package com.implementsystem.geract.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.implementsystem.geract.entity.Entregas;
import com.implementsystem.geract.entity.Equipes;
import com.implementsystem.geract.entity.Notas;
import com.implementsystem.geract.services.EntregaServiceRemote;
import com.implementsystem.geract.services.EquipeServiceRemote;
import com.implementsystem.geract.services.NotaServiceRemote;

@ViewScoped
@ManagedBean(name="manterNotas")
public class ManterNotas implements Serializable{

	private static final long serialVersionUID = 947123222522991313L;
	
	@EJB private EquipeServiceRemote equipeService;
	@EJB private EntregaServiceRemote entregaService;
	@EJB private NotaServiceRemote notaService;
	
	private Equipes equipeSelecionada = new Equipes();
	private Entregas entregaSelecionada = new Entregas();
	private List<Equipes> listEquipes = new ArrayList<Equipes>();
	private List<Entregas> listEntregas = new ArrayList<Entregas>();
	private List<Notas> listNotas = new ArrayList<Notas>();
	
	public void listaEntregasPorEquipe(AjaxBehaviorEvent event){
		listEntregas = new ArrayList<Entregas>();
		equipeSelecionada = equipeService.buscarPorId(Equipes.class, equipeSelecionada.getId());
		listEntregas.addAll(entregaService.buscarPorEquipe(equipeSelecionada));
	}
	
	public void listaNotasPorEquipeEntrega(AjaxBehaviorEvent event){
		entregaSelecionada = entregaService.buscarPorId(Entregas.class, entregaSelecionada.getId());
		listNotas = notaService.buscarPorEquipeEntrega(equipeSelecionada, entregaSelecionada);
	}
	
	public String atualizarNotas(){
		
		Boolean isValido = validar(listNotas);
		
		if(isValido){
		
			for (Notas nota : listNotas) {
				nota.setDataAlteracao(new Date());
				notaService.atualizar(nota);
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com Sucesso!", ""));
			return limpar();
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Soma das Notas maior que o valor determinado pela entrega!", ""));
			return null;
		}
		
	}
	
	public Boolean validar(List<Notas> notas){
		
		Boolean validado = true;
		Double somaNotas = 0.0;
		for (Notas nota : notas) {
			somaNotas += nota.getNota();
		}
		
		if(somaNotas > entregaSelecionada.getNota() * notas.size())
			validado = false;
		
		return validado;
	}
	
	public String limpar(){
		
		listEntregas = new ArrayList<Entregas>();
		equipeSelecionada = new Equipes();
		entregaSelecionada = new Entregas();
		
		return null;
	}
	
	public Equipes getEquipeSelecionada() {
		return equipeSelecionada;
	}
	public void setEquipeSelecionada(Equipes equipeSelecionada) {
		this.equipeSelecionada = equipeSelecionada;
	}
	public Entregas getEntregaSelecionada() {
		return entregaSelecionada;
	}
	public void setEntregaSelecionada(Entregas entregaSelecionada) {
		this.entregaSelecionada = entregaSelecionada;
	}
	public List<Equipes> getListEquipes() {
		if(listEquipes.isEmpty())
			listEquipes.addAll(equipeService.findAll(Equipes.class, false));
		return listEquipes;
	}
	public void setListEquipes(List<Equipes> listEquipes) {
		this.listEquipes = listEquipes;
	}
	public List<Entregas> getListEntregas() {
		return listEntregas;
	}
	public void setListEntregas(List<Entregas> listEntregas) {
		this.listEntregas = listEntregas;
	}
	public List<Notas> getListNotas() {
		return listNotas;
	}
	public void setListNotas(List<Notas> listNotas) {
		this.listNotas = listNotas;
	}
	
}
