package com.mycom.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

	private int userId;
	private String userEmail;
	private String userPwd;
	private String userNm;
	private String userPhone;
	private String userProfile;
	
	// 회원 구분( 일반 회원(001, 002), 관리자(001, 001) )
	private String userClsf;
	private String userClsfName;
	
	// 프로필 저장
	UserProfileDto userProfileDto;
	
}
