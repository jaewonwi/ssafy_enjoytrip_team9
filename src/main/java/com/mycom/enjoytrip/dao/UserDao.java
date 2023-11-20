package com.mycom.enjoytrip.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoytrip.dto.UserDto;

@Mapper
public interface UserDao {

	int regist(UserDto dto);
	UserDto detail(String userEmail);			// 마이페이지 내 정보
	int update(UserDto dto);
	int updateNoPwd(UserDto dto);
	int delete(int userId);				// 마이페이지 회원 탈퇴
	
}
