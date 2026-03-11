package jp.co.sss.shop.controller; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.shop.repository.ItemWithCategoryRepository; 
 
@Controller 
public class ItemWithCategoryController { 
	 @Autowired 
	 ItemWithCategoryRepository repository; 
	 
	 @RequestMapping("/items/findItemAndCategory") 
	 public String showItemAndCategoryList(Model model) { 
		 model.addAttribute("items", repository.findAll()); 
		 return "items/item_category_list"; 
	 } 
} 