package com.Mintimate.tool;
//Md5加密类
//传入String类型，返回加密后字符串（以String类型返回）
import java.security.MessageDigest;

public class Md5 {
    //测试方法
    public static void main(String args[]){
        //输出Md5加密后的Hello
        System.out.println(Md5.Md5("Hello"));
    }
    public static String Md5(String str){
        String Md=null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            Md=toHex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Md;
    }
    private static String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 15, 16));
        }

        return sb.toString();
    }
}
