package com.mycom.enjoytrip.user.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
public class UserProfileDto { 
	private int profileId;
	private int userId;
	private String profileNm;
	private long profileSize;
	private String profileContentType;
	private String profileUrl;
	private LocalDateTime regDt;
	
	public void setBoardRegDate(Date regDt) {
        // lombok + mybatis mapping 충돌 방지
        this.regDt = LocalDateTime.ofInstant(regDt.toInstant(), ZoneId.systemDefault());        
    }
}
