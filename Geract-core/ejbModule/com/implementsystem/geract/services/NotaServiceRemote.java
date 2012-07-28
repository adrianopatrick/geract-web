package com.implementsystem.geract.services;

import java.util.List;

import javax.ejb.Remote;

import com.implementsystem.geract.entity.Entregas;
import com.implementsystem.geract.entity.Equipes;
import com.implementsystem.geract.entity.Notas;

@Remote
public interface NotaServiceRemote extends IServiceRemote<Notas>{
	
	List<Notas> buscarPorEquipeEntrega(Equipes equipe, Entregas entrega);

}
