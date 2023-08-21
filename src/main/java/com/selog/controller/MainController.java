package com.selog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.selog.dto.ArticleDto;
import com.selog.service.ArticleService;


@Controller
public class MainController {
	
	@Autowired
	private ArticleService articleService;
	
	
	/*
	 * main page
	 */
	@GetMapping("/selog")	
	public String selogMainPage(Model model) {
			
		// 게시글 목록 불러오기.
		List<ArticleDto> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);
		
		return "/selog";
	} 
	
}













