package jp.co.sss.practice.p05.q01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sss.practice.p05.q01.form.BmiForm;

@Controller
public class Practice0501Controller {
	@GetMapping("/bmi/input")
	public String showForm() {
		return "practice05/01/bmi_input";
	}
	
	@PostMapping("/bmi/result")
	public String calcBmi(BmiForm bmiForm, Model model) {
		bmiForm.setBmi(bmiForm.getWeight() / (bmiForm.getHeight() * bmiForm.getHeight()) * 10000);
		model.addAttribute("bmiForm", bmiForm);
		return "practice05/01/bmi_result";
	}
}
