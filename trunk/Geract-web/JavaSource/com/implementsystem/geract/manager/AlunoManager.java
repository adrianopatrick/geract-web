package com.implementsystem.geract.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.implementsystem.geract.entity.Alunos;
import com.implementsystem.geract.services.AlunoServiceRemote;

@SessionScoped
@ManagedBean(name="alunoManager")
public class AlunoManager implements Serializable{

	private static final long serialVersionUID = -8341426314680970142L;
	
	@EJB
	private AlunoServiceRemote alunoService;
	private Alunos aluno = new Alunos();
	private List<Alunos> alunos = new ArrayList<Alunos>();
	private Boolean retornou = true;
	private Boolean atualizar = false;
	
	public String preparePesquisar(){
		limpar();
		alunos = alunoService.findAll(Alunos.class, false);
		
		return "listAlunos";
	}
	
	public String preparaInserir(){
		limpar();
		atualizar = false;
		return "formAluno";
	}
	
	public String preparaAlterar(){
		
		//aluno = alunoService.example(aluno, false).get(0);
		atualizar = true;
		
		return "formAluno";
	} 
	
	public void limpar(){
		aluno = new Alunos();
		alunos = new ArrayList<Alunos>();
	}
	
	public String inserir(){
		
		alunoService.inserir(aluno);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserido com sucesso!", ""));
		
		return preparePesquisar();
	}
	
	public String atualizar(){
		
		aluno = alunoService.atualizar(aluno);
		alunos = alunoService.findAll(Alunos.class, false);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", ""));
		
		return preparePesquisar();
	}
	
	public String excluir(){
		try{
			alunoService.excluir(aluno);
			alunos.remove(aluno);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com sucesso!", ""));
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não foi possível excluir, verifique as dependências.", ""));
		}
		return preparePesquisar();
	} 
	
	public String pesquisar(){
		
		if(aluno != null)
			alunos = alunoService.example(aluno, false);
		else
			alunos = alunoService.findAll(Alunos.class, false);
		
		if(alunos == null || alunos.isEmpty())
			retornou = false;
		else
			retornou = true;
		
		return "listAlunos";
	}

	
	/**getts and setts**/
	public Alunos getAluno() {
		return aluno;
	}

	public void setAluno(Alunos aluno) {
		this.aluno = aluno;
	}

	public List<Alunos> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Alunos> alunos) {
		this.alunos = alunos;
	}

	public AlunoServiceRemote getService() {
		return alunoService;
	}

	public void setService(AlunoServiceRemote service) {
		this.alunoService = service;
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

	public void setAtualizar(Boolean altera) {
		this.atualizar = altera;
	}
	
}
