package com.multi.mybatis.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class SqlSessionTemplate {

	private static SqlSessionFactory sf = null;
	
	public static SqlSession getSession() {
		SqlSession session = null;
		try {
			if(sf == null) {
				String resource = "mybatis-config.xml";
				InputStream is = Resources.getResourceAsStream(resource); // xml 파일을 로드하는 코드
				SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
				sf = sfb.build(is);
			}
			// false : 수동 커밋 지원하는 옵션 / true = auto commit
			session = sf.openSession(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}
}
