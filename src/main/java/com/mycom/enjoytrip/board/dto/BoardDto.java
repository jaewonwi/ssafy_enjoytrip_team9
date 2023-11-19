package com.mycom.enjoytrip.board.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
public class BoardDto {
	
	private int boardId;
	private String boardTitle;
	private String boardContent;
	private LocalDateTime boardRegDate;
	private int boardLike;
	private int boardReadCount;
	private int userId;
	private String userEmail;
	private String userNm;
	private String userProfile;
	private boolean sameUser;
	
	// regDt setter
    public void setBoardRegDate(Date boardRegDate) {
        // lombok + mybatis mapping 충돌 방지
        this.boardRegDate = LocalDateTime.ofInstant(boardRegDate.toInstant(), ZoneId.systemDefault());        
    }
	
	
}
