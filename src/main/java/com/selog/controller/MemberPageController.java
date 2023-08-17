package com.selog.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.selog.dto.ArticleDto;
import com.selog.service.ArticleService;

@Controller
public class MemberPageController {

	
	@Autowired
	private ArticleService service;
	
	
	
	@GetMapping("/@{username}")
	public String showMemberPage(@PathVariable String username) {
		
		
		return "redirect:/fuck";
	}
	
	
	@GetMapping("/@{username}/{memberPageId}")
	public String showArticle(
		  @PathVariable String username 
		, @PathVariable int memberPageId
		, Model model) {
		
		
		Map<String, Object> uri = new HashMap<>();
			uri.put("username", username);
			uri.put("memberPageId", memberPageId);
	
		ArticleDto article = service.getArticleByUri(uri);
		model.addAttribute("article", article);
		
		return "/memberPage/articleView";
	}
}














