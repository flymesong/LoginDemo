package weixin.main;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Hex;

import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.Access_tokenUtil;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;
import yuepark.util.WeixinUtil;
import bean.Yuepark;
import dao.AccessTokenDao;
import dao.YueparkDao;
import dao.impl.AccessTokenDaoImpl;
import dao.impl.YueparkDaoImpl;


public class Main {

	/**
	 * @param args
	 */
	 private static String hexString = "0123456789ABCDEFabcdef";
	public static void main(String[] args) {
//		String backUri = "https://yueparktest.applinzi.com/user/userImpowerLogin";
//		backUri = URLEncoder.encode(backUri);
		String accesstoken = Access_tokenUtil.getToken();
		System.out.println(accesstoken);
//		JSONObject ojsop = new JSONObject();
//		ojsop.put("12", "23");
//		ojsop.put("34", "45");
//		System.out.println(ojsop.toString());
//		String appid="wxfd965e088d2fdb94";
//		String secret ="d6f0c3d327a2b5000a1a3c28b44f828d";
//		
//		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
//		url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
//  
//		String a =HttpClientUrl.sendGet(url,null,"UTF-8");
//		System.out.println(a);
//		JSONObject jsonObject =  JSONObject.fromObject(a);
//		String access_token =jsonObject.getString("access_token");
//		System.out.println("access_token:"+access_token);
//		YueparkDao yueparkDao = new YueparkDaoImpl();
//		String mobileno = "15018595485";
//		Yuepark yuepark=yueparkDao.seachPhone(mobileno);
//		String openid=yuepark.getOpenid();
//		System.out.println(openid);
		
//		AccessTokenDao accessTokenDao = new AccessTokenDaoImpl();
//		String token3="1";
//		String time="1";
//		accessTokenDao.update(token3, time);
//		String str = "1231213211";
//		String time = "1710171035";
//		String atime = "1710171000";
//		int a =0;
//		try {
//		   int a = Integer.parseInt(time)-Integer.parseInt(atime);
//		    if(a>36){
//		    	System.out.println("daaa");
//		    }else{
//		    	System.out.println("bbbbbbb");
//		    }
//		} catch (NumberFormatException e) {
//		    e.printStackTrace();
//		}
//	Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
//		String time = sdf.format(date);
		
//		int a=Integer.parseInt(time);
//		int result=(Integer.valueOf(time).intValue())-(Integer.valueOf(atime).intValue());
//		System.out.println("result:"+a);
//		String appid="wxcc58309eeef6b660";
//		String secret ="29496670e27f1b933e6157d2715255e0";
//		String aToken = WeixinUtil.getAccess_token("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret+"");  
//        System.out.println("这里是aToken"+aToken);
//        String[] tokenOne = aToken.split(":");
//        String[] token = tokenOne[1].split(",");
//        char [] stringArr = token[0].toCharArray();
//        String token3 = "" ;
//        for(int i=1;i<stringArr.length-1;i++){
//            String token2 = String.valueOf(stringArr[i]);
//            token3 += token2;
//        }
//        System.out.println("这里是ACCESS_TOKEN"+token3);
//		String openid="we";
//		String text ="Hello World";
//		JSONObject obj1 = new JSONObject();
//        obj1.put("content", "Hello World");
//        
//        JSONObject obj = new JSONObject();
//        obj.put("touser", openid);
//        obj.put("msgtype", "text");
//        obj.put("text", obj1);
//
//        System.out.println("obj1:" + obj1);
//        System.out.println("obj:" + obj);
//		String json = "{\"touser\":\""+openid+"\",\"template_id\":\"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY\",\"url\":\"http://localhost:8080/weixin/login.jsp\"" +
//				",\"miniprogram\":{\"appid\":\"xiaochengxuappid12345\",\"pagepath\":\"index?foo=bar\"}" +
//				",\"data\":{\"first\":{\"value\":\"恭喜你购买成功！\",\"color\":\"#173177\"}" +
//				",\"keynote1\":{\"value\":\"巧克力\",\"color\":\"#173177\"}" +
//				",\"keynote2\":{\"value\":\"39.8元\",\"color\":\"#173177\"}" +
//				",\"keynote3\":{\"value\":\"2014年9月22日\",\"color\":\"#173177\"}" +
//				",\"remark\":{\"value\":\"欢迎再次购买！\",\"color\":\"#173177\"}}}"; 
//           System.out.println("json:" + json);
//        String code = "013kEQJv17BdRc0l61Iv1vEPJv1kEQJK";
//		String appid="wxcc58309eeef6b660";
//		String secret ="29496670e27f1b933e6157d2715255e0";
//		String url ="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
//		String a =HttpClientUrl.sendGet(url,null,"UTF-8");
//		System.out.println(a);
//		String accessToken="Ps20oq2mxSurZ28KixKDaRkn91SJY0q9QpacCFNX88X4guTFHcGX_ng0Yqwb7r3Ps4m1WGb1cfB7qPl_PwpQLmRDNeFWIpKw4WLkBtbIpa4";
//		String openId = "obMejwPXFHdHNFT_-Xmg9uoXEAFY";
//		String unurl="https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN ";
//		String a =HttpClientUrl.sendGet(unurl,null,"UTF-8");
//		
//		JSONObject jsonObject =  JSONObject.fromObject(a);
//		String jsonObject12 =jsonObject.getString("unionid");
//		String jsonObject1 =jsonObject.getString("openid");
//		System.out.println(a);
//		System.out.println(jsonObject12);
//		System.out.println(jsonObject1);
//		YueparkDao yueparkDao = new YueparkDaoImpl();
//		Yuepark yuepark=yueparkDao.UnionId("oaGKM00e8cQvL2q7PeMqmEFsJE_M");
//		if(yuepark==null){
//			System.out.println("aaa");
//		}else{
//			System.out.println("bbb");
//		}
//		DateFormat dateTimeformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		String strBeginDate = dateTimeformat.format(new Date());
//		System.out.println(strBeginDate);
//		Date date1=null;
//		try {
//			date1=dateTimeformat.parse(strBeginDate);
////			System.out.println(date1);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
//		String url="http://yueparkstop.applinzi.com/OeauthServlet";
//		System.out.println(Main.urlEncodeUTF8(url));
//		YueparkDao user = new YueparkDaoImpl();
//		user.update("1231412", "rew");
		
//		String a="FDFEFF226C3032303030363130343337323032303136303332343032313933368A0A";
//		System.out.println(Main.decode(a));s
	}
	   
	public static String urlEncodeUTF8(String source){
		String result = source;
		try{
			result = java.net.URLEncoder.encode(source,"utf-8");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		return result;
		
	}
//	public static String hexToString(String str) {
//	　　　　return unescape(str.replace(/\\/g, "%"));
//	　　}
//	public static String decodeHexStr(int dataCoding, String hexStr) {
//        String realStr = null;
//        try {
//            if (hexStr != null) {
//                if (dataCoding == 15) {
//                    realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "GBK");
//                } else if ((dataCoding & 0x0C) == 0x08) {
//                    realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "UnicodeBigUnmarked");
//                } else {
//                    realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "ISO8859-1");
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        
//        return realStr;
//    }

}
