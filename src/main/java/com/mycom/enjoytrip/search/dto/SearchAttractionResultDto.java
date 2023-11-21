package com.mycom.enjoytrip.search.dto;

import java.util.List;

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
public class SearchAttractionResultDto {
	private int result;
	private SearchAttractionDto dto;
	private List<SearchAttractionDto> list;
	private int count;
}
