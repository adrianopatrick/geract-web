package com.implementsystem.geract.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.implementsystem.geract.entity.Entregas;
import com.implementsystem.geract.entity.Equipes;
import com.implementsystem.geract.entity.Notas;
import com.implementsystem.geract.services.NotaServiceRemote;

@Stateless(mappedName="ejb/NotaService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class NotaServiceImpl extends GenericServiceImpl<Notas> implements NotaServiceRemote{
	
	@SuppressWarnings("unchecked")
	public List<Notas> buscarPorEquipeEntrega(Equipes equipe, Entregas entrega){
		
		List<Notas> notas = new ArrayList<Notas>();
		String jpql = "select n from Notas n where n.equipe.id = :equipeId and n.entrega.id = :entregaId";
		
		Query query = em.createQuery(jpql);
		query.setParameter("equipeId", equipe.getId());
		query.setParameter("entregaId", entrega.getId());
		notas = (List<Notas>)query.getResultList();
		
		return notas;
		
	}

}
