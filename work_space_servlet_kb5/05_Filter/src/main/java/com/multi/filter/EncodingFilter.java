package com.multi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 필터란?
 * 서블릿이 호출되기 전과 그리고 응답 후에 Stream을 특수처리(필터링) 하기 위해 사용되는 클래스
 */

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*") // 모든 페이지 필터링
public class EncodingFilter extends HttpFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// filter가 생성될때 한번만 호출되는 메소드
		System.out.println(filterConfig.getFilterName() + "가 생성됨!");
	}

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 실제 필터링되는 영역
		System.out.println("EncodingFilter가 호출됨!!");

		// post 방식일때만 필터링 하는 방법
		if(req.getMethod().equals("POST")) {
			System.out.println("method : " + req.getMethod());
			// get방식은 처리되지 않는 영역
		}

		// 모든 요청과 응답 character-set utf-8로 변경
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");

		// 서블릿 호출 이전에 처리 할 필터가 오는 자리
		chain.doFilter(req, res); // 다음 필터가 있다면 필터로 연결하고, 필터가 없는 경우 서블릿을 호출하는 코드
		// 서블릿 호출 이후에 처리 할 필터가 오는 자리

		System.out.println("EncodingFilter가 끝나는 영역!");
	}

	@Override
	public void destroy() {
		// 필터가 소멸될때 호출되는 메소드
		System.out.println("필터 소멸됨");
	}
}
