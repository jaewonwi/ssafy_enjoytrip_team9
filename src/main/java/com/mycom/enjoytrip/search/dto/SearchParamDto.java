package com.mycom.enjoytrip.search.dto;

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
public class SearchParamDto {
	private int limit;
	private int offset;
	private String searchWord;
	
	private int sidoCode;
	private int gugunCode;
	private int contentTypeId;
	
	// 현재 사용자를 가져와서 북마크 표시를 남기기 위함
	private int userId;
}
