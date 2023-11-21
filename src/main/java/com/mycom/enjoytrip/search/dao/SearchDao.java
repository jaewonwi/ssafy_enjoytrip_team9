package com.mycom.enjoytrip.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoytrip.search.dto.SearchAttractionDto;
import com.mycom.enjoytrip.search.dto.SearchParamDto;
import com.mycom.enjoytrip.search.dto.SidoGugunDto;

@Mapper
public interface SearchDao {
	// 시도, 구군 리스트
	List<SidoGugunDto> sidoList();
	List<SidoGugunDto> gugunList(int sidoCode);
	
//	// 관광지 목록
//	List<SearchAttractionDto> attractionListBySido();				// 시도
//	List<SearchAttractionDto> attractionListBySidoGugun(
//						@Param("sidoCode") int sidoCode,
//						@Param("gugunCode") int gugunCode);						// 시도, 구군
//	List<SearchAttractionDto> attractionListByContentTypeId(int contentTypeId);		// 분류코드
//	List<SearchAttractionDto> attractionListBySidoContentTypeId(
//						@Param("sidoCode") int sidoCode,
//						@Param("contentTypeId") int contentTypeId);				// 시도, 분류코드		
//	List<SearchAttractionDto> attractionListBySidoGugunContentTypeId(
//						@Param("sidoCode") int sidoCode,
//						@Param("gugunCode") int gugunCode,
//						@Param("contentTypeId") int contentTypeId);				// 시도, 구군, 분류코드
//	
//	// 관광지 목록의 총 개수
//	int totalCountBySido(int sidoCode);									// 시도
//	int totalCountBySidoGugun(
//						@Param("sidoCode") int sidoCode,
//						@Param("gugunCode") int gugunCode);				// 시도, 구군
//	int totalCountByContentTypeId(int contentTypeId);					// 분류코드
//	int totalCountBySidoContentTypeId(
//						@Param("sidoCode") int sidoCode,
//						@Param("contentTypeId") int contentTypeId);		// 시도, 분류코드
//	int totalCountBySidoGugunContentTypeId(
//						@Param("sidoCode") int sidoCode,
//						@Param("gugunCode") int gugunCode,
//						@Param("contentTypeId") int contentTypeId);		// 시도, 구군, 분류코드


	SearchAttractionDto attractionDetail(int contentId); // 관광지 상세정보
	

	
	// 관광지 목록
	List<SearchAttractionDto> attractionListBySido(SearchParamDto searchParamDto);							// 시도
	List<SearchAttractionDto> attractionListBySidoGugun(SearchParamDto searchParamDto);						// 시도, 구군
	List<SearchAttractionDto> attractionListByContentTypeId(SearchParamDto searchParamDto);					// 분류코드
	List<SearchAttractionDto> attractionListBySidoContentTypeId(SearchParamDto searchParamDto);				// 시도, 분류코드		
	List<SearchAttractionDto> attractionListBySidoGugunContentTypeId(SearchParamDto searchParamDto);			// 시도, 구군, 분류코드
	
	// 관광지 목록의 총 개수
	int totalCountBySido(SearchParamDto searchParamDto);							// 시도
	int totalCountBySidoGugun(SearchParamDto searchParamDto);						// 시도, 구군
	int totalCountByContentTypeId(SearchParamDto searchParamDto);					// 분류코드
	int totalCountBySidoContentTypeId(SearchParamDto searchParamDto);				// 시도, 분류코드
	int totalCountBySidoGugunContentTypeId(SearchParamDto searchParamDto);		// 시도, 구군, 분류코드

}
