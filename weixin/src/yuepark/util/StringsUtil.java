package yuepark.util;

public class StringsUtil {
	public static String urlParse(String url){
		int len1 = url.indexOf("&t=");
		int len2 = url.indexOf("&method=");
		if(len1!=-1 && len2!=-1){
			String str1 = url.substring(len1+3, len2);
			String str2 = url.substring(len1, len2);
			url = url.replaceAll(str2, "");
			int len3 = url.indexOf("Index.aspx?");
			url = url.substring(0, len3) +str1+"api/"+url.substring(len3);
		}
		url= url.replaceAll(" ", "%20").replaceAll("<", "%3C").replaceAll(">", "%3E");
		return url;
	}
}

