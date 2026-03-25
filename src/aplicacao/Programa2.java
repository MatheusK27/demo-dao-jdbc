package aplicacao;


import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entidades.Departamento;

public class Programa2 {

	public static void main(String[] args) {
		
		DepartamentoDao departamentoDao = DaoFactory.criarDepartamento();
		
		System.out.println("===TESTE 1 INSERIR DEPARTAMENTO ===");
		Departamento novoDep= new Departamento(null, "Limpeza");
		departamentoDao.inserir(novoDep);
		System.out.println("Departamento Criado, id e nome:  " +novoDep);
		
		System.out.println("===TESTE 2 ATUALIZAR DEPARTAMENTO ===");
		Departamento dep = departamentoDao.buscarId(7);
		dep.setNome("Limpeza2");
		departamentoDao.atualizar(dep);
		System.out.println("Att. Realizadda com sucesso! ");
		
		System.out.println("===TESTE 3 ATUALIZAR DEPARTAMENTO ===");
	    dep = new Departamento(7, "Limpeza geral");
	    departamentoDao.atualizar(dep);
	    System.out.println("Att. Realizadda com sucesso! ");
		
	    System.out.println("===TESTE 4 DELETAR DEPARTAMENTO ===");
	    departamentoDao.deletarId(8);
	    System.out.println("Departamento DELETADO");
	    
	    System.out.println("===TESTE 5 BUSCAR TODOS===");
	    for(Departamento departamento:departamentoDao.buscarTodos()) {
	    	System.out.println(departamento);
	  
	    }
	    System.out.println("Busca Concluida"); 	
	}

}
