package com.mycom.enjoytrip.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.enjoytrip.search.dto.SearchAttractionBookmarkDto;
import com.mycom.enjoytrip.search.dto.SearchAttractionDto;
import com.mycom.enjoytrip.search.dto.SearchParamDto;
import com.mycom.enjoytrip.search.dto.SidoGugunDto;

@Mapper
public interface SearchDao {
	// 시도, 구군 리스트
	List<SidoGugunDto> sidoList();
	List<SidoGugunDto> gugunList(int sidoCode);
	
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
	
	// 현재 유저의 북마크 리스트 목록
	List<SearchAttractionBookmarkDto> getBookmarkList(int userId);

	// 분류별 상위 관광지 추출
	List<SearchAttractionDto> recommendAttractionList(@Param("limit") int limit, @Param("contentTypeId") int contentTypeId);

}
