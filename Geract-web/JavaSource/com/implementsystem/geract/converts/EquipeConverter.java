package com.implementsystem.geract.converts;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.implementsystem.geract.entity.Equipes;
import com.implementsystem.geract.services.EquipeServiceRemote;

public class EquipeConverter implements Converter{

	private EquipeServiceRemote equipeService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		try {
			InitialContext con = new InitialContext();
			equipeService = (EquipeServiceRemote) con.lookup("ejb/EquipeService");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		Equipes eq = new Equipes();
		eq.setNome(value);
		Equipes equipe = equipeService.example(eq, false).get(0);
		
		return equipe;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return ((Equipes)value).getNome();
	}

}
