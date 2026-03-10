package jp.co.sss.practice.p03.q01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Practice0301Controller {
	@RequestMapping(path = "/first")
	public String first() {
		return "practice03/01/before_redirect";
	}
	
	@RequestMapping(path = "/second")
	public String second() {
		return "redirect:/third";
	}
	
	@RequestMapping(path = "/third")
	public String third() {
		return "practice03/01/after_redirect";
	}
}
