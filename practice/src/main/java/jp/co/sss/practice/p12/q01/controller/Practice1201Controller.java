package jp.co.sss.practice.p12.q01.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Practice1201Controller {
	@GetMapping("/fortune")
	public String fortune(Model model) {
		int fortuneNum = (int) Math.floor(Math.random() * 5) + 1;
		System.out.println("fortuneNum：" + fortuneNum);
		model.addAttribute("fortuneNum", fortuneNum);
		model.addAttribute("today", new Date());
		return "practice12/01/fortune";
	}
}
