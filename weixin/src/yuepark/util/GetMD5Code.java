package yuepark.util;


import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMD5Code {
	
	public final static String APP_KEY = "10011001";
	
	 // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    /**
     * MD5加密
     * @param strObj
     * @return
     */
    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
 // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }
    /**
     * get请求url加密
     * @param url
     * @return
     */
    public static String  getEncryptionValuePair(String url){
        try {
            List<NameValuePair> pairs= getUrlParameters(url);
            for(int i=0;i<pairs.size();i++){
                NameValuePair pair = pairs.get(i);
                if(pair.getName().equals("method")){
                    String value = pair.getValue().toLowerCase();
                    value = GetMD5Code(APP_KEY + value);
                    url += ("&appkey="+ APP_KEY
                            + "&security="+ value
//                            +"&mobilecode="+ CommonUtility.getIMEI()
                            );
//                            +"&timestamp="+getSystemTimestamp());
                }
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//			TagUtil.showLogDebug(e.toString());
        }
//        return getEncryptionUrl(url);
        return url;
    }
    /**
     * 将Url地址转化为请求参数键值对
     * @param url
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static List<NameValuePair> getUrlParameters(String url)
            throws UnsupportedEncodingException {
        List<NameValuePair>  params = new ArrayList<NameValuePair>();
        String[] urlParts = url.split("\\?");
        if (urlParts.length > 1) {
            String query = urlParts[1];
            for (String param : query.split("&")) {
                String pair[] = param.split("=");
                String key = URLDecoder.decode(pair[0], "UTF-8");
                String value = "";
                if (pair.length > 1) {
                    value = URLDecoder.decode(pair[1], "UTF-8");
                }
                if (key !="" && value != "")
                    params.add(new BasicNameValuePair(key, value));
            }
        }
        return params;
    }
}
