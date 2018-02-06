package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> selectGuestbookList(){
		return sqlSession.selectList("guestbook.selectList");
	}
	
	public int insertGuestbook(GuestbookVo guestbookVo){ 
		return sqlSession.insert("guestbook.insert", guestbookVo);
	}
	
	public int deleteGuestbook(GuestbookVo guestbookVo){ 
		return sqlSession.delete("guestbook.delete", guestbookVo);
	}
}
