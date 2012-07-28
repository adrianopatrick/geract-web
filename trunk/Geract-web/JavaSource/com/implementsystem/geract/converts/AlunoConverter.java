package com.implementsystem.geract.converts;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.implementsystem.geract.entity.Alunos;
import com.implementsystem.geract.services.AlunoServiceRemote;

public class AlunoConverter implements Converter{

	private AlunoServiceRemote alunoService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		try {
			InitialContext con = new InitialContext();
			alunoService = (AlunoServiceRemote) con.lookup("ejb/AlunoService");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		Alunos al = new Alunos();
		al.setNome(value);
		Alunos aluno = alunoService.example(al,false).get(0);
		
		return aluno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return ((Alunos)value).getNome();
	}

}
