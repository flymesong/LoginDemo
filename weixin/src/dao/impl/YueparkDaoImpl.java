package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.DBHelper;
import bean.Yuepark;
import dao.YueparkDao;

public class YueparkDaoImpl implements YueparkDao {

	/**
	 * 根据openID查yuepark对象
	 */
	@Override
	public Yuepark seach(String openid) {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet= null;
		Yuepark yuepark=null;
		try{
			connection=DBHelper.getConnection();
			String sql="select * from  yuepark where openid='"+openid+"'";
			statement =connection.prepareStatement(sql);
			resultSet=statement.executeQuery();
			if(resultSet.next()){
				yuepark =new Yuepark();
				yuepark.setId(resultSet.getInt("id"));
				yuepark.setOpenid(resultSet.getString("openid"));
				yuepark.setImgurl(resultSet.getString("imgurl"));
				yuepark.setPhone(resultSet.getString("phone"));
				yuepark.setPassword(resultSet.getString("password"));
				yuepark.setAccesstoken(resultSet.getString("accesstoken"));
				yuepark.setAddtime(resultSet.getString("addtime"));
				yuepark.setOpenid_xiao(resultSet.getString("openid_xiao"));
				yuepark.setUnionid(resultSet.getString("unionid"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeALL(connection, statement);
		}
		
		return yuepark;
	}

	/**
	 * 更新yuepark
	 */
	@Override
	public boolean update(Yuepark yuepark){
		
		Connection connection=null;
		PreparedStatement statement=null;
		boolean result=false;
		try{
			connection = DBHelper.getConnection();
			String sql="update yuepark set accesstoken='"+yuepark.getAccesstoken()+"',imgurl='"+yuepark.getImgurl()+"',openid='"+yuepark.getOpenid()+"',password='"+yuepark.getPassword()+"',addtime='"+yuepark.getAddtime()+"',unionid='"+yuepark.getUnionid()+"' where phone='"+yuepark.getPhone()+"'";
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

	/**
	 * 添加yuepark
	 */
	@Override
	public int Addopenid(Yuepark yuepark) {
		Connection connection=null;
		PreparedStatement statement=null;
		
		int row=0;
		try{
			connection=DBHelper.getConnection();
			String sql="insert into yuepark(openid,imgurl,phone,password,accesstoken,addtime,openid_xiao,unionid) values('"+yuepark.getOpenid()+"','"+yuepark.getImgurl()+"','"+yuepark.getPhone()+"','"+yuepark.getPassword()+"','"+yuepark.getAccesstoken()+"','"+yuepark.getAddtime()+"','','"+yuepark.getUnionid()+"')";
			statement=connection.prepareStatement(sql);
//			System.out.println("-------------什么鬼-------------------");
//			System.out.println(statement.executeUpdate());
			row=statement.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeALL(connection, statement);
		}
		
		return row;
	}

	/**
	 * 根据phone查yuepark
	 */
	@Override
	public Yuepark seachPhone(String phone) {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet= null;
		Yuepark yuepark=null;
		try{
			connection=DBHelper.getConnection();
			String sql="select * from  yuepark where phone='"+phone+"'";
			statement =connection.prepareStatement(sql);
			resultSet=statement.executeQuery();
			if(resultSet.next()){
				yuepark =new Yuepark();
				yuepark.setId(resultSet.getInt("id"));
				yuepark.setOpenid(resultSet.getString("openid"));
				yuepark.setImgurl(resultSet.getString("imgurl"));
				yuepark.setPhone(resultSet.getString("phone"));
				yuepark.setPassword(resultSet.getString("password"));
				yuepark.setAccesstoken(resultSet.getString("accesstoken"));
				yuepark.setAddtime(resultSet.getString("addtime"));
				yuepark.setOpenid_xiao(resultSet.getString("openid_xiao"));
				yuepark.setUnionid(resultSet.getString("unionid"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeALL(connection, statement);
		}
		
		return yuepark;
	}

	/**
	 * 更新phone和pwd
	 */
	@Override
	public boolean updatepassword(String pwd, String phone) {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement statement=null;
		boolean result=false;
		try{
			connection = DBHelper.getConnection();
			String sql="update yuepark set password='"+pwd+"' where phone='"+phone+"'";
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

	/***
	 * 根据openid_xiao查yuepark
	 */
	@Override
	public Yuepark select(String openid_xiao) {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet= null;
		Yuepark yuepark=null;
		try{
			connection=DBHelper.getConnection();
			String sql="select * from  yuepark where openid_xiao='"+openid_xiao+"'";
			statement =connection.prepareStatement(sql);
			resultSet=statement.executeQuery();
			if(resultSet.next()){
				yuepark =new Yuepark();
				yuepark.setId(resultSet.getInt("id"));
				yuepark.setOpenid(resultSet.getString("openid"));
				yuepark.setImgurl(resultSet.getString("imgurl"));
				yuepark.setPhone(resultSet.getString("phone"));
				yuepark.setPassword(resultSet.getString("password"));
				yuepark.setAccesstoken(resultSet.getString("accesstoken"));
				yuepark.setAddtime(resultSet.getString("addtime"));
				yuepark.setOpenid_xiao(resultSet.getString("openid_xiao"));
				yuepark.setUnionid(resultSet.getString("unionid"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeALL(connection, statement);
		}
		
		return yuepark;
	}

	/**
	 * 添加phone和openid（小程序）
	 */
	@Override
	public int addopenid_xiao(String phone, String openid) {
		Connection connection=null;
		PreparedStatement statement=null;
		
		int row=0;
		try{
			connection=DBHelper.getConnection();
			String sql="insert into yuepark(phone,openid_xiao) values('"+phone+"','"+openid+"')";
			statement=connection.prepareStatement(sql);			
			row=statement.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeALL(connection, statement);
		}
		
		return row;
	}

	/**
	 * 更新phone和openID（小程序）
	 */
	@Override
	public boolean updateopenid_xiao(String phone, String openid) {
		Connection connection=null;
		PreparedStatement statement=null;
		boolean result=false;
		try{
			connection = DBHelper.getConnection();
			String sql="update yuepark set openid_xiao='"+openid+"' where phone='"+phone+"'";
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

	/**
	 * 删除phone列
	 */
	@Override
	public boolean delPhone(String phone) {
		Connection connection=null;
		PreparedStatement statement=null;
		boolean result=false;
		try{
			connection = DBHelper.getConnection();
			String sql="DELETE FROM yuepark WHERE phone='"+phone+"'";
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

	/**
	 * 根据unionid查yuepark
	 */
	@Override
	public Yuepark UnionId(String unionid) {
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet= null;
		Yuepark yuepark=null;
		try{
			connection=DBHelper.getConnection();
			String sql="select * from  yuepark where unionid='"+unionid+"'";
			statement =connection.prepareStatement(sql);
			resultSet=statement.executeQuery();
			if(resultSet.next()){
				yuepark =new Yuepark();
				yuepark.setId(resultSet.getInt("id"));
				yuepark.setOpenid(resultSet.getString("openid"));
				yuepark.setImgurl(resultSet.getString("imgurl"));
				yuepark.setPhone(resultSet.getString("phone"));
				yuepark.setPassword(resultSet.getString("password"));
				yuepark.setAccesstoken(resultSet.getString("accesstoken"));
				yuepark.setAddtime(resultSet.getString("addtime"));
				yuepark.setOpenid_xiao(resultSet.getString("openid_xiao"));
				yuepark.setUnionid(resultSet.getString("unionid"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBHelper.closeALL(connection, statement);
		}
		
		return yuepark;
	}

}
