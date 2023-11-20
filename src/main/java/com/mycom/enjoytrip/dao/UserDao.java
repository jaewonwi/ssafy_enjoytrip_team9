package com.mycom.enjoytrip.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoytrip.dto.UserDto;
import com.mycom.enjoytrip.dto.UserProfileDto;

@Mapper
public interface UserDao {

	int regist(UserDto dto);
	
	// 마이페이지 내 정보
	UserDto detail(String userEmail);			
	UserProfileDto profileDetail(int userId);
	
	int update(UserDto dto);
	
	
	// int updateNoPwd(UserDto dto);
	int delete(int userId);				// 마이페이지 회원 탈퇴
	
	// 프로필 관련
	String profileUrlSelect(int userId);
	int profileUrlDelete(int userId);
	int profileInsert(UserProfileDto userProfileDto);

	
	// 로그인할 때는 필요없다.
}
