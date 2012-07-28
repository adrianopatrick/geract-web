package com.implementsystem.geract.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;

import com.implementsystem.geract.entity.Alunos;
import com.implementsystem.geract.entity.Equipes;
import com.implementsystem.geract.services.AlunoServiceRemote;
import com.implementsystem.geract.services.EquipeServiceRemote;

@SessionScoped
@ManagedBean(name="equipeManager")
public class EquipeManager implements Serializable{

	private static final long serialVersionUID = 2497876317105496668L;
	
	@EJB
	private EquipeServiceRemote equipeService;
	@EJB
	private AlunoServiceRemote alunoService;
	
	private Equipes equipe = new Equipes();
	private List<Equipes> equipes = new ArrayList<Equipes>();
	private Boolean retornou = false;
	private Boolean atualizar = false;
	private DualListModel<Alunos> membros = new DualListModel<Alunos>();
	
	public String preparaPesquisar(){
		
		limpar();
		equipes = equipeService.findAll(Equipes.class, false);
		if(!equipes.isEmpty())
			retornou = true;
		else
			retornou = false;
		
		return "listEquipes";
	}
	
	public String preparaInserir(){
		limpar();
		atualizar = false;
		return "formEquipe";
	}
	
	public String preparaAtualizar(){
		
		atualizar = true;
		return "formEquipe";
	}
	
	public String inserir(){
		
		equipeService.inserir(equipe);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserido com sucesso!", ""));
		
		return preparaPesquisar();
		
	}
	
	public String pesquisar(){
		
		equipes = equipeService.example(equipe, false);
		if(!equipes.isEmpty())
			retornou = true;
		else
			retornou = false;
		
		return "listEquipes";
	}
	
	public String atualizar(){
		
		equipe = equipeService.atualizar(equipe);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com Sucesso!", ""));
		
		return preparaPesquisar();
	}
	
	public String excluir(){
		
		try{
			equipe = equipeService.example(equipe, false).get(0);
			equipeService.excluir(equipe);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Removido com Sucesso!", ""));
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não possível remover, verifique as dependências", ""));
			e.printStackTrace();
		}
		return preparaPesquisar();
	}

	public void limpar(){
		equipe = new Equipes();
		equipes = new ArrayList<Equipes>();
	}
	
	
	public String preparaMembros(){
		
		equipe = equipeService.buscarPorId(Equipes.class, equipe.getId());
		
		List<Alunos> todos = alunoService.findAll(Alunos.class, false);
		List<Alunos> source = new ArrayList<Alunos>();
		//garante que somente serão mostrados alunos que não tiverem alocados
		for (Alunos aluno : todos) {
			if(aluno.getEquipe() == null)
				source.add(aluno);
		}
		
		List<Alunos> target = equipe.getAlunos();
		
		membros = new DualListModel<Alunos>(source, target);
		
		return "manterEquipeAluno";
		
	}
	
	public String salvarMembros(){
		
		
		for (Alunos aluno : equipe.getAlunos()) {
			aluno.setEquipe(null);
			alunoService.atualizar(aluno);
		}
		
		for (Alunos aluno : membros.getTarget()) {
			equipe.getAlunos().add(aluno);
			aluno.setEquipe(equipe);
			alunoService.atualizar(aluno);
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Membros atualizados com sucesso!", ""));
		
		return "manterEquipeAluno";
	}
	
	public EquipeServiceRemote getEquipeService() {
		return equipeService;
	}

	public void setEquipeService(EquipeServiceRemote equipeService) {
		this.equipeService = equipeService;
	}

	public Equipes getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipes equipe) {
		this.equipe = equipe;
	}

	public List<Equipes> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipes> equipes) {
		this.equipes = equipes;
	}

	public Boolean getRetornou() {
		return retornou;
	}

	public void setRetornou(Boolean retornou) {
		this.retornou = retornou;
	}

	public Boolean getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(Boolean atualizar) {
		this.atualizar = atualizar;
	}

	public DualListModel<Alunos> getMembros() {
		return membros;
	}

	public void setMembros(DualListModel<Alunos> membros) {
		this.membros = membros;
	}

}
