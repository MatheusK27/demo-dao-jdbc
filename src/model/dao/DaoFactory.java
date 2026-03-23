package model.dao;

import db.DB;
import model.dao.impl.VendedorDaoJDBC;

public class DaoFactory {

	public static VendedoresDao criarVendedoresDao() {
		return new VendedorDaoJDBC(DB.getConnection());
	}
	
}
