package org.exemplo.persistencia.database.dao;

import java.util.List;

import org.exemplo.persistencia.database.model.Cliente;
import org.exemplo.persistencia.database.model.Conta;

public interface IEntityDAO <T>{

	public void save(T t);
	public T findById(Integer id);
	public List<T> findAll();
	public void update(T t);
	public void delete(T t);
	public Cliente findByCpf(String cpf);
	public Cliente findByCpf2(String cpf);
	public void closeSession();

}
