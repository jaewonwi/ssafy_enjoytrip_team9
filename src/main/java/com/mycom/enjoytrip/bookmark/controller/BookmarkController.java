package com.mycom.enjoytrip.bookmark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoytrip.bookmark.dto.BookmarkDto;
import com.mycom.enjoytrip.bookmark.service.BookmarkService;

@RestController
public class BookmarkController {
	@Autowired
	BookmarkService service;
	
	// 북마크 등록
	@PostMapping("/bookmarks")
	public Map<String, String> bookmarkInsert(@RequestBody BookmarkDto dto) {
		Map<String, String> map = new HashMap<>();
		if (service.bookmarkInsert(dto) == 1) {
			map.put("result", "success");
			return map;
		}
		
		map.put("result", "fail");
		return map;
	}

	// bookmarkId를 받아야 하나의 bookmark만 삭제할 수 있다. -> contentId만으로도 가능하다.
	// 북마크 삭제
	@DeleteMapping("/bookmarks/{userId}/{contentId}")
	public Map<String, String> bookmarkDelete(@PathVariable int userId, @PathVariable int contentId) {
		Map<String, String> map = new HashMap<>();
		if (service.bookmarkDelete(userId, contentId) == 1) {
			map.put("result", "success");
			return map;
		}
		map.put("result", "fail");
		return map;
	}
	
	// 해당 user가 선택한 bookmarkList를 가져온다.
	// bookmarkId, contentId, userId를 리턴한다.
	@GetMapping("/bookmarks/{userId}")
	public List<BookmarkDto> bookmarkList(@PathVariable("userId") int userId) {	// 북마크 목록
		return service.bookmarkList(userId);
	}
}
