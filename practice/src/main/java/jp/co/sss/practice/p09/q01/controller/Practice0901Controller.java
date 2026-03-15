package jp.co.sss.practice.p09.q01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import jp.co.sss.practice.p09.q01.form.BodyTempForm;

@Controller
public class Practice0901Controller {
	@GetMapping("/body_temp/input")
	public String showInput(@ModelAttribute BodyTempForm bodyTempForm) {
		return "practice09/01/bt_input";
	}
	
	@PostMapping("/body_temp/check")
	public String checkInputAndSelectResult(@Valid @ModelAttribute 
	  BodyTempForm bodyTempForm, BindingResult result) {
		if (result.hasErrors()) {
			return "practice09/01/bt_input";
		}
		if (bodyTempForm.getBodyTemp() > 35.0 && bodyTempForm.getBodyTemp() < 37.5) {
			return "practice09/01/bt_ok";
		} else {
			return "practice09/01/bt_ng";
		}
	}
}
