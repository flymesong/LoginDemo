package dao;

import bean.AccessToken;

public interface AccessTokenDao {
	public AccessToken seach() ;//查对象
	
	public boolean update(String access_token,String data_time);//更新对象
}
