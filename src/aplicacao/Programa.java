package aplicacao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedoresDao;
import model.entidades.Departamento;
import model.entidades.Vendedores;

public class Programa {

	public static void main(String[] args) {
	    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		
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
        
        System.out.println("===TEST 4 : INSERIR VENDEDOR ===");
        Vendedores novoVendedor = new Vendedores(null, "Matheus", 
        		"Matheus@hotmail.com",new Date(),4000.0, dep);
        vendedoresDao.inserir(novoVendedor);
        System.out.println("Novo vendedor = "+ novoVendedor.getId());
	}

}
