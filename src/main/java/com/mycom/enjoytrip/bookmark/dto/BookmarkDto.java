package com.mycom.enjoytrip.bookmark.dto;

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
public class BookmarkDto {
	private int bookmarkId;
	private int userId;
	private int contentId;
}
