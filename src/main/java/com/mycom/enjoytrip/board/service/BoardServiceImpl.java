package com.mycom.enjoytrip.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.enjoytrip.board.dao.BoardDao;
import com.mycom.enjoytrip.board.dto.BoardDto;
import com.mycom.enjoytrip.board.dto.BoardParamDto;
import com.mycom.enjoytrip.board.dto.BoardResultDto;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao dao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
    @Transactional
//    public BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request) {
	public BoardResultDto boardInsert(BoardDto dto) {
        BoardResultDto boardResultDto = new BoardResultDto();
        
        try {
            dao.boardInsert(dto); // useGeneratedKeys="true" keyProperty="boardId"
            boardResultDto.setResult(SUCCESS);
            
        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }
        return boardResultDto;
    }

    @Override
    @Transactional
//    public BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request){
    public BoardResultDto boardUpdate(BoardDto dto){
        BoardResultDto boardResultDto = new BoardResultDto();
        
        try {
            dao.boardUpdate(dto);	// 게시글 업데이트
            boardResultDto.setResult(SUCCESS);
            
        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }
        
        return boardResultDto;
    }

    @Override
    @Transactional
    public BoardResultDto boardDelete(int boardId) {
        BoardResultDto boardResultDto = new BoardResultDto();
        
        try {
            dao.boardReadCountDelete(boardId);
            dao.boardLikeDelete(boardId);
            dao.boardDelete(boardId);        
            boardResultDto.setResult(SUCCESS);
            
        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }
        
        return boardResultDto;
    }

    @Override
    @Transactional
    public BoardResultDto boardDetail(BoardParamDto boardParamDto) {
        BoardResultDto boardResultDto = new BoardResultDto();
        
        try {
            int userReadCnt = dao.boardUserReadCount(boardParamDto);
            if( userReadCnt == 0 ) {
                dao.boardUserReadInsert(boardParamDto.getBoardId(), boardParamDto.getUserId());		// 조회할 때마다 조회수 증가(사용자 당 한번)
                dao.boardReadCountUpdate(boardParamDto.getBoardId());
            }
            
            BoardDto dto = dao.boardDetail(boardParamDto);
            boardResultDto.setDto(dto);
            boardResultDto.setResult(SUCCESS);
            
        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }
        
        return boardResultDto;
    }

    @Override
    public BoardResultDto boardList(BoardParamDto boardParamDto) {
        BoardResultDto boardResultDto = new BoardResultDto();
        
        try {
            List<BoardDto> list = dao.boardList(boardParamDto);
            int count = dao.boardListTotalCount();        
            boardResultDto.setList(list);
            boardResultDto.setCount(count);
            boardResultDto.setResult(SUCCESS);
            
        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }
        
        return boardResultDto;
    }

    @Override
    public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto) {
        
        BoardResultDto boardResultDto = new BoardResultDto();
        
        try {
            List<BoardDto> list = dao.boardListSearchWord(boardParamDto);
            int count = dao.boardListSearchWordTotalCount(boardParamDto);
            
            boardResultDto.setList(list);
            boardResultDto.setCount(count);
            
            boardResultDto.setResult(SUCCESS);
        
        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }
        
        return boardResultDto;
    }
    
    @Override
    public BoardResultDto boardLikeUpdate(BoardParamDto boardParamDto) {
    	BoardResultDto boardResultDto = new BoardResultDto();
    	
    	try {
            int userLikeCnt = dao.boardUserLikeCount(boardParamDto);
            if( userLikeCnt == 0 ) {
                dao.boardUserLikeInsert(boardParamDto.getBoardId(), boardParamDto.getUserId());		// 게시글 좋아요 증가(사용자 당 한번)
                dao.boardLikeUpdate(boardParamDto.getBoardId());
                
                boardResultDto.setResult(SUCCESS);
            }
    	} catch (Exception e) {
    		e.printStackTrace();
    		boardResultDto.setResult(FAIL);
    	}
    	
        return boardResultDto;
    }

}
