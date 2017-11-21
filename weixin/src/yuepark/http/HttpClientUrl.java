package yuepark.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import yuepark.util.GetMD5Code;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class HttpClientUrl {
	/** 
     * @Description:使用URLConnection发送post 
     * @author:
     * @time:2016年5月17日 下午3:26:52 
     */  
    public static String sendPost(String urlParam, Map<String, Object> params, String charset) {  
        StringBuffer resultBuffer = null;  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> e : params.entrySet()) {  
                sbParams.append(e.getKey());  
                sbParams.append("=");  
                sbParams.append(e.getValue());  
                sbParams.append("&");  
            }  
        }  
        URLConnection con = null;  
        OutputStreamWriter osw = null;  
        BufferedReader br = null;  
        try {  
            URL realUrl = new URL(urlParam);  
            // 打开和URL之间的连接  
            con = realUrl.openConnection();  
            // 设置通用的请求属性  
            con.setRequestProperty("accept", "*/*");  
            con.setRequestProperty("connection", "Keep-Alive");  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 发送POST请求必须设置如下两行  
            con.setDoOutput(true);  
            con.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            osw = new OutputStreamWriter(con.getOutputStream(), charset);  
            if (sbParams != null && sbParams.length() > 0) {  
                // 发送请求参数  
                osw.write(sbParams.substring(0, sbParams.length() - 1));  
                // flush输出流的缓冲  
                osw.flush();  
            }  
            // 定义BufferedReader输入流来读取URL的响应  
            resultBuffer = new StringBuffer();  
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));  
            if (contentLength > 0) {  
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));  
                String temp;  
                while ((temp = br.readLine()) != null) {  
                    resultBuffer.append(temp);  
                }  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (osw != null) {  
                try {  
                    osw.close();  
                } catch (IOException e) {  
                    osw = null;  
                    throw new RuntimeException(e);  
                }  
            }  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
        return resultBuffer.toString();  
    }
    
    /**
	 * @Description:使用URLConnection发送get请求
	 * @author:
	 * @time:2016年5月17日 下午3:27:58
	 */
	public static String sendGet(String urlParam, Map<String, Object> params, String charset) {
		StringBuffer resultBuffer = null;
		// 构建请求参数
		StringBuffer sbParams = new StringBuffer();
		if (params != null && params.size() > 0) {
			for (Entry<String, Object> entry : params.entrySet()) {
				sbParams.append(entry.getKey());
				sbParams.append("=");
				sbParams.append(entry.getValue());
				sbParams.append("&");
			}
		}
		BufferedReader br = null;
		try {
			URL url = null;
			if (sbParams != null && sbParams.length() > 0) {
				url = new URL(urlParam +"&"+ sbParams.substring(0, sbParams.length() - 1));//+ "?" 
			} else {
				url = new URL(urlParam);
			}
			URLConnection con = url.openConnection();
			// 设置请求属性
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection", "Keep-Alive");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立连接
			con.connect();
			resultBuffer = new StringBuffer();
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
			String temp;
			while ((temp = br.readLine()) != null) {
				resultBuffer.append(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
					e.printStackTrace();
				}
			}
		}
		return resultBuffer.toString();
//		System.out.println(resultBuffer.toString());
	}
    public static void main(String[] args) {
//    	String url = HttpUrl.Register_Url;//注册
//    	String url = HttpUrl.Getuserinfo_Url;//查询信息
//    	String url = HttpUrl.Login_Url;//登录
//    	String url = HttpUrl.Logout_Url;//退出登录
//    	String url = HttpUrl.Getsafecode_Url;//获取验证码
//    	String url = HttpUrl.ResetPwd_Url;//修改密码
//    	String url = HttpUrl.QueryBalance_Url;//余额查询
//    	String url = HttpUrl.BalanceRecharge_Url;//余额充值
    	String url = HttpUrl.CurrentOrder_Url;//当前订单
//    	String url = HttpUrl.ApplyCashOut_Url;//余额提现
//    	String url = HttpUrl.GetOrderList_Url;//订单列表
//    	String url = HttpUrl.Weixinpay_Url;//微信支付
//    	String url = HttpUrl.BerthCodeApply_Url;//申请泊位
//    	String url = HttpUrl.Setberthstatus_Url;//控锁状态
//    	String url = HttpUrl.AccountPay_Url;//余额支付
//    	String url = HttpUrl.Alipay_Url;//支付宝支付
//    	String url = HttpUrl.ActCarLock_Url;//激活车位锁
//    	String url = HttpUrl.CancelOrder_Url;//取消订单
//    	String url = HttpUrl.SetStatus_Url;//全状态上报
    	
//    	{"status":"1","msg":"成功","data":
//    	{"parkorder":
//    	{"worktype":"0",
//    		"parkpwd":"0",
//    		"bargainordercode":"52017102014011423914",
//    		"parkingname":"测试停车场2.0",
//    		"berthcode":"00000043",
//    		"parkingid":"28",
//    		"seationaddress":"",
//    		"latitude":"22.5818603",
//    		"longitude":"113.9514174",
//    		"addtime":"2017/10/20 14:01:14",
//    		"enterstatus":"2",
//    		"startparkingtime":"2017/10/20 14:01:26",
//    		"endparkingtime":"2000/1/1 0:00:00",
//    		"applytype":"4",
//    		"amount":"15.00",
//    		"parkduration":"15",
//    		"parktime":"2017/10/20 14:01:27",
//    		"deductedprice":"0.00",
//    		"arrearsprice":"0.00"}}};

//    	{"arrearsprice":"0.00",
//    		"deductedprice":"0.00",
//    		"enterstatus":"2",
//    		"bargainordercode":"52017101809323262135",
//    		"berthcode":"00000041",
//    		"parkingname":"测试停车场2.0",
//    		"addtime":"2017/10/18 9:32:32",
//    		"startparkingtime":"2017/10/18 9:32:42",
//    		"endparkingtime":"2017/10/18 9:33:44",
//    		"amount":"0.00",
//    		"canceltime":"",
//    		"parkduration":"1",
//    		"applytype":"4",
//    		"applytime":"2017/10/18 9:32:42",
//    		"orderstatus":"1",
//    		"parktime":"2017/10/18 9:32:43"}
    	Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		System.out.println(time);
//    	String pwd1 = "15452154564";
//    	String pwd = GetMD5Code.GetMD5Code(pwd1);
//    	String paypwd =GetMD5Code.GetMD5Code(pwd1);
		
			
//		}
    	url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
//    	for(int j=1;j<=5;j++){
    	Map<String, Object> map = new HashMap<String, Object>();  
    	  map.put("mobileno", "17723614530");
//    	  map.put("pageindex", j);
//    	  map.put("mobileno", "15818786148");
//    	  map.put("ordercode", "52017092610393384997");
//    	  map.put("parkPwd", "05011608010013"); 
//    	  map.put("parkStatus",0); //车位状态
//    	  map.put("machineStatus",1); //锁臂状态
//    	  map.put("fusestatus",0);//保险丝
//    	  map.put("tstamp", time); 
//    	  map.put("code", "000000000043");  
//    	  map.put("method", "register");   
//    	  map.put("appkey", "10011001");  
//    	  map.put("security", "10011001"); 
//    	  map.put("loginpwd", pwd);
//    	  map.put("pwd", "1234");
//    	  map.put("paypwd", paypwd); 
//    	  map.put("amount", 0.01); 
//    	  map.put("paytype", 3);
//    	  map.put("openid", "obMejwPXFHdHNFT_-Xmg9uoXEAFY");
    	String a =sendGet(url,map,"UTF-8");
    	System.out.println(a);
//    	JSONObject jsonObject =  JSONObject.fromObject(a);    	
//    	JSONObject jsonObject1 =JSONObject.fromObject(jsonObject.getString("data"));    	
//    	System.out.println(jsonObject1);
//    	JSONArray jsonarray = JSONArray.fromObject(jsonObject1.getJSONArray("items"));  
//    	System.out.println(jsonarray);
//    	for(int i =0;i<jsonarray.size();i++){
//    		JSONObject job = jsonarray.getJSONObject(i);
//    		String applytype= job.getString("bargainordercode");
//    		System.out.println(job);
//    		System.out.println(applytype);
//    	}
    	System.out.println("---------------------------------------------------------------------");
//    	System.out.println("-------------------------------"+j+"--------------------------------------");
    	System.out.println("---------------------------------------------------------------------");
//		}
//    	int count = jsonObject1.getInt("count");
//    	System.out.println(count);
//    	if(count>0){
//    	JSONObject jsonObject2 = JSONObject.fromObject(jsonObject1.getString("WxResponse"));
//    		System.out.println(jsonObject2);
//    		System.out.println("未支付金额:"+jsonObject2.getString("arrearsprice"));
//			System.out.println("已支付金额:"+jsonObject2.getString("deductedprice"));
//			System.out.println("停车密码:"+jsonObject2.getString("parkpwd"));
//			System.out.println("是否已到场停车:--1未到场、2已到场--："+jsonObject2.getString("enterstatus"));
//			System.out.println("停车订单号:"+jsonObject2.getString("bargainordercode"));
//			System.out.println("泊位编号:"+jsonObject2.getString("berthcode"));
//			System.out.println("停车场名称:"+jsonObject2.getString("parkingname"));
//			System.out.println("停车场编号:"+jsonObject2.getString("parkingid"));
//			System.out.println("地址:"+jsonObject2.getString("seationaddress"));
//			System.out.println("纬度:"+jsonObject2.getString("latitude"));
//			System.out.println("经度:"+jsonObject2.getString("longitude"));
//			System.out.println("申请时间:"+jsonObject2.getString("addtime"));
//			System.out.println("订单开始时间:"+jsonObject2.getString("startparkingtime"));
//			System.out.println("订单结束时间:"+jsonObject2.getString("endparkingtime"));
//			System.out.print("实际费用:"+jsonObject2.getString("amount"));
//			
//			System.out.println("停车时长:"+jsonObject2.getString("parkduration"));
//			System.out.println("预约类型：-1立即预约、4扫码停车-:"+jsonObject2.getString("applytype"));			
//			System.out.println("停车时间:"+jsonObject2.getString("parktime"));
//    		if(jsonarray.size()>0){
//    			for(int i =0;i<jsonarray.size();i++){
//    				JSONObject job = jsonarray.getJSONObject(i);
//    				System.out.println("------------------------------------------------------------------------------");
//    				System.out.println("未支付金额:"+job.get("arrearsprice"));
//    				System.out.println("已支付金额:"+job.get("deductedprice"));
//    				System.out.println("是否已到场停车:-1未到场、2已到场-："+job.get("enterstatus"));
//    				System.out.println("停车订单号:"+job.get("bargainordercode"));
//    				System.out.println("泊位编号:"+job.get("berthcode"));
//    				System.out.println("停车场名称:"+job.get("parkingname"));
//    				System.out.println("申请时间:"+job.get("addtime"));
//    				System.out.println("订单开始时间:"+job.get("startparkingtime"));
//    				System.out.println("订单结束时间:"+job.get("endparkingtime"));
//    				System.out.println("当前费用:"+job.get("amount"));
//    				System.out.println("取消时间:"+job.get("canceltime"));
//    				System.out.println("停车时长:"+job.get("parkduration"));
//    				System.out.println("预约类型：-1立即预约、4扫码停车-:"+job.get("applytype"));
//    				System.out.println("受理时间:"+job.get("applytime"));
//    				System.out.println("订单状态:-1已完成、2已取消、3欠费-："+job.get("orderstatus"));
//    				System.out.println("停车时间:"+job.get("parktime"));
//    				System.out.println("------------------------------------------------------------------------------");
//    			}
//    		}
//    		
//    	}
//    	String msg =jsonObject.getString("msg");
//    	System.out.println(msg);
//    	JSONObject jsonObject2 = JSONObject.fromObject(jsonObject1.getString("count"));
//		System.out.println(jsonObject2);
//    	String prepayid =jsonObject2.getString("prepayid");
//    	System.out.println(prepayid);
    	

//    	url1 = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url1));
//    	Map<String, Object> map1 = new HashMap<String, Object>();  
//    	  map1.put("mobileno", "18824126316");
//    	  map1.put("ordercode", prepayid);
//    	  String a1 =sendGet(url1,map1,"UTF-8");
//      	  System.out.println(a1);
//    	  String b =sendGet(url1,map1,"UTF-8");
//    	  System.out.println(b);
    }
}
