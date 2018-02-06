package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/joinform", method=RequestMethod.GET)
	public String joinForm() {
		
		return "user/joinform";
	}
	

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		userService.join(userVo);
		return "user/joinsuccess";
	}
	
	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginForm() {
		
		return "user/loginform";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,  HttpSession session) {
		UserVo authUser =  userService.getUser(email, password);
		
		if(authUser != null) { //로그인 성공 이면 세션에 정보를 저장한후 메인으로 리다이렉트
			session.setAttribute("authUser", authUser);
			return "redirect:/main"; 
		}else { //로그인 실패면 로그인 페이지로 리다이렉트(실패 메세지 출력)
			return "redirect:/user/loginform?result=fail";
		}
	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET )
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main";
	}
	
	
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(HttpSession session, Model model) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		
		UserVo userVo = userService.getUser(no);
		model.addAttribute("userVo", userVo);
		return "user/modifyform";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		userVo.setNo(no);

		userService.modifyUser(userVo);
		
		authUser.setName(userVo.getName());
		
		return "redirect:/main";
	}
	
}
