package com.mycom.enjoytrip.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoytrip.user.dto.UserDto;
import com.mycom.enjoytrip.user.dto.UserProfileDto;

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
	
	
	// 사용자 목록 (관리자 전용)
	List<UserDto> userList();
}
