package com.selog.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.selog.dto.ArticleDto;
import com.selog.dto.CommentDto;

@Mapper
public interface ArticleMapper {

	public List<ArticleDto> getArticles();
	
	public List<ArticleDto> getSummarizedArticles();
	
	public ArticleDto getArticleByUri(Map<String, Object> uri);

	public void doLike(Map<String, Object> likesMap);

	public void cancelLike(Map<String, Object> likeMap);

	public void insertArticle(ArticleDto article);

	public void insertComment(CommentDto Comment);

}
