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
		System.out.println("selectKey 실행전" + guestbookVo.toString());
		sqlSession.insert("guestbook.insert", guestbookVo);
		System.out.println("selectKey 실행후" + guestbookVo.toString());
		return guestbookVo.getNo();
	}
	
	public int deleteGuestbook(GuestbookVo guestbookVo){ 
		return sqlSession.delete("guestbook.delete", guestbookVo);
	}
	
	
	public List<GuestbookVo> selectGuestbookListPage(int page){
		return sqlSession.selectList("guestbook.selectListByPage", page);
	}
	
	public GuestbookVo selectGuestBook(int no){
		return sqlSession.selectOne("guestbook.selectGuestBook", no);	
	}
	
}
