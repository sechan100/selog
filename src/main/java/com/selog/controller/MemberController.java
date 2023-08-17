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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	

	// ###########[ login ]############################################################################################

	// 로그인 폼 페이지.
	@GetMapping("/login")
	public String loginPage(Model model, @RequestParam(required = false) String fail) {

		model.addAttribute("member", new MemberDto());
		
		// 만약, 로그인에 실패했을 경우, 실패 사유를 같이 페이지로 전송.
		model.addAttribute("fail", fail);

		return "/member/loginForm";
	}

	
	// 로그인 프로세스 로직.
	@PostMapping("/loginPrcs")
	public String loginPrcs(HttpServletRequest request, Model model, MemberDto attemptingLoginMember) {
		
		// 로그인 id로 기존의 사용자 정보를 DB에서 가져온다.
		MemberDto registedMember =  service.getMemberByUsername(attemptingLoginMember.getUsername());
	

		
		// [로그인 실패]: 계정이 존재하지 않음. -> fail=id (아이디가 없다는 것이 곧 계정이 존재하지 않는다는 의미)
		if(registedMember == null){
			return "redirect:/login?fail=id";
		
		
		// [로그인 실패]: 비밀번호 불일치.
		} else if( !attemptingLoginMember.getPassword().equals( registedMember.getPassword() ) ){
			
			return "redirect:/login?fail=pw";


			
		// [로그인 성공]
		} else {
			
			// temporary login member setting.
			HttpSession session = request.getSession();
			
			session.setAttribute("user", registedMember);
			
			// log
			System.out.println("=====[ New Login ]============================================================\n");
			System.out.println(registedMember.toString());
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
