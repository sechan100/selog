package com.selog.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.selog.dto.ArticleDto;

@Mapper
public interface ArticleMapper {

	public List<ArticleDto> getArticles();
	
	public ArticleDto getArticleByUri(Map<String, Object> uri);

}
