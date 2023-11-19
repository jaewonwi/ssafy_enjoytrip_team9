package com.mycom.enjoytrip.service;

import com.mycom.enjoytrip.dto.UserDto;

public interface UserService {
	
	int regist(UserDto dto);
	UserDto detail(String userEmail);			// 마이페이지 내 정보
	int update(UserDto dto);		// 마이페이지 내 정보 수정
	int delete(int userId);				// 마이페이지 회원 탈퇴

}
