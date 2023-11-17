package com.mycom.enjoytrip.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoytrip.dto.UserDto;

@Mapper
public interface LoginDao {

	UserDto login(String userEmail);
	
}
