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
public class SidoGugunDto {
	private String sidoName;
	private String gugunName;
	private int sidoCode;			// sido  key
	private int gugunCode;			// gugun key
	
}
