package model.dao;

import java.util.List;

import model.entidades.Departamento;

public interface DepartamentoDao {

	public void inserir(Departamento obj);
	public void atualizar(Departamento obj);
	public void deletarId(Integer id);
	Departamento buscarId(Integer Id);
	List<Departamento>buscarTodos();
	
	
}
