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
public class SearchAttractionDto {
	private int contentId;
	private int contentTypeId;
	
	private String title;
	private String addr1;
	private String addr2;
	private String firstImage;
	private int latitude;			// 위도
	private int longitude;			// 경도
	
	private String overview;		// 상세내용
	
	private String sidoName;
	private String gugunName;
	private int sidoCode;			// sido  key
	private int gugunCode;			// gugun key
	
	private String cat1;
	private String cat2;
	private String cat3;
	
}
