package com.mycom.enjoytrip.board.service;

import com.mycom.enjoytrip.board.dto.BoardDto;
import com.mycom.enjoytrip.board.dto.BoardParamDto;
import com.mycom.enjoytrip.board.dto.BoardResultDto;


public interface BoardService {

	BoardResultDto boardDetail(BoardParamDto boardParamDto);
    
    BoardResultDto boardDelete(int boardId);
    
//    BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request);
    BoardResultDto boardUpdate(BoardDto dto);

//    BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request);
    BoardResultDto boardInsert(BoardDto dto);

    BoardResultDto boardList(BoardParamDto boardParamDto);
    //int boardListTotalCount();    
    BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);
    //int boardListSearchWordTotalCount(boardParamDto boardParamDto);
    
}
