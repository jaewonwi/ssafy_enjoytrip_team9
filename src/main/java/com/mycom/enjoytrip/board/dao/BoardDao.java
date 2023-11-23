package com.mycom.enjoytrip.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.enjoytrip.board.dto.BoardDto;
import com.mycom.enjoytrip.board.dto.BoardParamDto;

@Mapper
public interface BoardDao {
	int boardInsert(BoardDto dto);				// 게시글 등록

	// 게시글 목록
    List<BoardDto> boardList(BoardParamDto boardParamDto);
    int boardListTotalCount();
   
    List<BoardDto> boardListSearchWord(BoardParamDto boardParamDto);
    int boardListSearchWordTotalCount(BoardParamDto boardParamDto);

	// 게시글 하나 조회
	BoardDto boardDetail(BoardParamDto boardParamDto);
	
	// map - Dto
    int boardUserReadCount(BoardParamDto boardParamDto); 
   
    // map - @param
    int boardUserReadInsert(
           @Param("boardId") int boardId, 
           @Param("userId") int userId );
    int boardReadCountUpdate(int boardId);
    
    int boardDelete(int boardId);				// 게시글 삭제    
    int boardReadCountDelete(int boardId);		// 게시글 조회수도 삭제 
    
    int boardUpdate(BoardDto dto);
}
