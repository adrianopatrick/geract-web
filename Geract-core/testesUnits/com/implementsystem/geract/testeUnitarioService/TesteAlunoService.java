package com.implementsystem.geract.testeUnitarioService;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.implementsystem.geract.entity.Alunos;
import com.implementsystem.geract.entity.Equipes;
import com.implementsystem.geract.services.AlunoServiceRemote;
import com.implementsystem.geract.services.EquipeServiceRemote;
public class TesteAlunoService {

	InitialContext context;
	AlunoServiceRemote alunoService;
	EquipeServiceRemote equipeService;
	
	@Before
	public void unit() throws NamingException{
		context = new InitialContext();
		//alunoService = (AlunoServiceRemote) context.lookup("ejb/AlunoService");
		equipeService = (EquipeServiceRemote) context.lookup("ejb/EquipeService");
	}
	
	@Ignore
	public void testaInserir(){
		
		Equipes equipe = new Equipes();
		equipe.setNome("turma1");		
		
		Alunos aluno = new Alunos();
		aluno.setMatricula("12-1A");
		aluno.setNome("Patrick");
		
		equipe = equipeService.inserir(equipe);
		aluno.setEquipe(equipe);		
		aluno = alunoService.inserir(aluno);
		
	}
	
	@Test
	public void testaListar(){
		
		Equipes equipe = equipeService.buscarPorId(Equipes.class,1);
		System.out.println(equipe.getNome());
		for (Alunos aluno : equipe.getAlunos()) {
			System.out.println(aluno.getNome()+" "+aluno.getMatricula());
		}
	}
	
	@After
	public void finish() throws NamingException{
		context.close();
	}
}
