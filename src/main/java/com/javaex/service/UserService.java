package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
	public int join(UserVo userVo) {
		return userDao.insertUser(userVo);
	}
	
	
	public UserVo getUser(String email, String password) {
		
		return userDao.selectUser(email, password);
	}
	
	public UserVo getUser(int no) {
		return userDao.selectUser(no);
	}
	
	public boolean emailCheck(String email) {
		boolean result; 
		UserVo userVo = userDao.selectUser(email);
		
		if(userVo != null) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}
	
	public int modifyUser(UserVo userVo) {
		return userDao.updateUser(userVo);
	}
	
}
