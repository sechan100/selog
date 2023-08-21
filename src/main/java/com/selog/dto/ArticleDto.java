package com.selog.dto;

import java.util.List;

import lombok.Data;

@Data
public class ArticleDto {

	private int id;
	private int memberPageId;
	private String title;
	private String content;
	private String img;
	private String postDate;
	private int authorId;
	private int likes;
	private MemberDto author;
	private List<CommentDto> comments;
	
}
