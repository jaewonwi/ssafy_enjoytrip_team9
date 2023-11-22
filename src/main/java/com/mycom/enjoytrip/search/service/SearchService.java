package com.mycom.enjoytrip.search.service;

import java.util.List;

import com.mycom.enjoytrip.search.dto.SearchAttractionDto;
import com.mycom.enjoytrip.search.dto.SearchAttractionResultDto;
import com.mycom.enjoytrip.search.dto.SearchParamDto;
import com.mycom.enjoytrip.search.dto.SidoGugunDto;

public interface SearchService {
	// 시도, 구군 리스트
	List<SidoGugunDto> sidoList();
	List<SidoGugunDto> gugunList(int sidoCode);
	
//	// 관광지 목록
//	SearchAttractionResultDto attractionListBySido(int sidoCode);															// 시도
//	SearchAttractionResultDto attractionListBySidoGugun(int sidoCode, int gugunCode);										// 시도, 구군
//	SearchAttractionResultDto attractionListByContentTypeId(int contentTypeId);												// 분류코드
//	SearchAttractionResultDto attractionListBySidoContentTypeId(int sidoCode, int contentTypeId);							// 시도, 분류코드		
//	SearchAttractionResultDto attractionListBySidoGugunContentTypeId(int sidoCode, int gugunCode, int contentTypeId);		// 시도, 구군, 분류코드
	
	SearchAttractionDto attractionDetail(int contentId); // 관광지 상세정보

	// 관광지 목록
	SearchAttractionResultDto attractionListBySido(SearchParamDto searchParamDto);						// 시도
	SearchAttractionResultDto attractionListBySidoGugun(SearchParamDto searchParamDto);					// 시도, 구군
	SearchAttractionResultDto attractionListByContentTypeId(SearchParamDto searchParamDto);				// 분류코드
	SearchAttractionResultDto attractionListBySidoContentTypeId(SearchParamDto searchParamDto);			// 시도, 분류코드		
	SearchAttractionResultDto attractionListBySidoGugunContentTypeId(SearchParamDto searchParamDto);		// 시도, 구군, 분류코드

	// 관광지 목록 랜덤
	SearchAttractionResultDto randomAttractionList(int limit);
}
