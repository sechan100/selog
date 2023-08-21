package com.selog.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.selog.dto.ArticleDto;
import com.selog.dto.CommentDto;



@Service
public interface ArticleService {
	
	public List<ArticleDto> getArticles();
	
	public ArticleDto getArticleByUri(Map<String, Object> uri);

	public void doLike(Map<String, Object> likesMap);

	public void cancelLike(Map<String, Object> likeMap);

	public void postArticle(ArticleDto article);

	public void addComment(CommentDto comment);

}
