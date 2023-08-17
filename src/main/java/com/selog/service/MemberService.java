package com.selog.service;

import org.springframework.stereotype.Service;

import com.selog.dto.MemberDto;

@Service
public interface MemberService {

	public void insertNewMember(MemberDto member);

	public MemberDto getMemberByUsername(String username);
	
	public MemberDto getMemberById(int id);
}
