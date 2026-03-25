package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;	
import model.dao.DaoFactory;
import model.dao.VendedoresDao;
import model.entidades.Departamento;
import model.entidades.Vendedores;

public class Programa {

	public static void main(String[] args) throws ParseException {
	    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
	    Scanner sc= new Scanner(System.in);
		
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
        		"Matheus@hotmail.com",sdf.parse("27/02/1995"),4000.0, dep);
        vendedoresDao.inserir(novoVendedor);
        System.out.println("Novo vendedor = "+ novoVendedor.getId());
        
        
        System.out.println("===TEST 5 : ATUALIZAR VENDEDOR ===");
        vendedores = vendedoresDao.buscarId(19);
        vendedores.setBaseSalarial(5000.00);
        vendedoresDao.atualizar(vendedores);
        
        System.out.println("Att concluída:  "+ vendedores.getBaseSalarial());
        
        System.out.println("===TEST 6 : DELETE ID ===");
        System.out.println("Digite id pra ser deletado: ");
        int id= sc.nextInt();
        vendedoresDao.deletarId(id);
        System.out.println("ID DELETADO COM SUCESSO!");
       
        sc.close();
	}
        
	
	
	
}
