package com.multi.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.multi.util.AES256Cipher;
import com.multi.util.EncryptUtil;

public class EncryptWrapper extends HttpServletRequestWrapper {

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		
		// 파라메터 이름에 따라 필터링을 가는 방법
		if(name.equals("password")) {
			value = EncryptUtil.oneWayEnc(value); // sha-256으로 일방향 암호화 수행하여 hashCode를 받아오는 과정
		} else if(name.equals("day")) {
			try {
				value = AES256Cipher.AES_Encode(value); // AES256을 통해 해석 가능한 암호화 수행
			} catch (Exception e) {}
		}
		
		return value;
	}

}
