package com.mycom.enjoytrip.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.enjoytrip.dto.UserDto;
import com.mycom.enjoytrip.dto.UserResultDto;

public interface UserService {
	
	int regist(UserDto dto);
	UserResultDto detail(String userEmail);			// 마이페이지 내 정보
//	int update(UserDto dto, int flag);		// 마이페이지 내 정보 수정
	UserResultDto update(UserDto dto, MultipartHttpServletRequest request);
	int delete(String userEmail);				// 마이페이지 회원 탈퇴

}
