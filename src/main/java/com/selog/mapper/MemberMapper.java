package com.selog.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.selog.dto.MemberDto;

@Mapper
public interface MemberMapper {
	public void insertNewMember(MemberDto member);

	public MemberDto getMemberByUsername(String id);

	public MemberDto getMemberById(int id);

	public List<Map<String, Integer>> getLikes(int memberId);

}

