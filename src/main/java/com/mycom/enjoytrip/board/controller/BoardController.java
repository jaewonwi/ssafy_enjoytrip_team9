package com.mycom.enjoytrip.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.enjoytrip.board.dto.BoardDto;
import com.mycom.enjoytrip.board.dto.BoardParamDto;
import com.mycom.enjoytrip.board.dto.BoardResultDto;
import com.mycom.enjoytrip.board.service.BoardService;
import com.mycom.enjoytrip.dto.UserDto;

@RestController
// @CrossOrigin(origins="*", allowedHeaders="*")
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@GetMapping(value="/boards")
    public BoardResultDto boardList(BoardParamDto boardParamDto){
        
        BoardResultDto boardResultDto;

        if( boardParamDto.getSearchWord().isEmpty() ) {
            boardResultDto = service.boardList(boardParamDto);
        } else {
            boardResultDto = service.boardListSearchWord(boardParamDto);
        }
        
        return boardResultDto;
    }
	
    @GetMapping(value="/boards/{boardId}")
    public BoardResultDto boardDetail(@PathVariable int boardId, HttpSession session){

        BoardParamDto boardParamDto = new BoardParamDto();
        boardParamDto.setUserId( ((UserDto) session.getAttribute("userDto")).getUserId());
        boardParamDto.setBoardId(boardId);

        BoardResultDto boardResultDto = service.boardDetail(boardParamDto);
        // 게시글 작성자와 현 사용자가 동일
        if( ((UserDto) session.getAttribute("userDto")).getUserId() == boardResultDto.getDto().getUserId() ) {
            boardResultDto.getDto().setSameUser(true);
            System.out.println("같은사용자인가요?" + boardResultDto.getDto().isSameUser());
        }                
                
        return boardResultDto;     
    }
    
    @PostMapping(value="/boards")
//    public BoardResultDto boardInsert(BoardDto boardDto, MultipartHttpServletRequest request) {
      public BoardResultDto boardInsert(@RequestBody BoardDto boardDto) {
        
//        boardDto.setUserId( ((UserDto) request.getSession().getAttribute("userDto")).getUserId());
//        BoardResultDto boardResultDto = service.boardInsert(boardDto, request);
        BoardResultDto boardResultDto = service.boardInsert(boardDto);
        
        return boardResultDto;     
    }
    
    @PostMapping(value="/boards/{boardId}") 
//    public BoardResultDto boardUpdate(BoardDto boardDto, MultipartHttpServletRequest request){
    public BoardResultDto boardUpdate(@RequestBody BoardDto boardDto){
//        boardDto.setUserId( ((UserDto) request.getSession().getAttribute("userDto")).getUserId());
//        BoardResultDto boardResultDto = service.boardUpdate(boardDto, request);
    	System.out.println(boardDto.toString());
    	
        BoardResultDto boardResultDto = service.boardUpdate(boardDto);

        return boardResultDto;        
    }
    
    @DeleteMapping(value="/boards/{boardId}") 
    public BoardResultDto boardDelete(@PathVariable(value="boardId") int boardId){
        BoardResultDto boardResultDto = service.boardDelete(boardId);
        
        return boardResultDto;
    }
    
    @PostMapping(value="/boards/{boardId}/boardLike")
    public void boardLikeUpdate(@PathVariable int temp){

        System.out.println("가능?");

    }
}
