package com.mycom.enjoytrip.user.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResultDto {
	private int result;
	private UserProfileDto userProfileDto;
	private UserDto userDto;

	private List<UserDto> list;
}
