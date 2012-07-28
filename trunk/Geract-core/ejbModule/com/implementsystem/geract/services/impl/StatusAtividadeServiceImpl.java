package com.implementsystem.geract.services.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.implementsystem.geract.entity.StatusAtividade;
import com.implementsystem.geract.services.StatusAtividadeService;

@Stateless(mappedName="ejb/StatusAtividadeService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class StatusAtividadeServiceImpl extends GenericServiceImpl<StatusAtividade> implements StatusAtividadeService{

}
