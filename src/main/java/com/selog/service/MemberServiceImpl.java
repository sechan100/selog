package com.selog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selog.dto.MemberDto;
import com.selog.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;



//	new member sign up
	@Override
	public void insertNewMember(MemberDto member) {
		mapper.insertNewMember(member);
	}


// get registed member data by id
	@Override
	public MemberDto getMemberByUsername(String username) {
		return mapper.getMemberByUsername(username);
	}


	@Override
	public MemberDto getMemberById(int id) {
		return mapper.getMemberById(id);
	}
	
}
