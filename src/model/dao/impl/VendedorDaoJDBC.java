package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendedoresDao;
import model.entidades.Departamento;
import model.entidades.Vendedores;

public class VendedorDaoJDBC implements VendedoresDao {
	
	private Connection con;
	
	

	public VendedorDaoJDBC(Connection con) {
		this.con = con;
	}

	@Override
	public void inserir(Vendedores obj) {
		
		
	}

	@Override
	public void atualizar(Vendedores obj) {
		
		
	}

	@Override
	public void deletarId(Integer id) {
		
		
	}

	@Override
	public Vendedores buscarId(Integer id) {
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st=con.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs= st.executeQuery();
			if(rs.next()) {
				Departamento dep = instanciaDepartamento(rs);
				Vendedores ven= instaciaVendedor(rs,dep);
				return ven;
			}
			return null;
			
			}
		     catch(SQLException e){
				throw new DbException(e.getMessage());						
					
		}finally{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	private Vendedores instaciaVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedores ven=new Vendedores();
		ven.setId(rs.getInt("Id"));
		ven.setNome(rs.getString("Name"));
		ven.setEmail(rs.getString("Email"));
		ven.setBaseSalarial(rs.getDouble("BaseSalary"));
		ven.setAniversario(rs.getDate("BirthDate"));
		ven.setDepartamento(dep);
		return ven;
	}

	private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {
		Departamento dep=new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Vendedores> buscarTodos() {
		
		return null;
	}

}
