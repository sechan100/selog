package com.selog.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.selog.dto.MemberDto;

@Service
public interface MemberService {

	public void insertNewMember(MemberDto member);

	public MemberDto getMemberByUsername(String username);
	
	public MemberDto getMemberById(int id);

	public List<Map<String, Integer>> getLikes(int memberId);
}
