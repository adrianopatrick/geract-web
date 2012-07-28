package com.implementsystem.geract.services;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IServiceRemote<T> {

	T inserir(T entity);
	T atualizar(T entity);
	void excluir(T entity);
	T buscarPorId(Class<T> modelClass, Integer id);
	List<T> example(T bean, Boolean initialize);
	List<T> findAll(Class<T> modelClass, Boolean initialize);
	
}
