package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pojo.Token;
import test.MyX509TrustManager;
/**
 * 通用工具类
 * @author USER
 *
 */
public class CommonUtil {

	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	
	//获取凭证
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	//发送http请求
	/*
	 * 
	 */
	public static JSONObject httpRequest(String requestUrl,String requestMethod,String outputStr){
		JSONObject jsonObject = null;
		try{
			//创建SSL对象
			TrustManager[] tm = {new MyX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			//建立连接
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			//设置请求方式
			conn.setRequestMethod(requestMethod);
			
			//当提交的数据不为null 向输入流写数据
			if(null!=outputStr){
				OutputStream outputStream = conn.getOutputStream();
				//设置编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			
			//从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			//读取响应内容
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while((str = bufferedReader.readLine())!= null){
				buffer.append(str);
			}
			//释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		}catch(Exception e){
			log.error("请求异常",e);
		}
		return jsonObject;
	}
	
	public static Token getToken(String appid,String appsecret){
		Token token = null;
		String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl,"GET",null);
		if(null!=jsonObject){
			try{
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			}catch(Exception e){
				log.error("获取口令失败");
			}
		}
		
		return token;
	}
	

}
