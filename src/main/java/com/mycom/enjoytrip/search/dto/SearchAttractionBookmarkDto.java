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
public class SearchAttractionBookmarkDto {
	
	private int contentId;
	private String title;
	private String addr1;
	private String firstImage;
	
	private int bookmarkId;
	private int userId;
	
}