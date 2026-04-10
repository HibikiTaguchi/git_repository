package jp.co.sss.advanced.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sss.advanced.entity.Kind;
import jp.co.sss.advanced.repository.KindRepository;

@Controller
public class AdminKindController {
	@Autowired
	KindRepository kindRepo;
	
	@GetMapping("/admin/kind/list")
	public String showAllKind(Model model) {
		List<Kind> kindList = kindRepo.findAllByOrderByKindIdAsc();
		model.addAttribute("kindList", kindList);
		return "/admin/kind/kind_list";
	}
}
