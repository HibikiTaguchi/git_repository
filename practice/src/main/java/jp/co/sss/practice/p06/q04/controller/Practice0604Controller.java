package jp.co.sss.practice.p06.q04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sss.practice.p06.repository.FruitsSeasonRepository;

@Controller
public class Practice0604Controller {
	@Autowired
	FruitsSeasonRepository fsRepo;
	
	@GetMapping("/fruits/search/input")
	public String selectSeason(Model model) {
		return "practice06/04/fruits_select_season";
	}
	
	@GetMapping("/fruits/search/result")
	public String searchAndCalc(Integer season, Model model) {
		System.out.println("searchAndCalcメソッド開始");
		System.out.println("season:" + season);
		model.addAttribute("fsList", fsRepo.findBySeasonMonthOrderByFruitIdAsc(season));
		System.out.println("リスト取得、リクエストスコープへ保存");
		String str = "旬の月入力エラー\n1～13の値を入力してください";
		if (season >= 1 && season <= 12) {
			str = season + "月";
		} else if (season == 13) {
			str = "通年";
		}
		System.out.println("検索条件文字列設定");
		model.addAttribute("str", str);
		System.out.println("フォワード直前");
		return "practice06/04/fruits_list";
	}
}
