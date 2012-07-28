package com.implementsystem.geract.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.implementsystem.geract.entity.Atividades;
import com.implementsystem.geract.entity.Entregas;
import com.implementsystem.geract.entity.StatusAtividade;
import com.implementsystem.geract.entity.enums.TipoAtividade;
import com.implementsystem.geract.services.AtividadeServiceRemote;
import com.implementsystem.geract.services.StatusAtividadeService;

@SessionScoped
@ManagedBean(name="atividadeManager")
public class AtividadeManager {
	
	@EJB
	private AtividadeServiceRemote atividadeService;
	@EJB
	private StatusAtividadeService statusAtividadeService;
	
	private Atividades atividade = new Atividades();
	private List<Atividades> atividades = new ArrayList<Atividades>();
	private Boolean atualizar = false;
	private Entregas entrega = new Entregas();
	
	public String preparaPesquisar(){
		limpar();
		atividade.setEntrega(entrega);
		atividades = atividadeService.buscarPorEntrega(entrega);
		return "listAtividade";
	}
	
	public String preparaInserir(){
		limpar();
		atualizar = false;
		return "formAtividade";
	}
	
	public String preparaAtualizar(){
		atualizar = true;
		return "formAtividade";
	}
	
	public String inserir(){
		StatusAtividade status = statusAtividadeService.buscarPorId(StatusAtividade.class, 1);
		atividade.setStatus(status);
		atividade.setEntrega(entrega);
		atividadeService.inserir(atividade);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserido com sucesso!", ""));
		return preparaPesquisar();
	}
	
	public String atualizar(){
		
		if(atividade.getPontuacao() == null || atividade.getPontuacao() == 0.0){
			StatusAtividade status = statusAtividadeService.buscarPorId(StatusAtividade.class, 3);
			atividade.setStatus(status);
		} else {
			StatusAtividade status = statusAtividadeService.buscarPorId(StatusAtividade.class, 2);
			atividade.setStatus(status);
		}
			
		atividade = atividadeService.atualizar(atividade);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com Sucesso!", ""));
		return preparaPesquisar();
		
	}
	
	public String excluir(){
		
		try {
			atividadeService.excluir(atividade);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Removido com Sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não possível remover, verifique as dependências", ""));
			e.printStackTrace();
		}
		
		return preparaPesquisar();
	}
	
	public void limpar(){
		atividade = new Atividades();
		atividades = new ArrayList<Atividades>();
	}
	
	public Atividades getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividades atividade) {
		this.atividade = atividade;
	}
	public List<Atividades> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividades> atividades) {
		this.atividades = atividades;
	}
	public Boolean getAtualizar() {
		return atualizar;
	}
	public void setAtualizar(Boolean atualizar) {
		this.atualizar = atualizar;
	}
	public Entregas getEntrega() {
		return entrega;
	}
	public void setEntrega(Entregas entrega) {
		this.entrega = entrega;
	}
	public SelectItem[] getTiposAtividade() {
		
		SelectItem[] items = new SelectItem[TipoAtividade.values().length];
		int i = 0;
		for (TipoAtividade tipo : TipoAtividade.values()) {
			items[i++] = new SelectItem(tipo, tipo.getDescricao());
		}
		
		return items;
	}

}
