package com.mycom.enjoytrip.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.enjoytrip.dao.UserDao;
import com.mycom.enjoytrip.dto.UserDto;
import com.mycom.enjoytrip.dto.UserProfileDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Value("${app.fileupload.uploadPath}")
	String uploadPath;
	
	@Value("${app.fileupload.uploadFolder}")
	String uploadFolder;
	
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

	@Override
	public UserDto update(UserDto dto, MultipartHttpServletRequest request) {
		
		File rollbackFile;
		
		try {
			MultipartFile file = request.getFile("userProfileImageUrl");
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			
			// 폴더가 없으면 해당 이름으로 폴더 생성
			if (!uploadDir.exists()) uploadDir.mkdir();
			
			// 폴더에 저장된 기존 프로필 경로를 삭제
			// 칼럼만 삭제 -> update 사용
			
			// 파일 이름
			String fileName = file.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String extension = FilenameUtils.getExtension(fileName);
			String savingFileName = uuid + "." + extension;
			File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
			
			rollbackFile = destFile;
			
			file.transferTo(destFile);
			
			// DB저장
			private int profileId;
			private int userId;
			private String profileNm;
			private int profileSize;
			private String profileContentType;
			private String profileUrl;
			private LocalDateTime regDt;
			
			UserProfileDto profileDto = new UserProfileDto();
			profileDto.setUserId(dto.getUserId());
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
