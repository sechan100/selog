package com.selog.dto;

import java.util.Map;

import lombok.Data;

@Data
public class CommentDto {
	private int id;
	private String content;
	private int articleId;
	private int memberId;
	private MemberDto author;
	private String regDate;
	private Map<String, Object> articleUri;
}
