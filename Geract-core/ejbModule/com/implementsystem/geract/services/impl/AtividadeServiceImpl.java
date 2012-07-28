package com.implementsystem.geract.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.implementsystem.geract.entity.Atividades;
import com.implementsystem.geract.entity.Entregas;
import com.implementsystem.geract.services.AtividadeServiceRemote;


@Stateless(mappedName="ejb/AtividadeService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AtividadeServiceImpl extends GenericServiceImpl<Atividades> implements
		AtividadeServiceRemote {
	
	@SuppressWarnings("unchecked")
	public List<Atividades> buscarPorEntrega(Entregas entrega){
		
		List<Atividades> atividades = new ArrayList<Atividades>();
		String jpql = "select a from Atividades a where a.entrega.id = :entregaId order by a.id";
		
		Query query = em.createQuery(jpql);
		query.setParameter("entregaId", entrega.getId());
		atividades = (List<Atividades>)query.getResultList();
		
		return atividades;
	}

}
