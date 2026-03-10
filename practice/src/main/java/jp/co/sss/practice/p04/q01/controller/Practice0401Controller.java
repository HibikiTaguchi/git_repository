package jp.co.sss.practice.p04.q01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Practice0401Controller {
	@RequestMapping(path = "/input", method = RequestMethod.GET)
	public String showForm() {
		return "practice04//01/input_form";
	}
	
	@RequestMapping(path = "/send/get_method", method = RequestMethod.GET)
	public String SendByGetMethod(String color) {
		System.out.println("##GETメソッドで受け取りました");
		System.out.println("##パラメータの値:" + color);
		return "practice04/01/get_result";
	}
	
	@RequestMapping(path = "/send/post_method", method = RequestMethod.POST)
	public String SendByPostMethod(String color) {
		System.out.println("##POSTメソッドで受け取りました");
		System.out.println("##パラメータの値:" + color);
		return "practice04/01/post_result";
	}
}
