package one;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;

public class IdTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String source = "12345678";
		String encodes = new String(Base64.encodeBase64(source.getBytes()), "UTF-8");// encode
		System.out.println(encodes);// base64 encode½á¹û
		System.out.println(new String(Base64.decodeBase64(encodes.getBytes("UTF-8")), "UTF-8"));// base64
	}
}
