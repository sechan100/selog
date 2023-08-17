package com.selog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.selog.dto.ArticleDto;
import com.selog.dto.MsgDtoBuilder;
import com.selog.service.ArticleService;
import com.selog.temporaryloginmodule.LoginedMemberFactory;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ViewDevController {

	/*
	 * msg develop page
	 */
	@GetMapping("/msg")
	public String dkfd(Model model) {

		model.addAttribute("msg",
			new MsgDtoBuilder()
				.addMsgTitle("이보람님, 회원가입이 완료되었습니다!")
				.addmsgContent("확인을 누르면 메인페이지로 이동합니다.")
				.addAimUrl("/"));
		return "/util/msgPage";
	}


	@Autowired
	private ArticleService service;
	
	
	/*
	 * main article page
	 */
	@GetMapping("/selog")
	public String sechanboardMainPage(Model model, HttpServletRequest request) {

		
		// articles list
		List<ArticleDto> articles = service.getArticles();

		
		model.addAttribute("loginedMember", LoginedMemberFactory.getLoginedMember());
		model.addAttribute("articles", articles);

		return "/selog";
	}
}









