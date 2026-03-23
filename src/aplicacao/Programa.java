package aplicacao;

import java.util.Date;

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
	}

}
