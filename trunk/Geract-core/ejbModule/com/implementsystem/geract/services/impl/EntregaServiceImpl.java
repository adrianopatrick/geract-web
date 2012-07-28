package com.implementsystem.geract.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.implementsystem.geract.entity.Entregas;
import com.implementsystem.geract.entity.Equipes;
import com.implementsystem.geract.services.EntregaServiceRemote;

@Stateless(mappedName="ejb/EntregaService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EntregaServiceImpl extends GenericServiceImpl<Entregas> implements EntregaServiceRemote{

	@SuppressWarnings("unchecked")
	public List<Entregas> buscarPorEquipe(Equipes equipe){
		
		List<Entregas> entregas = new ArrayList<Entregas>();
		String jpql = "select e from Entregas e where e.equipe.id = :equipeid order by e.dataMarcada";
		
		Query query = em.createQuery(jpql);
		query.setParameter("equipeid", equipe.getId());
		entregas = (List<Entregas>)query.getResultList();
		
		return entregas;
	}
}
