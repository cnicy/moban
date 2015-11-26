package com.gehua.moban.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
	/**
	 * 检查对象是否为空
	 * 
	 * @param obj
	 *            java任意类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String && (obj.toString().trim().equals(""))) {
			return true;
		} else if (obj instanceof Number && ((Number) obj).doubleValue() < 0) {
			return true;
		} else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Map && ((Map) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 检查n个对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object... obj) {
		boolean res = false;
		for (Object o : obj) {
			if (isEmpty(o)) {
				res = true;
				break;
			}
		}
		return res;
	}

	/**
	 * 检查对象是否不为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	/**
	 * 检查对象是否不为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object... obj) {
		boolean res = true;
		for (Object o : obj) {
			if (isEmpty(o)) {
				res = false;
			}
		}
		return res;
	}

	/**
	 * 克隆一个对象
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Object cloneObject(Object obj) throws Exception {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(obj);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(
				byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		return in.readObject();
	}

	public static boolean isNumeric(String str) {
		if (null != str && !"".equals(str.trim()) && str.matches("[0-9]+")) {
			return true;
		}
		return false;
	}

	public static boolean isNotNumeric(String str) {
		return !isNumeric(str);
	}

	public static boolean isNotNumeric(String... str) {
		boolean res = false;
		for (String s : str) {
			if (isNotNumeric(s)) {
				res = true;
				break;
			}
		}
		return res;
	}

	/**
	 * 判断传入字符串是否为数字类型
	 * 
	 * @param str
	 * @param flag
	 *            如果为true,则为小数，否则为整数
	 * @return
	 */
	public static boolean isNumeric(String str, boolean flag) {
		if (null == str || "".equals(str.trim())) {
			return false;
		}
		if (flag) {
			if (str.matches("[0-9]+.[0-9]+")) {
				return true;
			}
		} else {
			if (str.matches("[0-9]+")) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 判断传入字符串是否不是数字类型
	 * 
	 * @param str
	 * @param flag
	 *            如果为true,则为小数，否则为整数
	 * @return
	 */
	public static boolean isNotNumeric(String str, boolean flag) {
		return !isNumeric(str, flag);
	}

	/**
	 * 判断是否为中文
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
	
	public static boolean isChinese(String str){
		if(null!=str && str.length()>0){
			char[] chares = str.toCharArray();
			for(char c :chares){
				if(isChinese(c))
					return true;
			}
		}else
			return false;
		
		return false;
	} 

	/**
	 * 判断是否为中文乱码
	 * 
	 * @param strName
	 * @return
	 */
	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {

				if (!isChinese(c)) {
					count = count + 1;
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将16位byte[] 转换为32位String
	 * 
	 * @param buffer
	 * @return String
	 */
	public static String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 15, 16));
		}

		return sb.toString();
	}
	/**
	 * 生成六位随机数密码
	 * @return
	 */
	public static String getSixNum(){
		int[] rand=new int[6];
		StringBuffer numString=new StringBuffer();
		for(int i=0;i<rand.length;i++){
			rand[i]=(Integer)new Random().nextInt(9);
			numString.append(rand[i]);
		}
 		return numString.toString();
	}
	 public static String getMD5(byte[] source) {
		  String s = null;
		  char hexDigits[] = {       // 用来将字节转换成 16 进制表示的字符
		     '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f'}; 
		   try
		   {
		    java.security.MessageDigest md = java.security.MessageDigest.getInstance( "MD5" );
		    md.update( source );
		    byte tmp[] = md.digest();          // MD5 的计算结果是一个 128 位的长整数    // 用字节表示就是 16 个字节
		    char str[] = new char[16 * 2];   // 每个字节用 16 进制表示的话，使用两个字符，// 所以表示成 16 进制需要 32 个字符
		    int k = 0;                                // 表示转换结果中对应的字符位置
		    for (int i = 0; i < 16; i++) {          // 从第一个字节开始，对 MD5 的每一个字节// 转换成 16 进制字符的转换
		     byte byte0 = tmp[i];                 // 取第 i 个字节
		     str[k++] = hexDigits[byte0 >>> 4 & 0xf];  // 取字节中高 4 位的数字转换, // >>> 为逻辑右移，将符号位一起右移
		     str[k++] = hexDigits[byte0 & 0xf];            // 取字节中低 4 位的数字转换
		    } 
		    s = new String(str);                                 // 换后的结果转换为字符串
		   }catch( Exception e )
		   {
		    e.printStackTrace();
		   }
		   return s;
	 }

	 public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	 
	public static void main(String[] args) {
		Integer c = 0;
		System.out.println(CommonUtil.isNotEmpty(c));
	}
}