package com.selog.dto;

import lombok.Data;

@Data
public class MsgVo {
	private String msgTitle = "로그인 후 이용가능합니다.";
	private String msgContent = "확인을 누르면 메인페이지로 이동합니다.";
	private String aimUrl = "/login";
	private String btnValue = "로그인";
}
