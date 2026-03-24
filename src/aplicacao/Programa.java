package aplicacao;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedoresDao;
import model.entidades.Departamento;
import model.entidades.Vendedores;

public class Programa {

	public static void main(String[] args) {
		
        VendedoresDao vendedoresDao= DaoFactory.criarVendedoresDao();
        System.out.println("===TEST 1: VENDEDOR ENCONTRADO ===");
        Vendedores vendedores= vendedoresDao.buscarId(3);
        System.out.println(vendedores);
        
        System.out.println("===TEST 2: VENDEDOR ENCONTRADO ===");
        Departamento dep= new Departamento(2, null);
        List<Vendedores>list= vendedoresDao.buscarPorDepartamento(dep);
        for(Vendedores ven:list) {
        	System.out.println(ven);
        }
        
        System.out.println("===TEST 3: BUSCAR TODOS ===");
        list= vendedoresDao.buscarTodos(); 
        for(Vendedores ven:list) {
        	System.out.println(ven);
        }
	}

}
