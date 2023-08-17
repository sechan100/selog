package com.selog.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.selog.dto.ArticleDto;
import com.selog.service.ArticleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ArticleController {

	
	@Autowired
	private ArticleService service;
	
	
	/*
	 * 게시글 페이지 처리.
	 */
	@GetMapping("/@{username}/{memberPageId}")
	public String showArticle(
		  @PathVariable String username 
		, @PathVariable int memberPageId
		, Model model) {
		
		
		// Map으로 uri를 전달. (map으로 전달해야 mybatis의 parameterType을 여러개 사용가능)
		Map<String, Object> uri = new HashMap<>();
			uri.put("username", username);
			uri.put("memberPageId", memberPageId);
			
			
		// uri를 통해서 매핑된 게시글 가져오기.
		ArticleDto article = service.getArticleByUri(uri);
		model.addAttribute("article", article);
		
		return "/memberPage/articleView";
	}

	
	/*
	 * 추천(likes) 처리 로직.
	 */
	@GetMapping("/@{username}/{memberPageId}/likes")
	public String doLikes(
		  @PathVariable String username 
		, @PathVariable int memberPageId
		
		, @RequestParam String action
		, @RequestParam String articleId
		
		, HttpServletRequest request
		, Model model) {
		
		
		// session 가져오기. (세션이 없다면 생성하지 않고 null 반환)
		HttpSession session = request.getSession(false);
		
		
		
		
		
		
		// 좋아요를 누른 경우.
		if(action.equals("like")){
			
			// VO 객체를 만들지 않고, Map으로 DB 전달.
			Map<String, Object> likesMap = new HashMap<>();
				likesMap.put("article_id", articleId);
//				likesMap.put("member_id", );
			
			service.doLike(likesMap);
			
			
			
		// 좋아요를 취소한 경우.
		} else {
			
			
			
			
		}
		
		Map<String, Object> uri = new HashMap<>();
			uri.put("username", username);	
			uri.put("memberPageId", memberPageId);
			
		ArticleDto article = service.getArticleByUri(uri);
		model.addAttribute("article", article);
		
		return String.format("redirect:/@%s/%d", username, memberPageId);
	}
}














