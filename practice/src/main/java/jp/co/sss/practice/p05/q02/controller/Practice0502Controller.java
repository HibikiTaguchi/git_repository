package jp.co.sss.practice.p05.q02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class Practice0502Controller {
	@GetMapping("/numguess/start")
	public String start(HttpSession session) {
		Integer hitNumber = (int) (Math.floor(Math.random() * 9) + 1);
		session.setAttribute("hitNumber", hitNumber);
		System.out.println("当たりの数は" + hitNumber);
		return "practice05/02/numguess_start";
	}
	
	@GetMapping("/numguess/input")
	public String inputNum() {
		return "practice05/02/numguess_input";
	}
	
	@GetMapping("/numguess/judge")
	public String judge(HttpSession session, Integer inputNum) {
		Integer hitNumber = (Integer) session.getAttribute("hitNumber");
		if (hitNumber == inputNum) {
			return "redirect:/numguess/hit";
		} else {
			return "practice05/02/numguess_judge";
		}
	}
	
	@GetMapping("/numguess/hit")
	public String hit(Model model, HttpSession session) {
		Integer hitNumber = (Integer) session.getAttribute("hitNumber");
		String hitMsg = "当たりです！正解は" + hitNumber + "でした。";
		System.out.println(hitMsg);
		model.addAttribute("hitMsg", hitMsg);
		session.invalidate();
		return "practice05/02/numguess_end";
	}
}
