package com.selog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selog.dto.ArticleDto;
import com.selog.mapper.ArticleMapper;




@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleMapper articleMapper;
	
	
	@Override
	public List<ArticleDto> getArticles() {
		return articleMapper.getArticles();
	}


	@Override
	public ArticleDto getArticleByUri(Map<String, Object> uri) {
		return articleMapper.getArticleByUri(uri);
	}



	
}
