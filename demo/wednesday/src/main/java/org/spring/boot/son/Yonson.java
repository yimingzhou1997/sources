package org.spring.boot.son;

import java.util.Base64;

public class Yonson {
	public void syso() {
		String strEncoder = Base64.getEncoder().encodeToString("beat".getBytes());
		System.out.println(strEncoder);
		System.out.println(new String(Base64.getDecoder().decode(strEncoder)));
	}
}
