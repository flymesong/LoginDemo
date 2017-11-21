package weixin.main;

import java.sql.Connection;

import util.DBHelper;
import yuepark.util.MathsUtil;

import dao.YueparkDao;
import dao.impl.YueparkDaoImpl;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		char ch ='F';
		 byte byteAscii = (byte)ch;
		 String str="sdw";
		 byte heck =(byte)(0xFF^60^byteAscii);
//		int n =3;
//		int[] arr={2,4,5,3,5,3,0,2};
//		int namea = 0;
//        for (int i = 0; i<arr.length;i++){
//          namea = arr[i];
		 System.out.println(str.charAt(1));
		 System.out.println(byteAscii);
          System.out.println(heck);
//          if(n==namea){
//        	  break;
//          }
//        }
//        return name;
//        System.out.println("name:"+namea);
//        
//        if(n==namea){
//        	System.out.println("a");
//        }else{
//        	System.out.println("b");
//        }
//		String a="1234567890";
//		System.out.println("a:"+a);
//		String b=MathsUtil.GetMD5Code(a);
//		System.out.println("b:"+b);
//		String c=MathsUtil.GetMD5Code(b);
//		System.out.println("c:"+c);
//		YueparkDao yuepark = new YueparkDaoImpl();
//		yuepark.update("123123", "eqw");
//		Connection count = DBHelper.getConnection();
//		if(count != null){
//			System.out.println("连接成功");
//		}else{
//			System.out.println("连接失败");
//		}
	}

}
