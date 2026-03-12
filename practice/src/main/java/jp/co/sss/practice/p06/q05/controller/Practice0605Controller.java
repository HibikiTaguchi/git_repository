package jp.co.sss.practice.p06.q05.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.practice.p06.bean.FruitsSeasonBean;
import jp.co.sss.practice.p06.entity.FruitsSeason;
import jp.co.sss.practice.p06.form.FruitsSeasonForm;
import jp.co.sss.practice.p06.repository.FruitsSeasonRepository;

@Controller
public class Practice0605Controller {
	@Autowired
	FruitsSeasonRepository fsRepo;
	
	@RequestMapping("/fruits/add/input")
	public String showFruitInput() {
		return "practice06/05/fruit_input";
	}
	
	@RequestMapping(path = "/fruits/add/complete", method = RequestMethod.POST)
	public String addFruit(FruitsSeasonForm fruitForm , Model model ) {
		FruitsSeason fs = new FruitsSeason();
		BeanUtils.copyProperties(fruitForm, fs);
		fsRepo.save(fs);
		FruitsSeasonBean fsBean = new FruitsSeasonBean();
		BeanUtils.copyProperties(fs, fsBean);
		model.addAttribute("fruitSeason", fsBean);
		return "practice06/05/fruit_detail";
	}
}
