package com.mycom.enjoytrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.enjoytrip.dao.UserDao;
import com.mycom.enjoytrip.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	public int regist(UserDto dto) {
		if (userDao.regist(dto) == 1)
			return SUCCESS;
		else
			return FAIL;
	}

	@Override
	public UserDto detail(String userEmail) {
		return userDao.detail(userEmail);
	}

	@Override
	public int update(UserDto dto) {
		if (userDao.update(dto) == 1)
			return SUCCESS;
		else
			return FAIL;
	}

	@Override
	public int delete(int userId) {
		return userDao.delete(userId);
	}

}
