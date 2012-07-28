package com.implementsystem.geract.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.implementsystem.geract.entity.Entregas;
import com.implementsystem.geract.entity.Equipes;
import com.implementsystem.geract.entity.StatusEntrega;
import com.implementsystem.geract.services.EntregaServiceRemote;
import com.implementsystem.geract.services.StatusEntregaService;

@SessionScoped
@ManagedBean(name="entregaManager")
public class EntregaManager {
	
	@EJB
	private EntregaServiceRemote entregaService;
	@EJB
	private StatusEntregaService statusService;
	
	private Entregas entrega = new Entregas();
	private List<Entregas> entregas = new ArrayList<Entregas>();
	private Boolean atualizar = false;
	private Equipes equipe = new Equipes();
	
	public String preparaPesquisar(){
		limpar();
		entrega.setEquipe(equipe);
		entregas = entregaService.buscarPorEquipe(equipe);
		return "listEntrega";
	}
	
	public String preparaInserir(){
		limpar();
		atualizar = false;
		return "formEntrega";
	}
	
	public String preparaAtualizar(){
		
		atualizar = true;
		return "formEntrega";
	}
	
	public String inserir(){
		StatusEntrega status = statusService.buscarPorId(StatusEntrega.class, 1);
		entrega.setStatus(status);
		entrega.setEquipe(equipe);
		entregaService.inserir(entrega);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserido com sucesso!", ""));
		return preparaPesquisar();
	}
	
	public String atualizar(){
		
		entrega = entregaService.atualizar(entrega);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com Sucesso!", ""));
		return preparaPesquisar();
	}
	
	public String excluir(){
		
		try {
			entregaService.excluir(entrega);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Removido com Sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não possível remover, verifique as dependências", ""));
			e.printStackTrace();
		}
		
		return preparaPesquisar();
	}
	
	public void limpar(){
		entrega = new Entregas();
		entregas = new ArrayList<Entregas>();
	}

	public Entregas getEntrega() {
		return entrega;
	}
	public void setEntrega(Entregas entrega) {
		this.entrega = entrega;
	}
	public List<Entregas> getEntregas() {
		return entregas;
	}
	public void setEntregas(List<Entregas> entregas) {
		this.entregas = entregas;
	}
	public Boolean getAtualizar() {
		return atualizar;
	}
	public void setAtualizar(Boolean atualizar) {
		this.atualizar = atualizar;
	}
	public Equipes getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipes equipe) {
		this.equipe = equipe;
	}
	
}
