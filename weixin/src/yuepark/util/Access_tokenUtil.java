package yuepark.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;
import yuepark.http.HttpClientUrl;
import bean.AccessToken;
import dao.AccessTokenDao;
import dao.impl.AccessTokenDaoImpl;

public class Access_tokenUtil {
	public final static String appid="wxfd965e088d2fdb94";
	public final static String secret ="d6f0c3d327a2b5000a1a3c28b44f828d";
	public static String getToken(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
		String time = sdf.format(date);
		AccessTokenDao accessTokenDao = new AccessTokenDaoImpl();
		AccessToken accessToken = accessTokenDao.seach();
		String data_time=accessToken.getData_time();
		String accesstoken = null;
		int bad = Integer.parseInt(time)-Integer.parseInt(data_time);
		if(bad > 60){
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
			url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
	  
			String a =HttpClientUrl.sendGet(url,null,"UTF-8");
			JSONObject jsonObject =  JSONObject.fromObject(a);
			String access_token =jsonObject.getString("access_token");
			System.out.println("access_token:"+access_token);
			accesstoken=access_token;
	        accessTokenDao.update(access_token, time);
		}else{
			accesstoken = accessToken.getAccess_token();
		}
		return accesstoken;
	}
}
