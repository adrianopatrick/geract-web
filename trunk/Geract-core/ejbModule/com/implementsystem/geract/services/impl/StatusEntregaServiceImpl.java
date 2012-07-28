package com.implementsystem.geract.services.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.implementsystem.geract.entity.StatusEntrega;
import com.implementsystem.geract.services.StatusEntregaService;

@Stateless(mappedName="ejb/StatusEntregaService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class StatusEntregaServiceImpl extends GenericServiceImpl<StatusEntrega> implements StatusEntregaService{

}
