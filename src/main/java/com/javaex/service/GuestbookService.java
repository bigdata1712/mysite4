package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> getGuestbookList(){
		return guestbookDao.selectGuestbookList();
	}
	
	public int write(GuestbookVo guestbookVo){
		return guestbookDao.insertGuestbook(guestbookVo);
	}
	
	public int remove(GuestbookVo guestbookVo){
		return guestbookDao.deleteGuestbook(guestbookVo);
	}

}
