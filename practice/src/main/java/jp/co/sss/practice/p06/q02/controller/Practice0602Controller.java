package jp.co.sss.practice.p06.q02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sss.practice.p06.repository.FruitsSeasonRepository;

@Controller
public class Practice0602Controller {
	@Autowired
	FruitsSeasonRepository fsRepo;
	
	@GetMapping("/fruits/list/sort/season")
	public String showFruitsList(Model model) {
		model.addAttribute("fsList", fsRepo.findAllByOrderBySeasonMonthAsc());
		return "practice06/02/fruits_list";
	}
}
