package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.IntegrationDto;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@MapperScan("com.example.demo.dao")
public class DemoController {
	
	UserDAO userDAO;
	
	MemberService memberService;
	
	@RequestMapping("/user/index")
	public String index(Model model) {
		model.addAttribute("index", "index");
		
		return "/user/index";
	}
	
	@RequestMapping("/user/demo")
	public String demo(Model model) {
		
		List<IntegrationDto> list = userDAO.selectUsers();
		
		model.addAttribute("userList", list);
		
		return "/user/demo";
	}
	
	//회원가입 처리
    @PostMapping("/user/signUp")
    public String execSignup(IntegrationDto memberDto) {

        memberService.joinUser(memberDto);
        return "redirect:/user/login_page";
    }
    
    //로그인 페이지
    @GetMapping("/user/login_page")
    public String login_page() {
        return "/user/login_page";
    }

    //회원가입 페이지
    @GetMapping("/user/sign_up")
    public String sign_up_page() {
        return "/user/sign_up_page";
    }
    
    /*
    @GetMapping(value="/csrf-token")
    public @ResponseBody String getCsrfToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
        return token.getToken();
    }
    */
}
