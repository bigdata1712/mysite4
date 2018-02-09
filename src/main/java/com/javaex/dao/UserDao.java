package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public int insertUser(UserVo userVo) {

		return sqlSession.insert("user.insert", userVo);
	}

	public UserVo selectUser(String email, String password) {
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("email", email);
		userMap.put("password", password);
		UserVo userVo = sqlSession.selectOne("user.selectUserByEmailPw", userMap);
		return userVo;
	}


	public UserVo selectUser(int no) {
		UserVo userVo = sqlSession.selectOne("user.selectUserByNo", no);
		return userVo;
	}
	
	public UserVo selectUser(String email) {
		UserVo userVo = sqlSession.selectOne("user.selectUserByEmail", email); 
		return userVo;
	}
	
	public int updateUser(UserVo userVo) {
		return sqlSession.update("user.update", userVo);
	}
	
}
