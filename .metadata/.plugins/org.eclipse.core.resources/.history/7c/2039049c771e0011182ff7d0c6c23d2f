package jp.co.sss.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jp.co.sss.shop.form.LoginForm;

@Controller
public class SessionController {
	@RequestMapping(path = "/login",method = RequestMethod.GET)
	public String login() {
		return "session/login";
	}
	
	@RequestMapping(path = "/doLogin",method = RequestMethod.GET)
	public String doLoginGet(Integer userId) {
		System.out.println("ユーザーID:" + userId);
		return "session/login";
	}
	
	@RequestMapping(path = "/doLogin",method = RequestMethod.POST)
	public String doLoginPost(Integer userId) {
		System.out.println("ユーザーID:" + userId);
		return "session/login";
	}
	
	@GetMapping("/loginUsingForm")
	public String loginUsingForm() {
		return "session/login_using_form";
	}
	
	@PostMapping("/doLoginUsingForm")
	public String doLoginUsingForm(LoginForm form) {
		System.out.println("ユーザーID:" + form.getUserId());
		System.out.println("パスワード:" + form.getPassword());
		return "session/login_using_form";
	}
	
	@RequestMapping(path = "/loginOnRequest",method = RequestMethod.GET) 
	public String loginOnRequest() { 
	return "session/login_on_request"; 
	} 
	 
	@RequestMapping(path = "/doLoginOnRequest",method = RequestMethod.POST) 
	public String doLoginOnRequest(LoginForm form,Model model) { 
	model.addAttribute("userId",form.getUserId()); 
	return "session/login_on_request"; 
	} 
	
	@GetMapping("/loginOnSession")
	public String loginOnSession() {
		return "session/login_on_session";
	}
	
	@PostMapping("/doLoginOnSession")
	public String doLoginOnSession(LoginForm form, HttpSession session) {
		if(form.getUserId() == 123) {
			//入力したユーザーIDをセッション属性userIdとしてセッションスコープに保存
			session.setAttribute("userId", form.getUserId());
			return "redirect:/";
		} else {
			return "session/login_on_session";
		}
	}
	
	@GetMapping("/logout") 
	public String logout(HttpSession session) { 
	//セッションの破棄  
	session.invalidate(); 
	return "redirect:/"; 
	} 
}
