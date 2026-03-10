package jp.co.sss.practice.p03.q02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Practice0302Controller {
	@RequestMapping(path = "/show/redirect_page")
	public String before() {
		return "practice03/02/redirect_link";
	}
	
	@RequestMapping(path = "/absolute")
	public String absolute() {
		return "redirect:https://www.google.co.jp";
	}
	
	@RequestMapping(path = "/relative")
	public String relative() {
		return "redirect:/result";
	}
	
	@RequestMapping(path = "/result")
	public String result() {
		return "practice03/02/redirect_result";
	}
}
