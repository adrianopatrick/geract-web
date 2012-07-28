package com.implementsystem.geract.services.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.implementsystem.geract.entity.Equipes;
import com.implementsystem.geract.services.EquipeServiceRemote;


@Stateless(mappedName="ejb/EquipeService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EquipeServiceImpl extends GenericServiceImpl<Equipes> implements EquipeServiceRemote{

	
}
