package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;
import menu.Menu;

public class MenuUtil {

	private static Logger log = LoggerFactory.getLogger(MenuUtil.class);
	//菜单创建
	public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //删除菜单
	public final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	//创建菜单
	public static boolean createMenu(Menu menu,String accessToken){
		boolean result = false;
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		
		//将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		//发送POST请求
		JSONObject jsonObject = CommonUtil.httpRequest(url, "POST", jsonMenu);
		if(null!=jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if(0==errorCode){//创建菜单成功
				result = true;
			}else{
				result = false;
				log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}
	public static boolean deleteMenu(String accessToken){
		boolean result = false;
		String requestUrl = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		//发起get请求删除菜单
		JSONObject jsonObject = CommonUtil.httpRequest(requestUrl, "GET", null);
		if(null!=jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if(0==errorCode){//删除菜单成功
				result = true;
			}else{
				result = false;
			}
		}
		return result;
	}
	


}
