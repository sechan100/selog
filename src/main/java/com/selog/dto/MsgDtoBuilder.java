package com.selog.dto;


public class MsgDtoBuilder {
	private String msgTitle = "* 알림 *";
	private String msgContent = "확인을 누르면 메인페이지로 이동합니다.";
	private String aimUrl = "/";
	private String btnValue = "확인";
	
	
	public MsgDtoBuilder addMsgTitle(String title) {
		this.msgTitle = title;
		return this;
	}
	
	public MsgDtoBuilder addmsgContent(String content) {
		this.msgContent = content;
		return this;
	}
	
	public MsgDtoBuilder addAimUrl(String aimUrl) {
		this.aimUrl = aimUrl;
		return this;
	}
	
	public MsgDtoBuilder addBtnValue(String btnValue) {
		this.btnValue = btnValue;
		return this;
	}
	
	

	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	

	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	

	public String getAimUrl() {
		return aimUrl;
	}
	public void setAimUrl(String aimUrl) {
		this.aimUrl = aimUrl;
	}
	
	
	public String getBtnValue() {
		return btnValue;
	}
	public void setBtnValue(String btnValue) {
		this.btnValue = btnValue;
	}
	
}
