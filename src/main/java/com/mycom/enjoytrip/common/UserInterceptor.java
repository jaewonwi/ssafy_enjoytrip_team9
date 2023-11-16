package com.mycom.enjoytrip.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mycom.enjoytrip.dto.UserDto;

/* 일반 회원 Interceptor
 * group_code: 001 - 회원 구분
 * code: 002 - 일반 회원
 * 
 * WebMvcConfig에서 interceptor를 적용시킬 경로를 지정 
 */


// client가 login을 받으면 vue에서 login component로 보낸다. 그렇지 않으면 해당 페이지로 이동시킨다.
@Component
public class UserInterceptor implements HandlerInterceptor {

	// client가 request를 보내고 response를 내려준다.
	// json 문자열로 내려준다. "result":"general"
	private final String jsonStr = "{\"result\":\"general\"}";
	
	// DispatcherServlet에서 Controller로 들어가기 전에 호출 -> 로그인 체크
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("UserInterceptor >>> " + request.getRequestURI());
		
//		// JSP를 사용하지 않기 때문에 비동기 처리만 하면 된다.
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		
//		if (userDto == null) {
//			// login이 필요하다는 응답을 보내준다.
//			response.getWriter().write(jsonStr);
//			return false;		// 통과 불가
//		}
		
		return true;
	}
}
