package db;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	private  static Connection con = null;
    public static Connection getConnection() {
    	if (con==null) {
       try {
    	  Properties pros= loadProperties();
    	  String url= pros.getProperty("dburl");
    	  con=DriverManager.getConnection(url,pros);
       }catch(SQLException e){
    	   throw new DbException(e.getMessage());
       }
    	}
    	return con;
    }
    public static void CloseConnection() {
    	if(con!=null) {
    		try {
    			con.close();
    		}catch(SQLException e) {
    			throw new DbException(e.getMessage());
    		}
    		
    	}
    }

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties pro = new Properties();
			pro.load(fs);
			return pro;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
    public static void closeStatement(Statement st) {
    	if(st!=null) {
   	      try {
			st.close();
		  } catch (SQLException e) {
			throw new DbException(e.getMessage());
		  }
   	      
    	}
    }
    
    public static void closeResultSet(ResultSet rs) {
    	if(rs!=null) {
    		try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
    	}
    }
}

