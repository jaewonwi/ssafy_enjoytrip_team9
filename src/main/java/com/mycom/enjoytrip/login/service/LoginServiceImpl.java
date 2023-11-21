package com.mycom.enjoytrip.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.enjoytrip.login.dao.LoginDao;
import com.mycom.enjoytrip.user.dto.UserDto;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;

	@Override
	public UserDto login(UserDto dto) {
		UserDto userDto = loginDao.login(dto.getUserEmail());
		if (userDto != null && dto.getUserPwd().equals(userDto.getUserPwd())) {
			
			// 비밀번호를 null 처리해서 보안 관리
			userDto.setUserPwd(null);
			
			return userDto;
		}
		System.out.println("LoginService(login): userDto가 null");
		return null;
	}

}
