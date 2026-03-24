package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



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
		PreparedStatement st=null;
		try {
			st=con.prepareStatement(
					"INSERT INTO seller "
					+ "(Name,Email,BirthDate,BaseSalary,DepartmentId) "
					+ "VALUES "
					+ "(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
			st.setDouble(4, obj.getBaseSalarial());
			st.setInt(5, obj.getDepartamento().getId());
			
			int linhas = st.executeUpdate();
		
			if(linhas> 0) {
				ResultSet rs= st.getGeneratedKeys();
				if(rs.next()) {
					int id= rs.getInt(1);
					obj.setId(id);
					DB.closeResultSet(rs);
				}
				else{
					throw new DbException("Error. Nenhuma linha foi alterada!"); 
				}
				
				
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			
		}
		
		
	}

	@Override
	public void atualizar(Vendedores obj) {
		PreparedStatement st=null;
		try {
			st=con.prepareStatement(
					"UPDATE seller  "
					+ "SET Name = ? , Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE Id = ? ");
					
					
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
			st.setDouble(4, obj.getBaseSalarial());
			st.setInt(5, obj.getDepartamento().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();
		}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
				
			}
		
	}

	@Override
	public void deletarId(Integer id) {
		PreparedStatement st= null;
		
		try {
			st=con.prepareStatement(
					"DELETE FROM seller "
					+ "WHERE Id = ?");
			st.setInt(1, id);
			st.executeUpdate();
					
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
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
			
			/*executeQuery() executa a consulta no SQL e me devolve um ResultSet*/
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
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st=con.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name");
			
			rs= st.executeQuery();
			
			List<Vendedores>list= new ArrayList<>();
			
			Map<Integer,Departamento> map = new HashMap<>();
			
			while(rs.next()) {
				 
				/*aqui ele vai verificar se ja existe a chave no departamentID
				  se tiver, ele reutiliza */
		         Departamento dep=map.get(rs.getInt("DepartmentId"));
				
				if(dep==null) {
					
					dep = instanciaDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Vendedores ven= instaciaVendedor(rs,dep);
					
				list.add(ven);
				
				
			}
			return list;
			
			}
		     catch(SQLException e){
				throw new DbException(e.getMessage());						
					
		}finally{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Vendedores> buscarPorDepartamento(Departamento departamento) {
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st=con.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE Department.Id = ? "
					+ "ORDER BY Name");
			st.setInt(1, departamento.getId());
			
			rs= st.executeQuery();
			
			List<Vendedores>list= new ArrayList<>();
			
			Map<Integer,Departamento> map = new HashMap<>();
			
			while(rs.next()) {
				 
				/*aqui ele vai verificar se ja existe a chave no departamentID
				  se tiver, ele reutiliza */
		         Departamento dep=map.get(rs.getInt("DepartmentId"));
				
				if(dep==null) {
					
					dep = instanciaDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Vendedores ven= instaciaVendedor(rs,dep);
					
				list.add(ven);
				
				
			}
			return list;
			
			}
		     catch(SQLException e){
				throw new DbException(e.getMessage());						
					
		}finally{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
