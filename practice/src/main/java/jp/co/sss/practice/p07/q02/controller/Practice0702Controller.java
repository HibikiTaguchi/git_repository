package jp.co.sss.practice.p07.q02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sss.practice.p07.entity.Area;
import jp.co.sss.practice.p07.entity.FruitsArea;
import jp.co.sss.practice.p07.repository.AreaRepository;
import jp.co.sss.practice.p07.repository.FruitsAreaRepository;

@Controller
public class Practice0702Controller {
	@Autowired
	FruitsAreaRepository faRepo;
	@Autowired
	AreaRepository areaRepo;
	
	@GetMapping("/fruits/select/area")
	public String showSelecter(Model model) {
		model.addAttribute("areaList", areaRepo.findAllByOrderByAreaIdAsc());
		return "practice07/02/area_select";
	}
	
	@GetMapping("/fruits/select/area/result")
	public String searchAndShowResult(Integer areaId, Model model) {
		Area area = new Area();
		area = areaRepo.getReferenceById(areaId);
		model.addAttribute("areaName", area.getAreaName());
		List<FruitsArea> faList = faRepo.findByAreaIdOrderByFruitIdAsc(areaId);
		model.addAttribute("faList", faList);
		return "practice07/02/fruits_list";
	}
}
