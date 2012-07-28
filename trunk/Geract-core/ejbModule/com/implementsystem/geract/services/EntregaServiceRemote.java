package com.implementsystem.geract.services;

import java.util.List;

import javax.ejb.Remote;

import com.implementsystem.geract.entity.Entregas;
import com.implementsystem.geract.entity.Equipes;

@Remote
public interface EntregaServiceRemote extends IServiceRemote<Entregas>{

	List<Entregas> buscarPorEquipe(Equipes equipe);
}
