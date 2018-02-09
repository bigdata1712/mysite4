package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/gb")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<GuestbookVo> list = guestbookService.getGuestbookList();
		model.addAttribute("list", list);
		model.addAttribute("newLine", "\r\n"); //줄바꿈 문자 치환용
		return "guestbook/list";
	}
	
	@RequestMapping(value="/write")
	public String write(@ModelAttribute GuestbookVo guestbookVo){
		guestbookService.write(guestbookVo);
		return "redirect:/gb/list";
	}
	
	@RequestMapping(value="/deleteform")
	public String deleteform(){
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/remove")
	public String remove(@ModelAttribute GuestbookVo guestbookVo){
		guestbookService.remove(guestbookVo);
		return "redirect:/gb/list";
	}
	
	@RequestMapping(value="/listajax", method=RequestMethod.GET)
	public String listajax() {
		return "guestbook/listajax";
	}
	
	
	
	
}
