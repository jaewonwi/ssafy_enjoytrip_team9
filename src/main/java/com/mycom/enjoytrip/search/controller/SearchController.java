package com.mycom.enjoytrip.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoytrip.search.dto.SearchAttractionDto;
import com.mycom.enjoytrip.search.dto.SearchAttractionResultDto;
import com.mycom.enjoytrip.search.dto.SearchParamDto;
import com.mycom.enjoytrip.search.dto.SidoGugunDto;
import com.mycom.enjoytrip.search.service.SearchService;

@RestController
public class SearchController {

	@Autowired
	SearchService service;
	
	@GetMapping(value="/sidoList")
	public List<SidoGugunDto> sidoList(){
		return service.sidoList();
	}
	
	@GetMapping(value="/gugunList/{sidoCode}")
	public List<SidoGugunDto> gugunList(@PathVariable int sidoCode){
		return service.gugunList(sidoCode);
	}
	

	// 관광지 목록
	@GetMapping(value="/attractionList")
	public SearchAttractionResultDto attractionList(SearchParamDto searchParamDto) {
        System.out.println("/attractionList");
		System.out.println(searchParamDto);
		
        SearchAttractionResultDto searchAttractionResultDto = new SearchAttractionResultDto();

		boolean sido = searchParamDto.getSidoCode() == 0 ? false : true;
		boolean gugun = searchParamDto.getGugunCode() == 0 ? false : true;
		boolean contentType = searchParamDto.getContentTypeId() == 0 ? false : true;
		
		int sidoCode = searchParamDto.getSidoCode();
		int gugunCode = searchParamDto.getGugunCode();
		int contentTypeId = searchParamDto.getContentTypeId();
		
		if (sido) {
			if (gugun) {
				if (contentType) {	// 시도, 구군, 분류코드
					searchAttractionResultDto = service.attractionListBySidoGugunContentTypeId(searchParamDto);
					System.out.println("시도, 구군, 분류코드");
				} else {			// 시도, 구군
					searchAttractionResultDto = service.attractionListBySidoGugun(searchParamDto);
					System.out.println("시도, 구군");
				}
			} else if (contentType) {	// 시도, 분류코드
				searchAttractionResultDto = service.attractionListBySidoContentTypeId(searchParamDto);
				System.out.println("시도, 분류코드");
			} else if (!contentType) {	// 시도
				searchAttractionResultDto = service.attractionListBySido(searchParamDto);
				System.out.println("시도");
			}
		} else if (!sido && contentType) {		// 분류코드
			searchAttractionResultDto = service.attractionListByContentTypeId(searchParamDto);
			System.out.println("분류코드");
		}

		return searchAttractionResultDto;
	}
	
	// 관광지 상세 정보
	@GetMapping(value="/detail/{contentId}")
	public SearchAttractionDto attractionDetail(@PathVariable int contentId) {
		SearchAttractionDto searchAttractionDto = service.attractionDetail(contentId);
		return searchAttractionDto;
	}
	
	
	
}
