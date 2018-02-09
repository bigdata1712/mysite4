package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/gb/api")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public List<GuestbookVo> apiList(@RequestParam("page") int page) {
		List<GuestbookVo> guestbookList = guestbookService.getGuestbookListPage(page);
		return guestbookList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public GuestbookVo add(@ModelAttribute GuestbookVo guestbookVo) {
		
		GuestbookVo vo = guestbookService.writeGetVo(guestbookVo);
		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@RequestBody GuestbookVo guestbookVo) {
		
		int count = guestbookService.remove(guestbookVo);
		System.out.println(count);
		if(count != 0) { //DB삭제 성공 PK값을 보낸다.
			return guestbookVo.getNo(); 
		}else {          //실패시 -1 을 보낸다.
			return -1 ;
		}
	}

}
