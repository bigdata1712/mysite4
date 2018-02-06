package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	
	public List<BoardVo> selectBoardList() {
		return sqlSession.selectList("board.selectList");
	}
	
	
	public int insertBoard(BoardVo boardVo) {
		return sqlSession.insert("board.insert", boardVo);
	}

	public int deleteBoard(BoardVo boardVo) {
		return sqlSession.delete("board.delete", boardVo);
	}
	
	
	public BoardVo selectBoard(int no) {
		return sqlSession.selectOne("board.selctBoard", no);
	}
	
	public int updateBoard(BoardVo boardVo) {
		return sqlSession.update("board.update", boardVo);
	}
	
	public int updateHit(int no) {
		return sqlSession.update("board.updateHit", no);
	}
	
}
