package com.mycom.enjoytrip.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mycom.enjoytrip.user.dto.UserDto;

/* 관리자 interceptor
 * group_code : 001 - 회원 구분
 * code : 001 - 관리자
 */

@Component
public class AdminInterceptor implements HandlerInterceptor {
	
	private final String adminCode = "001";
	private final String jsonStr = "{\"result\":\"admin\"}";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("AdminInterceptor >>> " + request.getRequestURI());
		
		// CORS 에서  put, delete 등 오류 해결 코드
		if (request.getMethod().equals("OPTIONS")) {
			return true;
		}
		
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		if (userDto == null) {
			// login이 필요하다는 응답을 보내준다.
			System.out.println("userDto가 null 입니다.");
			response.getWriter().write(jsonStr);
			return false;
		}
		
		return true;
	}
}
