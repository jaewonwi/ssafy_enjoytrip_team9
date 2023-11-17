package com.mycom.enjoytrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.enjoytrip.dao.LoginDao;
import com.mycom.enjoytrip.dto.UserDto;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;

	@Override
	public UserDto login(UserDto dto) {
		UserDto userDto = loginDao.login(dto.getUserEmail());
		if (userDto != null && dto.getUserPwd().equals(dto.getUserPwd())) {
			
			// 비밀번호를 null 처리해서 보안 관리
			userDto.setUserPwd(null);
			
			return userDto;
		}
		return null;
	}

}
