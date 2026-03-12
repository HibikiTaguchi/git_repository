package jp.co.sss.practice.p07.q01controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sss.practice.p07.repository.FruitsAreaRepository;

@Controller
public class Practice0701Controller {
	@Autowired
	FruitsAreaRepository faRepo;
	
	@GetMapping("/fruits/area/list/all")
	public String showFruitsList(Model model) {
		model.addAttribute("faList", faRepo.findAll());
		return "practice07/01/fruits_list";
	}
}
