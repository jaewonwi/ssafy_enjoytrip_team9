package com.mycom.enjoytrip.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.enjoytrip.board.dto.BoardDto;
import com.mycom.enjoytrip.board.dto.BoardResultDto;
import com.mycom.enjoytrip.search.dao.SearchDao;
import com.mycom.enjoytrip.search.dto.SearchAttractionDto;
import com.mycom.enjoytrip.search.dto.SearchAttractionResultDto;
import com.mycom.enjoytrip.search.dto.SidoGugunDto;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchDao dao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public List<SidoGugunDto> sidoList() {
		List<SidoGugunDto> list = dao.sidoList();
		System.out.println("sidoList"+list.toString());
		return list;
	}

	@Override
	public List<SidoGugunDto> gugunList(int sidoCode) {
		List<SidoGugunDto> list = dao.gugunList(sidoCode);
		System.out.println("gugunList"+list.toString());
		return list;
	}

	@Override
	public SearchAttractionResultDto attractionListBySido(int sidoCode) {
		SearchAttractionResultDto searchAttractionResultDto = new SearchAttractionResultDto();
	   
	    try {
	        List<SearchAttractionDto> list = dao.attractionListBySido(sidoCode);
 	        int count = dao.totalCountBySido(sidoCode);        
	        searchAttractionResultDto.setList(list);
	        searchAttractionResultDto.setCount(count);
	        searchAttractionResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        searchAttractionResultDto.setResult(FAIL);
	    }
	    
	    return searchAttractionResultDto;
	}

	@Override
	public SearchAttractionResultDto attractionListBySidoGugun(int sidoCode, int gugunCode) {
		SearchAttractionResultDto searchAttractionResultDto = new SearchAttractionResultDto();
		try {
	       List<SearchAttractionDto> list = dao.attractionListBySidoGugun(sidoCode, gugunCode);
	       int count = dao.totalCountBySidoGugun(sidoCode, gugunCode);        
	       searchAttractionResultDto.setList(list);
	       searchAttractionResultDto.setCount(count);
	       searchAttractionResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	       e.printStackTrace();
	       searchAttractionResultDto.setResult(FAIL);
	    }
	    
	    return searchAttractionResultDto;
	}

	@Override
	public SearchAttractionResultDto attractionListByContentTypeId(int contentTypeId) {
		SearchAttractionResultDto searchAttractionResultDto = new SearchAttractionResultDto();
		try {
	       List<SearchAttractionDto> list = dao.attractionListByContentTypeId(contentTypeId);
	       int count = dao.totalCountByContentTypeId(contentTypeId);    
	       searchAttractionResultDto.setList(list);
	       searchAttractionResultDto.setCount(count);
	       searchAttractionResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	       e.printStackTrace();
	       searchAttractionResultDto.setResult(FAIL);
	    }
	    
	    return searchAttractionResultDto;
	}

	@Override
	public SearchAttractionResultDto attractionListBySidoContentTypeId(int sidoCode, int contentTypeId) {
		SearchAttractionResultDto searchAttractionResultDto = new SearchAttractionResultDto();
		try {
	       List<SearchAttractionDto> list = dao.attractionListBySidoContentTypeId(sidoCode, contentTypeId);
	       int count = dao.totalCountBySidoContentTypeId(sidoCode, contentTypeId);    
	       searchAttractionResultDto.setList(list);
	       searchAttractionResultDto.setCount(count);
	       searchAttractionResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	       e.printStackTrace();
	       searchAttractionResultDto.setResult(FAIL);
	    }
	    
	    return searchAttractionResultDto;
	}

	@Override
	public SearchAttractionResultDto attractionListBySidoGugunContentTypeId(int sidoCode, int gugunCode, int contentTypeId) {
		SearchAttractionResultDto searchAttractionResultDto = new SearchAttractionResultDto();
		try {
	       List<SearchAttractionDto> list = dao.attractionListBySidoGugunContentTypeId(sidoCode, gugunCode, contentTypeId);
	       int count = dao.totalCountBySidoGugunContentTypeId(sidoCode, gugunCode, contentTypeId);
	       searchAttractionResultDto.setList(list);
	       searchAttractionResultDto.setCount(count);
	       searchAttractionResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	       e.printStackTrace();
	       searchAttractionResultDto.setResult(FAIL);
	    }
	    
	    return searchAttractionResultDto;
	}

	
	@Override
	public SearchAttractionDto attractionDetail(int contentId) {
		return dao.attractionDetail(contentId);
	}

}