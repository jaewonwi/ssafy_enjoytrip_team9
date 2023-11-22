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
import com.mycom.enjoytrip.search.dto.SearchParamDto;
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
	public SearchAttractionDto attractionDetail(int contentId) {
		return dao.attractionDetail(contentId);
	}
	

	@Override
	public SearchAttractionResultDto attractionListBySido(SearchParamDto searchParamDto) {
		SearchAttractionResultDto searchAttractionResultDto = new SearchAttractionResultDto();
	   
	    try {
	        List<SearchAttractionDto> list = dao.attractionListBySido(searchParamDto);
 	        int count = dao.totalCountBySido(searchParamDto);        
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
	public SearchAttractionResultDto attractionListBySidoGugun(SearchParamDto searchParamDto) {
		SearchAttractionResultDto searchAttractionResultDto = new SearchAttractionResultDto();
		try {
	       List<SearchAttractionDto> list = dao.attractionListBySidoGugun(searchParamDto);
	       int count = dao.totalCountBySidoGugun(searchParamDto);        
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
	public SearchAttractionResultDto attractionListByContentTypeId(SearchParamDto searchParamDto) {
		SearchAttractionResultDto searchAttractionResultDto = new SearchAttractionResultDto();
		try {
	       List<SearchAttractionDto> list = dao.attractionListByContentTypeId(searchParamDto);
	       int count = dao.totalCountByContentTypeId(searchParamDto);    
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
	public SearchAttractionResultDto attractionListBySidoContentTypeId(SearchParamDto searchParamDto) {
		SearchAttractionResultDto searchAttractionResultDto = new SearchAttractionResultDto();
		try {
	       List<SearchAttractionDto> list = dao.attractionListBySidoContentTypeId(searchParamDto);
	       int count = dao.totalCountBySidoContentTypeId(searchParamDto);    
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
	public SearchAttractionResultDto attractionListBySidoGugunContentTypeId(SearchParamDto searchParamDto) {
		SearchAttractionResultDto searchAttractionResultDto = new SearchAttractionResultDto();
		try {
	       List<SearchAttractionDto> list = dao.attractionListBySidoGugunContentTypeId(searchParamDto);
	       int count = dao.totalCountBySidoGugunContentTypeId(searchParamDto);
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
	public SearchAttractionResultDto randomAttractionList(int limit) {
		SearchAttractionResultDto searchAttractionResultDto = new SearchAttractionResultDto();
		try {
			List<SearchAttractionDto> list = dao.randomAttractionList(limit);
			searchAttractionResultDto.setList(list);
			searchAttractionResultDto.setResult(SUCCESS);
	        
		} catch (Exception e) {
	       e.printStackTrace();
	       searchAttractionResultDto.setResult(FAIL);
		}

	    return searchAttractionResultDto;
	}

}
