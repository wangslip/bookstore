package cn.wangslip.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	/** 可以把密码 加工 MD5形式的 */
	private   static String  md5Str(String password){
		// "a" + "b" + "c" + "d"
		StringBuffer  strBuf = new StringBuffer();
		try {
			MessageDigest  md =  MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			// 得到md5 形式的一个byte 数组  长度是16
			byte[] md5Bytes = md.digest();

			// 把每个byte 分成 2 个16进制数 再拼接成字符串
			for(int i=0;i<md5Bytes.length;i++){
				byte md5Byte = md5Bytes[i];
				// 1byte 是 8bit 但是 一个int 是 32bit  4bit可以表示一个16进制数
				String  tempStr = Integer.toHexString(md5Byte&0xff);
				//System.out.println(tempStr);
				if(tempStr.length()==1){
					tempStr = "0"+tempStr;
				}
				strBuf.append(tempStr);
			}
			return  strBuf.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	/** 可以把密码 加工 MD5形式的  并且加盐 */
	private   static String  md5StrAndSalt(String password,String salt){
		password = password + salt;
		// "a" + "b" + "c" + "d"
		StringBuffer  strBuf = new StringBuffer();
		try {
			MessageDigest  md =  MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			// 得到md5 形式的一个byte 数组  长度是16
			byte[] md5Bytes = md.digest();

			// 把每个byte 分成 2 个16进制数 再拼接成字符串
			for(int i=0;i<md5Bytes.length;i++){
				byte md5Byte = md5Bytes[i];
				// 1byte 是 8bit 但是 一个int 是 32bit  4bit可以表示一个16进制数
				String  tempStr = Integer.toHexString(md5Byte&0xff);
				//System.out.println(tempStr);
				if(tempStr.length()==1){
					tempStr = "0"+tempStr;
				}
				strBuf.append(tempStr);
			}
			return  strBuf.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	public static String getmd5password(String username,String password){
		String newname = Md5Util.md5StrAndSalt(username,password);
		String newpass = Md5Util.md5StrAndSalt(password,username);
		StringBuilder builder = new StringBuilder();
		builder.append(newname);
		for (int i = 0; i <newpass.length() ; i++) {
			if (i%2==0){
				String letter = newpass.substring(i,i+1);
				builder.replace(i,i+1,letter);
			}
		}
		return new String(builder);
	}
}
