package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entidades.Departamento;

public class DepartamentoDaoJDBC  implements DepartamentoDao{
	private Connection con;
	
	public DepartamentoDaoJDBC(Connection con) {
		this.con=con;
	}

	@Override
	public void inserir(Departamento obj) {
		PreparedStatement st=null;
		try {
			st=con.prepareStatement(
					"INSERT INTO department "
					+ "(Name) "
					+ "VALUES "	
					+ "(?)",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			int linhas = st.executeUpdate();
			if(linhas>0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id=rs.getInt(1);
					obj.setId(id);
					DB.closeResultSet(rs);
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
	public void atualizar(Departamento obj) {
		PreparedStatement st=null;
		try {
			st=con.prepareStatement(
					"UPDATE department "
					+ "SET Name = ? "
					+ "WHERE Id = ?");
			st.setString(1, obj.getNome());
			st.setInt(2, obj.getId());
			st.executeUpdate();		
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public void deletarId(Integer id) {
		PreparedStatement st=null;
		try {
			st=con.prepareStatement("DELETE FROM department WHERE Id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		}catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Departamento buscarId(Integer Id) {
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st=con.prepareStatement("SELECT * FROM department WHERE ID = ?");
			st.setInt(1, Id);
			rs= st.executeQuery();
			if(rs.next()) {
				Departamento dep= new Departamento();
				dep.setId(rs.getInt("Id"));
				dep.setNome(rs.getString("Name"));
				return dep;
			}
			return null;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage()); 
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
				
	}

	@Override
	public List<Departamento> buscarTodos() {
		PreparedStatement st= null;
		ResultSet rs=null;
		try {
			st=con.prepareStatement("SELECT * FROM department ORDER BY Name ");
			rs=st.executeQuery();
			List<Departamento>list= new ArrayList<>();
			while (rs.next()) {
				Departamento dep= new Departamento();
				dep.setId(rs.getInt("Id"));
				dep.setNome(rs.getString("Name"));
				list.add(dep);
			}
			return list;
					
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
				
	}

}
