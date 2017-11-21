package yuepark.util;

//import android.app.Activity;
//import android.content.Context;
//import android.content.IntentFilter;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.graphics.Rect;
//import android.telephony.TelephonyManager;
//import android.util.Base64;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;




import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by KTE on 2015/4/14.
 */
public class CommonUtility {

    public static final String PARK_SUCCESS_ACTION = "com.zbtpark.parkingpay.park.success.acition";//停车成功
    public static final String TIMES_RECEIVER_ACTION = "com.zbtpark.parkingpay.times.acition";//停车记时
    public static final String TIMES_STOPSERVICE_ACTION = "com.zbtpark.parkingpay.stopservice.acition";//停止计时

    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
            }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                }
            } catch (IOException e) {
            e.printStackTrace();
            }


        return sb.toString();
    }
    public static String longDateToStr(long d){
        Date date = new Date(d);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 方法一：将char 强制转换为byte
     * @param ch
     * @return
     */
    public static byte charToByteAscii(char ch){
        byte byteAscii = (byte)ch;

        return byteAscii;
    }


    public static String revert(String code) throws UnsupportedEncodingException {

        byte [] num;
        byte [] c_num = new byte[code.length()];
        String revert_code;

        num = code.getBytes();

        for(int i=0; i<num.length; i++){
            c_num[i] = (byte) (num[i] - 48);
        }

        revert_code = new String(c_num,"UTF-8");

        return revert_code;
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 方法一：将byte 强制转换为char
     * @param b
     * @return
     */
    public static char byteAsciiToChar(byte b){
        char ch = (char) b;

        return ch;
    }



    //合并两个byte数组
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }
//    public static final String getIMEI(){
//        TelephonyManager mTelephonyMgr = (TelephonyManager) YueparkApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
//        String imei = mTelephonyMgr.getDeviceId();
//        if(imei==null || imei.equals("") || imei.length()<5)
//            imei = "000000000000";
//        return imei;
//    }
}
