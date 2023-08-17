package com.selog.temporaryloginmodule;

import com.selog.dto.MemberDto;

public class LoginedMemberFactory {

	private static MemberDto loginedMember;
	
	
	public static void setLoginedMember(MemberDto loginedMember) {
		LoginedMemberFactory.loginedMember = loginedMember;
	}
	
	public static MemberDto getLoginedMember() {
		return loginedMember;
	}
	
}
















