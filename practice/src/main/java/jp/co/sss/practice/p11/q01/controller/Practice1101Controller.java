package jp.co.sss.practice.p11.q01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Practice1101Controller {
	@GetMapping("/filter/def")
	public String showView() {
		return "practice11/01/filter_view";
	}
	
	@GetMapping("/filter/xyz")
	public String whenRedirect() {
		return "practice11/01/filter_redirect";
	}
}
