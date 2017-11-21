package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import menu.Button;
import menu.ClickButton;
import menu.ComplexButton;
import menu.Menu;
import menu.ViewButton;
import net.sf.json.JSONObject;

public class Main {

	public static void main(String[] args) throws Exception {
		// 获取凭证接口地址
		String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx2d81ef523987ef12&secret=0cda0f120f1f2ad2c05440a469e9da8a";
		//建立连接
		URL url = new URL(token_url);
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		//使用自定义的信任管理器
		TrustManager[]tm = {new MyX509TrustManager()};
		SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		conn.setSSLSocketFactory(ssf);
		conn.setDoInput(true);
		
		//设置请求方式
		conn.setRequestMethod("GET");
		//获取返回流
		InputStream inputStream = conn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		//读取相应内容
		StringBuffer buffer = new StringBuffer();
		String str = null;
		while((str = bufferedReader.readLine())!= null){
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		conn.disconnect();
		System.out.println(buffer);
		JSONObject json = JSONObject.fromObject(buffer.toString());
		System.out.println(json.getString("access_token"));//访问凭证
		System.out.println(json.getInt("expires_in"));//有效期
		String access_token = json.getString("access_token");
		
		ViewButton btn1 = new ViewButton();
		btn1.setName("注册/充值");
		btn1.setType("view");
		btn1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2d81ef523987ef12&redirect_uri=http://yuebotingche.duapp.com/servlet/OAuthServlet&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");
		
		ViewButton btn2 = new ViewButton();
		btn2.setName("扫码停车");
		btn2.setType("view");
		btn2.setUrl("http://yuebotingche.duapp.com/login.jsp");
	
		ViewButton btn31 = new ViewButton();
		btn31.setName("个人信息");
		btn31.setType("view");
		btn31.setUrl("http://yuebotingche.duapp.com/login.jsp");
		
		ViewButton btn32 = new ViewButton();
		btn32.setName("余额查询");
		btn32.setType("view");
		btn32.setUrl("https://www.baidu.com");
		
		ViewButton btn33 = new ViewButton();
		btn33.setName("订单查询");
		btn33.setType("view");
		btn33.setUrl("https://www.baidu.com");
		
		ViewButton btn34 = new ViewButton();
		btn34.setName("联系我们");
		btn34.setType("view");
		btn34.setUrl("https://www.baidu.com");
		//复合按钮
		ComplexButton complexButton = new ComplexButton();
		complexButton.setSub_button(new Button[]{btn31,btn32,btn33,btn34});
		complexButton.setName("我的账户");
		
		//创建菜单对象
		Menu menu = new Menu();
		menu.setButton(new Button[]{btn1,btn2,complexButton});
		
		String jsonMenu = JSONObject.fromObject(menu).toString();
		
		String menuCreateUrl = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
		
		//建立连接
		URL url1 = new URL(menuCreateUrl);
		HttpsURLConnection conn1 = (HttpsURLConnection)url1.openConnection();
		//使用自定义的信任管理器
		TrustManager[]tm1 = {new MyX509TrustManager()};
		SSLContext sslContext1 = SSLContext.getInstance("SSL","SunJSSE");
		sslContext1.init(null, tm1, new java.security.SecureRandom());
		SSLSocketFactory ssf1 = sslContext1.getSocketFactory();
		conn1.setSSLSocketFactory(ssf1);
		conn1.setDoInput(true);
		conn1.setDoOutput(true);
		//设置请求方式
		conn1.setRequestMethod("POST");
		
		//向输出流写入菜单结构
		OutputStream outputStream = conn1.getOutputStream();
		outputStream.write(jsonMenu.getBytes("UTF-8"));
		outputStream.close();
		
		//取得输入流
		InputStream inputStream1 = conn1.getInputStream();
		InputStreamReader inputStreamReader1 = new InputStreamReader(inputStream1,"UTF-8");
		BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
		
		//读取响应内容
		StringBuffer buffer1 = new StringBuffer();
		String str1 = null;
		while((str1 = bufferedReader1.readLine())!= null){
			buffer1.append(str1);
		}
		bufferedReader1.close();
		inputStreamReader1.close();
		inputStream1.close();
		conn1.disconnect();
		System.out.println(buffer1);
		
	}

}
