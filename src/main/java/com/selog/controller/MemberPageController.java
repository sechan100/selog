package com.selog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MemberPageController {
	
	
	@GetMapping("/@{username}")
	public String showMemberPage(@PathVariable String username) {
		
		
		return "redirect:/fuck";
	}

}














