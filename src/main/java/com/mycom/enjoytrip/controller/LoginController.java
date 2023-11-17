package com.mycom.enjoytrip.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoytrip.dto.UserDto;
import com.mycom.enjoytrip.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;

	
	// UserDto: userEmail, userPwd
	@PostMapping(value="login")
	public Map<String, String> login(@RequestBody UserDto dto, HttpSession session) {
		
		System.out.println(dto);
		UserDto userDto = loginService.login(dto);
		Map<String, String> map = new HashMap<>();
		
		if (userDto != null) {
			session.setAttribute("userDto", userDto);
			
			// client가 필요한 정보
			// useLoginStore와 맞춘다.
			map.put("result", "success");
			map.put("userNm", userDto.getUserNm());
			map.put("userProfileImageUrl", userDto.getUserProfile());
			map.put("userEmail", userDto.getUserEmail());	// 내정보 수정할 때 필요한 정보
			
			return map;
		}
		
		map.put("result", "fail");
		return map;
	}
	
	@GetMapping(value="logout")
	public Map<String, String> logout(HttpSession session) {
		session.invalidate();
		
		Map<String, String> map = new HashMap<>();
		map.put("result", "success");	// 로그아웃 성공
		
		return map;
	}
}
