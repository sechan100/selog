package com.selog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.selog.dto.MemberDto;
import com.selog.dto.MsgDtoBuilder;
import com.selog.service.MemberService;
import com.selog.temporaryloginmodule.LoginedMemberFactory;


@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	

	// ###########[ login ]############################################################################################

	// login form page
	@GetMapping("/login")
	public String loginPage(Model model, @RequestParam(required = false) String fail) {

		model.addAttribute("member", new MemberDto());
		model.addAttribute("fail", fail);

		return "/member/loginForm";
	}

	// login process logic
	@PostMapping("/loginPrcs")
	public String loginPrcs(Model model, MemberDto attemptingLoginMember) {
		
		// get member data from DB by login id.
		MemberDto registedMember =  service.getMemberByUsername(attemptingLoginMember.getUsername());
	
	
		// registed member doesn't exist..
		if(registedMember == null){
			return "redirect:/login?fail=id";
		
		
		// password doesn't correspond..
		} else if( !attemptingLoginMember.getPassword().equals( registedMember.getPassword() ) ){
			
			return "redirect:/login?fail=pw";


			
		// login success
		} else {
			
			// temporary login member setting.
			LoginedMemberFactory.setLoginedMember(registedMember);
			
			// log
			System.out.println("============================================================================\n");
			System.out.println(LoginedMemberFactory.getLoginedMember().toString());
			System.out.println("\n============================================================================");
			
			return "redirect:/";
		}
	}

	// ###########[ sign up ]############################################################################################

	// sign up form page.
	@GetMapping("/signup")
	public String joinPage( @RequestParam(required = false) String fail, Model model) {

		model.addAttribute("member", new MemberDto());
		model.addAttribute("fail", fail);

		return "/member/signupForm";
	}

	// sign up process
	@PostMapping("/signupPrcs")
	public String processSignup(Model model, MemberDto member) {

		if(member.getName().equals("")){
		
			return "redirect:/signup?fail=emptyName";
			
		} else if(member.getUsername().equals("")) {
		
			return "redirect:/signup?fail=emptyId";
			
		} else if(member.getPassword().equals("")) {
		
			return "redirect:/signup?fail=emptyPassword";
			
		} else if(member.getEmail().equals("")) {
		
			return "redirect:/signup?fail=emptyEmail";
		}

		// print log
		System.out.println("========[New Sign Up]==========================================================");
		System.out.println(member.toString());
		System.out.println("===============================================================================");

		// Service
		service.insertNewMember(member);

		model.addAttribute("msg",
			new MsgDtoBuilder()
				.addMsgTitle(member.getName() + "님, 회원가입이 완료되었습니다!")
				.addAimUrl("/"));

		return "/util/msgPage";
	}

}
