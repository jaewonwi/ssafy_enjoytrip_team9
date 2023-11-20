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
	private static final int NO_PWD = -2;
	private static final int UPDATE_PWD = 2;

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
	public int update(UserDto dto, int flag) {
		if (flag == UPDATE_PWD) {
			if (userDao.update(dto) == 1)
				return UPDATE_PWD;
			else
				return FAIL;
		} else {
			System.out.println("비밀번호 수정 안함");
			if (userDao.updateNoPwd(dto) == 1)
				return NO_PWD;
			else
				return FAIL;
		}
	}

	@Override
	public int delete(String userEmail) {
		int userId = detail(userEmail).getUserId();
		return userDao.delete(userId);
	}

}
