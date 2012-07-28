package com.implementsystem.geract.services;

import java.util.List;

import javax.ejb.Remote;

import com.implementsystem.geract.entity.Atividades;
import com.implementsystem.geract.entity.Entregas;

@Remote
public interface AtividadeServiceRemote extends IServiceRemote<Atividades>{

	List<Atividades> buscarPorEntrega(Entregas entrega);
}
