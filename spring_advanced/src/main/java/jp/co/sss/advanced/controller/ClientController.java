package jp.co.sss.advanced.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.co.sss.advanced.bean.SearchInfoBean;
import jp.co.sss.advanced.entity.Kind;
import jp.co.sss.advanced.entity.Menuitem;
import jp.co.sss.advanced.repository.KindRepository;
import jp.co.sss.advanced.repository.MenuitemRepository;

@Controller
public class ClientController {
	@Autowired
	MenuitemRepository miRepo;
	@Autowired
	KindRepository kindRepo;
	
	@GetMapping("/")
	public String showClientMenuitemList(Model model) {
		model.addAttribute("miList", miRepo.findByStatusOrderByMenuitemIdAsc(0));
		return "/index";
	}
	@GetMapping("/search")
	public String showSearchMenu(Model model) {
		model.addAttribute("kindList", kindRepo.findAll());
		return "/search_menu";
	}
	@GetMapping("/search/byname")
	public String doSearchAndShowResult(String menuitemName, Model model) {
		String str = menuitemName.trim();
		if (menuitemName.equals(null) || menuitemName.isBlank() || menuitemName.isEmpty()) {
			return "redirect:/";
		}
		SearchInfoBean siBean = new SearchInfoBean();
		siBean.setSearchMethod(1);
		siBean.setSearchKey(str);
		List<Menuitem> miList = miRepo.findByMenuitemNameContainingAndStatusOrderByMenuitemIdAsc(str, 0);
		model.addAttribute("miList", miList);
		model.addAttribute("siBean", siBean);
		return "/index";
	}
	@GetMapping("/search/bykind")
	public String searchByKindAndShowResult(Integer kindId, Model model) {
		if (kindId == 0) {
			return "redirect:/";
		} else {
			SearchInfoBean siBean = new SearchInfoBean();
			siBean.setSearchMethod(2);
			Kind kind = kindRepo.getReferenceById(kindId);
			siBean.setSearchKey(kind.getKindName());
			Integer avgPrice = miRepo.findByKindIdAndCalcAvg(kind);
			siBean.setKindAvgPrice(avgPrice);
			model.addAttribute("miList", miRepo.findByKindAndStatusOrderByMenuitemIdAsc(kind, 0));
			model.addAttribute("siBean", siBean);
			return "/index";
		}
		
	}
	@GetMapping("/sort/{sortType}")
	public String sortList(@PathVariable Integer sortType, Model model) {
		List<Menuitem> miList = new ArrayList<Menuitem>();
		switch (sortType) {
			case 1:
				miList = miRepo.findByStatusOrderByPriceAscMenuitemIdAsc(0);
				break;
			case 2:
				miList = miRepo.findByStatusOrderByPriceDescMenuitemIdAsc(0);
				break;
		}
		model.addAttribute("miList", miList);
		return "/index";
	}
}
