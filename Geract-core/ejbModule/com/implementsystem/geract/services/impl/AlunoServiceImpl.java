package com.implementsystem.geract.services.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.implementsystem.geract.entity.Alunos;
import com.implementsystem.geract.services.AlunoServiceRemote;

@Stateless(mappedName="ejb/AlunoService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AlunoServiceImpl extends GenericServiceImpl<Alunos> implements AlunoServiceRemote{

	
}
