package yuepark.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HttpTest {
	/** 
     * @Description:使用URLConnection发送post 
     * @author:liuyc 
     * @time:2016年5月17日 下午3:26:52 
     */  
    public static String sendPost2(String urlParam, Map<String, Object> params, String charset) {  
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
    public static void main(String[] args) {
    	Map<String, Object> map = new HashMap<String, Object>();  
    	  map.put("name", 123);  
    	  map.put("key2", "value2");  
    	  map.put("key3", "value3");  
    	String a =sendPost2("",map,"");
    	
    }
}
