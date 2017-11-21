package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBHelper;
import bean.AccessToken;
import dao.AccessTokenDao;

public class AccessTokenDaoImpl implements AccessTokenDao {

	@Override
	public AccessToken seach() {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet= null;
		AccessToken accessToken=null;
		try{
			connection=DBHelper.getConnection();
			String sql="select * from  accesstoken where id=1";
			statement =connection.prepareStatement(sql);
			resultSet=statement.executeQuery();
			if(resultSet.next()){
				accessToken =new AccessToken();
				accessToken.setId(resultSet.getInt("id"));
				accessToken.setAccess_token(resultSet.getString("access_token"));
				accessToken.setData_time(resultSet.getString("data_time"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeALL(connection, statement);
		}
		
		return accessToken;
	}

	@Override
	public boolean update(String access_token,String data_time) {
		Connection connection=null;
		PreparedStatement statement=null;
		boolean result=false;
		try{
			connection = DBHelper.getConnection();
			String sql="update accesstoken set access_token='"+access_token+"',data_time='"+data_time+"' where id=1";
			statement=connection.prepareStatement(sql);			
			int row=statement.executeUpdate();
			if(row>0){
				result=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeALL(connection, statement);
		}
		return result;
	}

}
