package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;


public class DBHelper {
	private static final String DRIVICE="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_yuepark";
	private static final String USER="3k0mk2xnj1";
	private static final String PWD="j2502zhm0mk5y30yk3h0120i0j1142335hizhk14";
	
	public static Connection getConnection(){
		
		Connection connection = null;
		try{
			Class.forName(DRIVICE).newInstance();
			connection=DriverManager.getConnection(URL,USER,PWD);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;
	}
  public static void closeALL(Connection connection,Statement statement){
	  if(null!=statement){
		  try{
			  statement.close();
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
	  }
	  if(null != connection){
		  try{
			  connection.close();
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
	  }
	  
  }
}
