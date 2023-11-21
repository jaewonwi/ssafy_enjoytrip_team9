package com.mycom.enjoytrip.login.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoytrip.user.dto.UserDto;

@Mapper
public interface LoginDao {

	UserDto login(String userEmail);
	
}
