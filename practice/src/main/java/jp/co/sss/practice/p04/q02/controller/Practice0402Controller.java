package jp.co.sss.practice.p04.q02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.practice.p04.q02.form.FavoriteForm;

@Controller
public class Practice0402Controller {
	@RequestMapping("/favorite/input")
	public String showForm() {
		return "practice04/02/favorite_input";
	}
	
	@PostMapping("/favorite/result")
	public String getResultByForm(FavoriteForm favoriteForm) {
		System.out.println("==パラメータを受け取りました");
		System.out.println("==果物 :" + favoriteForm.getFruit());
		System.out.println("==スナック:" + favoriteForm.getSnack());
		System.out.println("==スイーツ:" + favoriteForm.getSweet());
		System.out.println("==おかず :" + favoriteForm.getSidedish());
		return "practice04/02/favorite_result";
	}
}
