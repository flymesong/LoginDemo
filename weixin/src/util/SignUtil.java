package util;

import java.security.MessageDigest;
import java.util.Arrays;

public class SignUtil {
	//与开发模式接口配置信息中的Token保持一致
	private static String token = "YueParking";
	
	/*
	 * 微信加密签名
	 * signature 微信加密签名
	 * timestapm 时间戳
	 * nonce 随机数
	 */
	public static boolean checkSignatrre(String signature,String timestamp,String nonce){
		//对token timestamp nonce 按字典排序
		String[] paramArr = new String[]{token,timestamp,nonce};
		Arrays.sort(paramArr);
		
		//将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
		String ciphertext = null;
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			//对拼接后的字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			ciphertext = byteToStr(digest);
		}catch(Exception e){
			e.printStackTrace();
		}
		//将sha1加密后的字符串和signature(腾讯发来的参数) 进行对比
		return ciphertext!=null ? ciphertext.equals(signature.toUpperCase()) : false;
	}
	
	/*
	 * 将字节数组转换成十六进制字符串
	 */
	private static String byteToStr(byte[] byteArray){
		String strDigest = "";
		for(int i=0;i<byteArray.length;i++){
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}
	
	/*
	 * 将字节转换为十六进制的字符串
	 */
	private static String byteToHexStr(byte mByte){
		char[] Digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte>>>4) & 0x0F];
		tempArr[1] = Digit[mByte & 0x0F];
		String s = new String(tempArr);
		return s;
	}
}
