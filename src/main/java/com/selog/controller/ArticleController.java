package com.selog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.selog.dto.ArticleDto;
import com.selog.dto.MemberDto;
import com.selog.dto.MsgVo;
import com.selog.service.ArticleService;
import com.selog.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ArticleController {

	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private MemberService memberService;
	
	
	/*
	 * 게시글 뷰.
	 */
	@GetMapping("/@{username}/{memberPageId}")
	public String showArticle(
		  @PathVariable String username 
		, @PathVariable int memberPageId
		, HttpServletRequest request
		, Model model) {
		
		
		// Map으로 uri를 전달. (map으로 전달해야 mybatis의 parameterType을 여러개 사용가능)
		Map<String, Object> uri = new HashMap<>();
			uri.put("username", username);
			uri.put("memberPageId", memberPageId);
			
		// uri를 통해서 매핑된 게시글 가져오기.
		ArticleDto article = articleService.getArticleByUri(uri);
		

		HttpSession session = request.getSession();	
		
		boolean isLiked = false;
		
		// 로그인 상태라면 게시글에 대한 좋아요 정보를 가져온다.
		if(session.getAttribute("user") != null){
			
			@SuppressWarnings("unchecked")
			List<Map<String, Integer>> likes =  (List<Map<String, Integer>>) session.getAttribute("likes");
			
			if(likes != null) {
				for(Map<String, Integer> like : likes){
					
					// [좋아요를 누른 적이 있다면]: 게시글 아이디가 같으면 해당 게시글에 대한 좋아요 정보가 존재하는 것이다.
					if(like.get("article_id") == article.getId()){
						isLiked = true;
						break;
					}
				}
			}
		}
		model.addAttribute("isLiked", isLiked);
		System.out.println("==============================================================================");
		System.out.println(article.getAuthor().toString());
		System.out.println("==============================================================================");
		model.addAttribute("article", article);
			
		
		return "/article/articleView";
	}

	
	/*
	 * 좋아요(likes) 처리 로직.
	 */
	@GetMapping("/@{username}/{memberPageId}/likes")
	public String doLikes(
		  @PathVariable String username 
		, @PathVariable int memberPageId
		, @RequestParam String articleId
		, HttpServletRequest request
		, Model model) {
		
		
		// session 가져오기. (세션이 없다면 생성하지 않고 null 반환)
		HttpSession session = request.getSession();
		
		
		// [session에 저장된 user가 존재하는 경우]: 로그인 상태.
		if(session.getAttribute("user") != null){
			
			// session에 넣어준 likes 리스트를 받아오기.
			@SuppressWarnings("unchecked")
			List<Map<String, Integer>> likes =  (List<Map<String, Integer>>) session.getAttribute("likes");
			
			
			boolean isLiked = false;
			
			
			// [좋아요를 누른 게시물이 하나라도 존재하는 경우] -> 해당 게시글과 일치하는 좋아요 정보가 있는지 찾음.
			// 좋아요 정보가 아예 존재하지 않는다면 바로 좋아요 로직 실행.
			if(likes != null) {
				for(Map<String, Integer> like : likes){
					
					int likeArticleId = like.get("article_id");
					
					// [해당 게시물에 좋아요를 누른적이 있는 경우]: 좋아요한 게시글의 아이디와 해당 게시글의 아이디가 같음.
					if(likeArticleId == Integer.parseInt(articleId)){
						isLiked = true;
						break;
					}
				}
			}
			
			
			// [좋아요 취소]: like에 레코드가 이미 존재하는 경우.
			if(isLiked){
			
				// likes 테이블에서 레코드를 삭제하기.
				Map<String, Object> likeMap = new HashMap<>();
					likeMap.put("article_id", articleId);
					likeMap.put("member_id", ( (MemberDto) session.getAttribute("user")).getId());
				articleService.cancelLike(likeMap);
				
				
				// 세션 초기화.
				session.removeAttribute("likes");
			
				List<Map<String, Integer>> newLikes  = memberService.getLikes(( (MemberDto) session.getAttribute("user")).getId());
				session.setAttribute("likes", newLikes);
				
			// [좋아요 누르기]: like에 레코드가 존재하지 않는 경우.
			} else {
				
				
				// likes 테이블에 레코드를 추가하기.
				Map<String, Object> likeMap = new HashMap<>();
					likeMap.put("article_id", articleId);
					likeMap.put("member_id", ( (MemberDto) session.getAttribute("user")).getId());
				articleService.doLike(likeMap);
				
				
				// 세션 초기화.
				session.removeAttribute("likes");
			
				List<Map<String, Integer>> newLikes  = memberService.getLikes(( (MemberDto) session.getAttribute("user")).getId());
				session.setAttribute("likes", newLikes);
			}
			
			
			
		// [session 존재하지 않음]
		} else {
			
			// 메세지 객체를 생성해서 로그인 후 이용 가능 안내 페이지로 보냄.
			MsgVo msg = new MsgVo();
			model.addAttribute("msg", msg);
			
			return "/util/msgPage";
		}
		
		
		Map<String, Object> uri = new HashMap<>();
			uri.put("username", username);	
			uri.put("memberPageId", memberPageId);
			
		ArticleDto article = articleService.getArticleByUri(uri);
		model.addAttribute("article", article);
		
		return String.format("redirect:/@%s/%d", username, memberPageId);
	}
}














