package test;

import menu.Button;
import menu.ClickButton;
import menu.ComplexButton;
import menu.Menu;
import menu.MiniprogramButton;
import menu.ViewButton;
import pojo.Token;
import util.CommonUtil;
import util.MenuUtil;

public class MenuManager {

	//定义菜单结构
	private static Menu getMenu(){
		
		ViewButton btn1 = new ViewButton();
		btn1.setName("注册充值");
		btn1.setType("view");
		btn1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfd965e088d2fdb94&redirect_uri=http://yuepark.applinzi.com/OeauthServlet&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect");
//		https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2d81ef523987ef12&redirect_uri=http%3A%2F%2Fyuebotingche.duapp.com%2FOeauthServlet&response_type=code&scope=snsapi_base&state=1#wechat_redirect
//			linkangsongweixin.duapp.com
		MiniprogramButton btn21 = new MiniprogramButton();
		btn21.setName("扫描二维码");
		btn21.setType("miniprogram");
		btn21.setUrl("");
		btn21.setAppid("wxcc58309eeef6b660");
		btn21.setPagepath("");

		MiniprogramButton btn22 = new MiniprogramButton();
		btn22.setName("输入泊位编码");
		btn22.setType("miniprogram");
		btn22.setUrl("");
		btn22.setAppid("wxcc58309eeef6b660");
		btn22.setPagepath("");

		ViewButton btn31 = new ViewButton();
		btn31.setName("个人信息");
		btn31.setType("view");
		btn31.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfd965e088d2fdb94&redirect_uri=http://yuepark.applinzi.com/OAuthServlet&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect");
		
		ViewButton btn32 = new ViewButton();
		btn32.setName("余额查询");
		btn32.setType("view");
		btn32.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfd965e088d2fdb94&redirect_uri=http://yuepark.applinzi.com/BalanceServlet&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect");
		
		ViewButton btn33 = new ViewButton();
		btn33.setName("订单查询");
		btn33.setType("view");
		btn33.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfd965e088d2fdb94&redirect_uri=http://yuepark.applinzi.com/OrderServlet&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect");
		
		ClickButton  btn34 = new ClickButton ();
		btn34.setName("联系我们");
		btn34.setType("click");
		btn34.setKey("V1001_TODAY_MUSIC");
//		ClickButton btn1 = new ClickButton();
//		btn1.setName("海贼王");
//		btn1.setType("click");
//		btn1.setKey("V1001_TODAY_MUSIC");
		//复合按钮
		ComplexButton complexButton = new ComplexButton();
		complexButton.setSub_button(new Button[]{btn31,btn32,btn33,btn34});
		complexButton.setName("我的账户");
		
		//复合按钮
		ComplexButton complexButton1 = new ComplexButton();
		complexButton1.setSub_button(new Button[]{btn21,btn22});
		complexButton1.setName("扫码停车");
		
		//创建菜单对象
		Menu menu = new Menu();
		menu.setButton(new Button[]{btn1,complexButton1,complexButton});
		
		return menu;
	}
	
	public static void main(String[] args) throws Exception {
		//声明appid等两个凭证
		String appId = "wxfd965e088d2fdb94";
		String appSecret = "d6f0c3d327a2b5000a1a3c28b44f828d";
		
		//获取Token
		Token token = CommonUtil.getToken(appId, appSecret);
		if(null!=token){
			//创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());
//			boolean result = MenuUtil.deleteMenu(token.getAccessToken());
			if(result == true){
				System.out.println("创建成功");
			}else{
				System.out.println("创建失败");
			}
		}
	}
}
