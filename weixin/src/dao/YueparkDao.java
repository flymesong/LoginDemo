package dao;

import bean.Yuepark;


public interface  YueparkDao {
	public Yuepark seach(String openid) ;
	
	public boolean update(Yuepark yuepark);
	
	public int Addopenid(Yuepark yuepark) ;
	
	public Yuepark seachPhone(String phone);
	
	public boolean updatepassword(String pwd,String phone);
	
	public boolean delPhone(String phone);//注销
	
	//小程序
	public Yuepark select(String openid_xiao);
	
	public int addopenid_xiao(String phone,String openid);
	
	public boolean updateopenid_xiao(String phone,String openid);
	
	public Yuepark UnionId(String unionid);
}
