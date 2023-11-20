package com.mycom.enjoytrip.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoytrip.dto.UserDto;
import com.mycom.enjoytrip.service.UserService;

@RestController
//@CrossOrigin(origins="*", allowedHeaders="*")
public class UserController {
	
	@Autowired
	UserService userService;
	
	private static final int NO_PWD = -2;
	private static final int UPDATE_PWD = 2;

	@PostMapping("users")
	public int regist(@RequestBody UserDto dto) {
		System.out.println(dto);
		return userService.regist(dto);
	}
	
	@GetMapping("users/{userEmail}")
	public UserDto detail(@PathVariable String userEmail) {
		return userService.detail(userEmail);
	}
	
	@PutMapping("users")
	public Map<String, String> update(@RequestBody UserDto dto, HttpSession session) {
		System.out.println("UserController-update: " + dto);
		Map<String, String> map = new HashMap<>();
		if (dto.getUserPwd().length() >= 4) {
			if (userService.update(dto, UPDATE_PWD) == UPDATE_PWD) {
				UserDto userDto = detail(dto.getUserEmail());
				System.out.println("session에 저장할 userDto: " + userDto);
				session.setAttribute("userDto", userDto);
				
				map.put("result", "success");
				
				map.put("userNm", userDto.getUserNm());
				map.put("userPhone", userDto.getUserPhone());
				map.put("userEmail", userDto.getUserEmail());
				map.put("userProfileImageUrl", userDto.getUserProfile());
				map.put("userClsf", userDto.getUserClsf());
				return map;
			}
		} else if (dto.getUserPwd().length() == 0) {
			if (userService.update(dto, NO_PWD) == NO_PWD) {
				UserDto userDto = detail(dto.getUserEmail());
				System.out.println("session에 저장할 userDto: " + userDto);
				session.setAttribute("userDto", userDto);
				
				map.put("result", "success");
				map.put("noUpdatePwd", "ok");
				
				map.put("userNm", userDto.getUserNm());
				map.put("userPhone", userDto.getUserPhone());
				map.put("userEmail", userDto.getUserEmail());
				map.put("userProfileImageUrl", userDto.getUserProfile());
				map.put("userClsf", userDto.getUserClsf());
				return map;
			}
		}
		
		map.put("result", "fail");
		return map;
	}
	
	@DeleteMapping("/users/{userEmail}")
	public Map<String, String> delete(@PathVariable String userEmail, HttpSession session) {
		session.invalidate();
		Map<String, String> map = new HashMap<>();
		if (userService.delete(userEmail) == 1) {
			map.put("result", "success");
			return map;
		}
		
		map.put("result", "fail");
		return map;
	}
}
