package yuepark.http;

public class Constants {
    /**
     * baidu map key = AC8FD9672088AE41C4EEEE89E96341A3F46AC834
     */
    /**
     * 是否已获取html
     */
    public static boolean  GET_HTML_URL = false;
    /**
     * 3G模式读取文件缓存时间限制
     */
    public static final int CONFIG_CACHE_MOBILE_TIMEOUT  = 60000*60*10;  //10小时
    /**
     * wifi模式读取文件缓存时间限制
     */
    public static final int CONFIG_CACHE_WIFI_TIMEOUT    = 60000*60*5;   //5小时
    /**
     * 服务器时间戳
     */
    public static  long SERVER_TIME_STAMP = 0;
    /**
     * 登录时间戳
     */
    public static  long LOGIN_TIME_STAMP = 0;
    /**
     *
     */
    public static final String HTTPS_FLAG = "https://";

}
