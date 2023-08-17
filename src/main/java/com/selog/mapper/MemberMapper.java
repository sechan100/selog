package com.selog.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.selog.dto.MemberDto;

@Mapper
public interface MemberMapper {
	public void insertNewMember(MemberDto member);

	public MemberDto getMemberByUsername(String id);

	public MemberDto getMemberById(int id);

}

