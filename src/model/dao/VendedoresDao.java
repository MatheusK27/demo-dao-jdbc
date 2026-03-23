package model.dao;

import java.util.List;

import model.entidades.Departamento;
import model.entidades.Vendedores;

public interface VendedoresDao {
	public void inserir(Vendedores obj);
	public void atualizar(Vendedores obj);
	public void deletarId(Integer id);
	Vendedores buscarId(Integer Id);
	List<Vendedores>buscarTodos();
	List<Vendedores>buscarPorDepartamento(Departamento departamento);
	
}
