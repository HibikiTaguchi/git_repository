package jp.co.sss.practice.p12.q02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Practice1202Controller {
	@GetMapping("/checkbox/input")
	public String showInput() {
		return "practice12/02/checkbox_input";
	}
	
	@PostMapping("/checkbox/result")
	public String checkResult(Model model, String[] destPrefs) {
		model.addAttribute("destPrefs", destPrefs);
		return "practice12/02/result";
	}
}
