package com.mycom.enjoytrip.attraction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.enjoytrip.attraction.dao.AttractionDao;
import com.mycom.enjoytrip.attraction.dto.AttractionDto;

@Service
public class AttractionServiceImpl implements AttractionService {

	@Autowired
	AttractionDao dao;
	
	@Override
	public List<AttractionDto> sidoList() {
		return dao.sidoList();
	}

	@Override
	public List<AttractionDto> gugunList(int sidoCode) {
		List<AttractionDto> list = dao.gugunList(sidoCode);
		System.out.println(list);
		return list;
	}

	@Override
	public List<AttractionDto> pinList(int sidoCode, int gugunCode) {
		return dao.pinList(sidoCode, gugunCode);
	}

	@Override
	public AttractionDto detail(int contentId) {
		return dao.detail(contentId);
	}

}
