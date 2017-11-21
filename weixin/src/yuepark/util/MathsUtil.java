package yuepark.util;



import yuepark.http.Constants;

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


public class MathsUtil {
    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    /**
     * 校验值
     */
//	public final static String signKey = "d2a57dc1d883fd21fb9951699df71cc7";
    public final static String signKey = "123456";
    /**
     * 支付宝合作伙伴密钥管理
     */
    public final static String ALIPAY_KEY = "ae8b8m53qkhqv6dbitievnixhpvxgr3f";
    /**
     * appKey
     */
//	public final static String APP_KEY = "106300944";
    public final static String APP_KEY = "10011001";
    final static double DEF_PI = 3.14159265359; // PI
    final static double DEF_2PI = 6.28318530712; // 2*PI
    final static double DEF_PI180 = 0.01745329252; // PI/180.0
    final static double DEF_R = 6370693.5; // radius of earth
    
    public final static String loginpwd = "123456";
    public final static String paypwd = "123456";

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
                    String loginpwd1=GetMD5Code.GetMD5Code(loginpwd);
                    String paypwd1 = GetMD5Code.GetMD5Code(paypwd);
                    value = GetMD5Code(APP_KEY + value);
                    url += ("&appkey="+ APP_KEY
                            + "&security="+ value
//                            + "&loginpwd="+ loginpwd1
//                            + "&paypwd="+ paypwd1
                            +"&mobilecode="+ "000000000000");
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
     * get请求url加密
     * @param url
     * @return
     */
    public static String  getEncryptionUrl(String url){
        List<String>listStr = new ArrayList<String>();
        Map<String ,String>map = new HashMap<String ,String>();
        try {
            List<NameValuePair> pairs= getUrlParameters(url);
            for(int i=0;i<pairs.size();i++){
                NameValuePair pair = pairs.get(i);
                listStr.add(pair.getName());
                String key = pair.getValue();
                map.put(pair.getName(), key);
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[] names = new String[listStr.size()];
        listStr.toArray(names);
        Comparator cmp  =  Collator.getInstance(java.util.Locale.ENGLISH);
        Arrays.sort(names, cmp);
        String str="";
        for(int j=0;j<names.length;j++){
            if(j==0)
                str += (names[j]+"="+map.get(names[j]));
            else
                str += ("&"+names[j]+"="+map.get(names[j]));
        }
        //TagUtil.showLogDebug("排序后的请求参数--"+str);
        String code = str+signKey;
        try {
            code = new String(code.getBytes(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//			TagUtil.showLogError(e.toString());
        }
        String sign = "&sign="+GetMD5Code(code);
        return (url +=sign);
    }
    /**
     * 支付宝get请求url加密
     * @param url
     * @return
     */
    public static String  getAlipayEncryptionUrl(String url){
        List<String>listStr = new ArrayList<String>();
        Map<String ,String>map = new HashMap<String ,String>();
        try {
            List<NameValuePair> pairs= getUrlParameters(url);
            for(int i=0;i<pairs.size();i++){
                NameValuePair pair = pairs.get(i);
                listStr.add(pair.getName());
                String key = pair.getValue();
                map.put(pair.getName(), key);
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[] names = new String[listStr.size()];
        listStr.toArray(names);
        Comparator cmp  =  Collator.getInstance(java.util.Locale.ENGLISH);
        Arrays.sort(names, cmp);
        String str="";
        for(int j=0;j<names.length;j++){
            if(j==0)
                str += (names[j]+"="+map.get(names[j]));
            else
                str += ("&"+names[j]+"="+map.get(names[j]));
        }
//		TagUtil.showLogDebug("排序后的请求参数--"+str);
        String code = str+ALIPAY_KEY;
        try {
            code = new String(code.getBytes(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//			TagUtil.showLogError(e.toString());
        }
        String sign = "&sign="+GetMD5Code(code);
        return (url +=sign);
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
    /**
     * 将Url地址转化为请求参数键值对
     * @param url
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static MultipartEntity getUrlRequestParams(String url)
            throws UnsupportedEncodingException {
        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,null
                ,Charset.forName("UTF-8"));
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
                    multipartEntity.addPart(key, new StringBody(value));
            }
        }
        return multipartEntity;
    }
    /**
     * MD5加密算法
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
     * 格式化金额数字
     * @param s
     * @return
     */
    public static String formatMoneyData(String s){
		/*if(s.indexOf(".") > 0){  
			s = s.replaceAll("0+?$", "");//去掉多余的0  
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
		} */
        if(s==null || s.equals(""))
            return "0.00";
        if(s.indexOf(",") != -1)
            return s ;
        DecimalFormat df2 = new DecimalFormat("###,###,##0.00");//保留2位
        return df2.format(Double.parseDouble(s));
    }
    /**
     * 格式化距离
     * @param s
     * @return
     */
    public static String formatDistanceData(String s){
		/*if(s.indexOf(".") > 0){  
			s = s.replaceAll("0+?$", "");//去掉多余的0  
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
		} */
        if(s==null || s.equals(""))
            return "0";
        DecimalFormat df2 = new DecimalFormat("########0.0");//保留2位
        return df2.format(Double.parseDouble(s));
    }

    /**
     * 格式化时间不带秒
     */
    public static String TimeFormat(String time){
        if(time.equals("") || time == null){
            return "" ;
        }else {
            if(time.indexOf(" ") != -1){
                time = time.substring(time.indexOf(" ") + 1, time.length()) ;
            }
            time = time.substring(0, time.lastIndexOf(":")) ;
            return time ;
        }

    }


    /**
     * 格式化时间带秒
     */
    public static String TimeFormatMin(String time){
        if(time.equals("") || time == null){
            return "" ;
        }else {
            if(time.indexOf(" ") != -1){
                time = time.substring(time.indexOf(" ") + 1, time.length()) ;
            }
            return time ;
        }

    }
    /**
     * 获取两坐标点之间的距离
     * @param lon1
     * @param lat1
     * @param lon2
     * @param lat2
     * @return
     */
    public static double  GetShortDistance(double lon1, double lat1, double lon2,
                                           double lat2) {
        double ew1, ns1, ew2, ns2;
        double dx, dy, dew;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 经度差
        dew = ew1 - ew2;
        // 若跨东经和西经180 度，进行调整
        if (dew > DEF_PI)
            dew = DEF_2PI - dew;
        else if (dew < -DEF_PI)
            dew = DEF_2PI + dew;
        dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
        dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
        // 勾股定理求斜边长
        distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }


    public static String formateTime(String times){
        String str = "" ;
        int time = Integer.parseInt(times);
        if(time == 0){
            str = "0分钟";
            return str;
        }
        if(time%60 == 0){
            str = time/60 + "小时" ;
        }else if(time > 60){
            str = time/60 + "小时" +  time%60 + "分钟" ;
        } else{
            str = time+ "分钟" ;
        }

        return str ;
    }
    /**
     *
     * 登录密码验证正则
     */
    public static boolean regatRegax(String str){
        boolean chagflag = false ;

        for (int i = 0; i < str.length(); i++) {
            String strOne = str.substring(i,i+1);
            if(strOne.matches("^[0-9a-zA-Z]+$") ){
                chagflag = true ;
                break ;
            }
        }

        boolean flag = str.matches("[\\x20-\\x7E]*") &&
                chagflag &&
                !str.matches("^[0-9]+$") &&
                !str.matches("^[a-zA-Z]+$") ;

        return flag ;
    }
    /**
     * 获取服务器时间戳
     * @return
     */
//    public static String getSystemTimestamp(){
//        long t = Constants.SERVER_TIME_STAMP +(System.currentTimeMillis() - Constants.LOGIN_TIME_STAMP);
//        String str = CommonUtility.longDateToStr(t);
//        return str;
//    } 

    /**
     * 格式化日期
     */
    public static String formatDate2(String date){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Date da = df.parse(date) ;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(da);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null ;
        }

    }

    /**
     * 格式化日期
     */
    public static String formatDate(String date){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
            Date da = df.parse(date) ;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(da);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null ;
        }

    }
    /**
     * DES解密算法
     * @param str
     * @return
     */
//    public static String DEXDecryptString(String str){
//        try {
//            str = Des3.decode(str);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
////			TagUtil.showLogError(e.toString());
//        }
//        //TagUtil.showLogDebug("验证码为:"+str);
//        return str;
//    }

    /**
            * 字符串转换成十六进制字符串
    * @param String str 待转换的ASCII字符串
    * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
            */
    public static String str2HexStr(String str)
    {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++)
        {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 十六进制转换字符串
     * @param String str Byte字符串(Byte之间无分隔符 如:[616C6B])
     * @return String 对应的字符串
     */
    public static String hexStr2Str(String hexStr)
    {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;

        for (int i = 0; i < bytes.length; i++)
        {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * bytes转换成十六进制字符串
     * @param byte[] b byte数组
     * @return String 每个Byte值之间空格分隔
     */
    public static String byte2HexStr(byte[] b)
    {
        String stmp="";
        StringBuilder sb = new StringBuilder("");
        for (int n=0;n<b.length;n++)
        {
            stmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((stmp.length()==1)? "0"+stmp : stmp);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }

    /**
     * bytes字符串转换为Byte值
     * @param String src Byte字符串，每个Byte之间没有分隔符
     * @return byte[]
     */
    public static byte[] hexStr2Bytes(String src)
    {
        int m=0,n=0;
        int l=src.length()/2;
        System.out.println(l);
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++)
        {
            m=i*2+1;
            n=m+1;
            ret[i] = Byte.decode("0x" + src.substring(i*2, m) + src.substring(m,n));
        }
        return ret;
    }

    /**
     * String的字符串转换成unicode的String
     * @param String strText 全角字符串
     * @return String 每个unicode之间无分隔符
     * @throws Exception
     */
    public static String strToUnicode(String strText)
            throws Exception
    {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++)
        {
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128)
                str.append("\\u" + strHex);
            else // 低位在前面补00
                str.append("\\u00" + strHex);
        }
        return str.toString();
    }

    /**
     * 将unicode 字符串
     * @param str 待转字符串
     * @return 普通字符串
     */
    public static String revert(String str)
    {
        str = (str == null ? "" : str);
        if (str.indexOf("\\u") == -1)//如果不是unicode码则原样返回
            return str;

        StringBuffer sb = new StringBuffer(1000);

        for (int i = 0; i < str.length() - 6;)
        {
            String strTemp = str.substring(i, i + 6);
            String value = strTemp.substring(2);
            int c = 0;
            for (int j = 0; j < value.length(); j++)
            {
                char tempChar = value.charAt(j);
                int t = 0;
                switch (tempChar)
                {
                    case 'a':
                        t = 10;
                        break;
                    case 'b':
                        t = 11;
                        break;
                    case 'c':
                        t = 12;
                        break;
                    case 'd':
                        t = 13;
                        break;
                    case 'e':
                        t = 14;
                        break;
                    case 'f':
                        t = 15;
                        break;
                    default:
                        t = tempChar - 48;
                        break;
                }

                c += t * ((int) Math.pow(16, (value.length() - j - 1)));
            }
            sb.append((char) c);
            i = i + 6;
        }
        return sb.toString();
    }

}
