package com.mycom.enjoytrip.user.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.enjoytrip.user.dto.UserDto;
import com.mycom.enjoytrip.user.dto.UserResultDto;
import com.mycom.enjoytrip.user.service.UserService;

@RestController
//@CrossOrigin(origins="*", allowedHeaders="*")
public class UserController {
	
	@Autowired
	UserService userService;
	
//	private static final int NO_PWD = -2;
//	private static final int UPDATE_PWD = 2;

	@PostMapping("/users")
	public int regist(@RequestBody UserDto dto) {
		System.out.println(dto);
		return userService.regist(dto);
	}
	
	@GetMapping("/users/{userEmail}")
	public UserResultDto detail(@PathVariable String userEmail) {
		// userResultDto - result, userDto, userProfileDto가 들어있다.
		UserResultDto userResultDto = userService.detail(userEmail);
		
		return userResultDto;
	}
	
	// 전화번호 비밀번호만 수정하는 코드
//	@PutMapping("users")
//	public Map<String, String> update(@RequestBody UserDto dto, HttpSession session) {
//		System.out.println("UserController-update: " + dto);
//		Map<String, String> map = new HashMap<>();
//		if (dto.getUserPwd().length() >= 4) {
//			if (userService.update(dto, UPDATE_PWD) == UPDATE_PWD) {
//				UserDto userDto = detail(dto.getUserEmail()).getUserDto();
//				System.out.println("session에 저장할 userDto: " + userDto);
//				session.setAttribute("userDto", userDto);
//				
//				map.put("result", "success");
//				
//				map.put("userNm", userDto.getUserNm());
//				map.put("userPhone", userDto.getUserPhone());
//				map.put("userEmail", userDto.getUserEmail());
//				map.put("userProfileImageUrl", userDto.getUserProfile());
//				map.put("userClsf", userDto.getUserClsf());
//				return map;
//			}
//		} else if (dto.getUserPwd().length() == 0) {
//			if (userService.update(dto, NO_PWD) == NO_PWD) {
//				UserDto userDto = detail(dto.getUserEmail()).getUserDto();
//				System.out.println("session에 저장할 userDto: " + userDto);
//				session.setAttribute("userDto", userDto);
//				
//				map.put("result", "success");
//				map.put("noUpdatePwd", "ok");
//				
//				map.put("userNm", userDto.getUserNm());
//				map.put("userPhone", userDto.getUserPhone());
//				map.put("userEmail", userDto.getUserEmail());
//				map.put("userProfileImageUrl", userDto.getUserProfile());
//				map.put("userClsf", userDto.getUserClsf());
//				return map;
//			}
//		}
//		
//		map.put("result", "fail");
//		return map;
//	}
	
	// 프로필, 전화번호, 비밀번호를 수정한다.
	@PostMapping("/users/profiles")
	public Map<String, String> updateProfile(UserDto dto, MultipartHttpServletRequest request) {
		System.out.println(dto);
		Map<String, String> map = new HashMap<>();
		
		if (dto.getUserPwd().length() == 0) {
			System.out.println("비밀번호를 입력하세요");
			map.put("result", "inputPwd");
			return map;
		}
		if (dto.getUserPhone().length() == 0) {
			System.out.println("전화번호를 입력하세요");
			map.put("result", "inputPhone");
			return map;
		}
		
		UserResultDto userResultDto = userService.update(dto, request);
		if (userResultDto.getResult() == 1) {
			map.put("result", "success");
			map.put("userNm", dto.getUserNm());
			map.put("userPhone", dto.getUserPhone());
			map.put("userEmail", dto.getUserEmail());
			map.put("userProfileImageUrl", userResultDto.getUserProfileDto().getProfileUrl());
		} else {
			map.put("result", "fail");
		}
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
	
	@GetMapping("users/userList")
	public UserResultDto userList() {
		return userService.userList();
	}
	

	@DeleteMapping("/users/admin/{userEmail}") 
	public Map<String, String> deleteOnlyAdmin(@PathVariable String userEmail, HttpSession session) {
		Map<String, String> map = new HashMap<>();

		System.out.println("관리자 >> 사용자 삭제!!!");
		
		if (userService.userDelete(userEmail) == 1) {
			map.put("result", "success");
			return map;
		}
		
		map.put("result", "fail");
		return map;
	}
}
