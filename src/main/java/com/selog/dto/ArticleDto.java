package com.selog.dto;

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
	private String authorUsername;
	private String authorName;
	private MemberDto author;
	private int likes;
	private int hits;
	
}
