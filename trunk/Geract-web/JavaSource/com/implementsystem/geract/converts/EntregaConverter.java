package com.implementsystem.geract.converts;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.implementsystem.geract.entity.Entregas;
import com.implementsystem.geract.services.EntregaServiceRemote;

public class EntregaConverter implements Converter {

	private EntregaServiceRemote entregaService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		try {
			InitialContext con = new InitialContext();
			entregaService = (EntregaServiceRemote) con.lookup("ejb/EntregaService");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		Entregas entr = new Entregas();
		entr.setDescricao(value);
		Entregas entrega = entregaService.example(entr, false).get(0);
		
		return entrega;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return ((Entregas)value).getDescricao();
	}

}
