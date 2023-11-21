package com.mycom.enjoytrip.user.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.enjoytrip.user.dao.UserDao;
import com.mycom.enjoytrip.user.dto.UserDto;
import com.mycom.enjoytrip.user.dto.UserProfileDto;
import com.mycom.enjoytrip.user.dto.UserResultDto;

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
	@Transactional
	public UserResultDto detail(String userEmail) {

		UserResultDto userResultDto = new UserResultDto();

		try {
			UserDto userDto = userDao.detail(userEmail);
			// userId를 이용해서 userProfileDto를 가져온다.
			UserProfileDto userProfileDto = userDao.profileDetail(userDto.getUserId());

			// userProfileDto를 userDto에 저장한다.
			userDto.setUserProfileDto(userProfileDto);

			// userDto를 userResultDto에 저장한다.
			userResultDto.setUserDto(userDto);
			userResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}

		return userResultDto;
	}

//	@Override
//	public int update(UserDto dto, int flag) {
//		if (flag == UPDATE_PWD) {
//			if (userDao.update(dto) == 1)
//				return UPDATE_PWD;
//			else
//				return FAIL;
//		} else {
//			System.out.println("비밀번호 수정 안함");
//			if (userDao.updateNoPwd(dto) == 1)
//				return NO_PWD;
//			else
//				return FAIL;
//		}
//	}

	@Override
	@Transactional
	public int delete(String userEmail) {
		UserDto userDto = userDao.detail(userEmail);
		int userId = userDto.getUserId();

		// userId를 이용해서 user_profile, 파일 삭제
		try {
			// 폴더에 저장된 기존 프로필 경로를 삭제
			String fileUrl = userDao.profileUrlSelect(userId);
//			String rollbackFileUrl = fileUrl;
			System.out.println("삭제시 fileUrl 확인: " + fileUrl);
			File tempFile = new File(uploadPath + File.separator + fileUrl);
			if (tempFile.exists()) {
				System.out.println("삭제시 fileUrl이 있는 경우");
				tempFile.delete();
			}

			// transactional 이용
			int profileRet = userDao.profileUrlDelete(userId);
			userDao.delete(userId);

			// 시간 없어서 구현 안함
//			if (profileRet != 1) {
//				// 파일 롤백!!
//				
//			}
			
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserServiceImpl: user_profile 또는 user 삭제 실패");
			return FAIL;
		}
	}

	@Override
	@Transactional
	public UserResultDto update(UserDto dto, MultipartHttpServletRequest request) {

//		File rollbackFile;
		String savingFileName = null;
		UserResultDto userResultDto = new UserResultDto();

		try {
			System.out.println("userProfile: " + request.getFiles("myFile").get(0));
			MultipartFile file = request.getFiles("myFile").get(0);
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			System.out.println("uploadDir: " + uploadDir);

			// 폴더가 없으면 해당 이름으로 폴더 생성
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
				System.out.println("uploadDir: " + uploadDir.mkdir());
			}

			// 폴더에 저장된 기존 프로필 경로를 삭제
			String fileUrl = userDao.profileUrlSelect(dto.getUserId());
			File tempFile = new File(uploadPath + File.separator + fileUrl);
			if (tempFile.exists()) {
				tempFile.delete();
			}

			// DB에 저장된 프로필 삭제
			userDao.profileUrlDelete(dto.getUserId());

			// 파일 이름
			String fileName = file.getOriginalFilename();
			System.out.println("fileName: " + fileName);
			UUID uuid = UUID.randomUUID();
			String extension = FilenameUtils.getExtension(fileName);
			savingFileName = uuid + "." + extension;
			File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

//			rollbackFile = destFile;

			file.transferTo(destFile);

			// DB저장
			UserProfileDto profileDto = new UserProfileDto();
			profileDto.setUserId(dto.getUserId());
			profileDto.setProfileNm(fileName);
			profileDto.setProfileSize(file.getSize());
			profileDto.setProfileContentType(file.getContentType());

			String profileUrl = uploadFolder + "/" + savingFileName;
			profileDto.setProfileUrl(profileUrl);

			userDao.profileInsert(profileDto);
			userResultDto.setResult(SUCCESS);
			userResultDto.setUserProfileDto(profileDto);

			// 무조건 모두 수정하도록: 경로 저장
			dto.setUserProfile(profileUrl);
			userDao.update(dto);

		} catch (Exception e) {
			e.printStackTrace();

			// rollback
			if (savingFileName != null) {
				File file = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				if (file.exists()) {
					file.delete();
				}
			}

			userResultDto.setResult(FAIL);
		}

		return userResultDto;
	}

}
