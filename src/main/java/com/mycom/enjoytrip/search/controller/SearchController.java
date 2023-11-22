package com.mycom.enjoytrip.search.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoytrip.search.dto.SearchAttractionBookmarkDto;
import com.mycom.enjoytrip.search.dto.SearchAttractionDto;
import com.mycom.enjoytrip.search.dto.SearchAttractionResultDto;
import com.mycom.enjoytrip.search.dto.SearchParamDto;
import com.mycom.enjoytrip.search.dto.SidoGugunDto;
import com.mycom.enjoytrip.search.service.SearchService;
import com.mycom.enjoytrip.user.dto.UserDto;

@RestController
public class SearchController {

	@Autowired
	SearchService service;

	@GetMapping(value = "/search/sidoList")
	public List<SidoGugunDto> sidoList() {
		return service.sidoList();
	}

	@GetMapping(value = "/search/gugunList/{sidoCode}")
	public List<SidoGugunDto> gugunList(@PathVariable int sidoCode) {
		return service.gugunList(sidoCode);
	}

	// 관광지 목록
	@GetMapping(value = "/search/attractionList")
	public SearchAttractionResultDto attractionList(SearchParamDto searchParamDto, HttpSession session) {

		// 사용자가 아닌 사람들도 검색할 수 있다.
		// 사용자인 경우에는 해당 유저의 북마크를 검사해서 표시한다.
		if ((UserDto) session.getAttribute("userDto") != null) {
			int userId = ((UserDto) session.getAttribute("userDto")).getUserId();
			// 현재 유저의 북마크 표시
			searchParamDto.setUserId(userId);
		}

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
				if (contentType) { // 시도, 구군, 분류코드
					searchAttractionResultDto = service.attractionListBySidoGugunContentTypeId(searchParamDto);
					System.out.println("시도, 구군, 분류코드");
				} else { // 시도, 구군
					searchAttractionResultDto = service.attractionListBySidoGugun(searchParamDto);
					System.out.println("시도, 구군");
				}
			} else if (contentType) { // 시도, 분류코드
				searchAttractionResultDto = service.attractionListBySidoContentTypeId(searchParamDto);
				System.out.println("시도, 분류코드");
			} else if (!contentType) { // 시도
				searchAttractionResultDto = service.attractionListBySido(searchParamDto);
				System.out.println("시도");
			}
		} else if (!sido && contentType) { // 분류코드
			searchAttractionResultDto = service.attractionListByContentTypeId(searchParamDto);
			System.out.println("분류코드");
		}

		return searchAttractionResultDto;
	}

	// 현재 유저가 북마크한 관광지 리스트 불러오기
	@GetMapping(value = "/search/bookmarkList/{userId}")
	public List<SearchAttractionBookmarkDto> bookmarkList(@PathVariable int userId) {
		System.out.println("/search/bookmarkList: " + userId);
		List<SearchAttractionBookmarkDto> list = service.getBookmarkList(userId);
		return list;
	}

	// 관광지 상세 정보
	@GetMapping(value = "/search/detail/{contentId}")
	public SearchAttractionDto attractionDetail(@PathVariable int contentId) {
		SearchAttractionDto searchAttractionDto = service.attractionDetail(contentId);
		return searchAttractionDto;
	}

	// 관광지 목록 랜덤
	@GetMapping(value = "/search/recommendList/{limit}/{contentTypeId}")
	public SearchAttractionResultDto recommendAttractionList(@PathVariable int limit, @PathVariable int contentTypeId) {
		return service.recommendAttractionList(limit, contentTypeId);
	}

}
