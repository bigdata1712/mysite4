package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getBoardList(){
		return boardDao.selectBoardList();
	}
	
	public int write(BoardVo boardVo) {
		return boardDao.insertBoard(boardVo);
	}
	
	public int remove(BoardVo boardVo) {
		return boardDao.deleteBoard(boardVo);
	}
	
	public BoardVo getBoardForRead(int no) {
		BoardVo boardVo = boardDao.selectBoard(no);
		boardDao.updateHit(no);
		return boardVo;
	}
	
	public BoardVo getBoardForModify(int no) {
		BoardVo boardVo = boardDao.selectBoard(no);
		return boardVo;
	}
	
	public int modifyBoard(BoardVo boardVo) {
		return boardDao.updateBoard(boardVo);
	}
}
