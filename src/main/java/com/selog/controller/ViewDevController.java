package com.selog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.selog.dto.ArticleDto;
import com.selog.dto.MemberDto;
import com.selog.dto.MsgVo;
import com.selog.service.ArticleService;
import com.selog.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ViewDevController {

	/*
	 * msg develop page
	 */
	@GetMapping("/msg")
	public String dkfd(Model model) {
		
		MsgVo msg = new MsgVo();
			msg.setMsgTitle("이보람님, 회원가입이 완료되었습니다!");
			msg.setMsgContent("확인을 누르면 메인페이지로 이동합니다.");
			msg.setAimUrl("/");
			
			
			model.addAttribute("msg", msg);
		return "/util/msgPage";
	}


	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private MemberService memberService;
	
	
	// 메인 페이지.
	@GetMapping("/selog")
	public String sechanboardMainPage(Model model, HttpServletRequest request) {

		
		// 게시글 목록 불러오기.
		List<ArticleDto> articles = articleService.getArticles();
		
		
		
//		// 개발할 때, 서버 재시작 할 때마다 로그인하는 작업을 없애기 위해서 자동적으로 로그인.
//		MemberDto autoLoginMember =  memberService.getMemberByUsername("sechan");
//		HttpSession session = request.getSession();
//		session.setAttribute("user", autoLoginMember);
//		// #######################################
		
		
		model.addAttribute("articles", articles);

		return "/selog";
	}
}









