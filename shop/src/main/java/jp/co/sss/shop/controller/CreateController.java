package jp.co.sss.shop.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.shop.entity.Category;
import jp.co.sss.shop.entity.ItemWithCategory;
import jp.co.sss.shop.form.ItemWithCategoryForm;
import jp.co.sss.shop.repository.ItemWithCategoryRepository;

@Controller  
public class CreateController { 
	@Autowired 
	ItemWithCategoryRepository repository; 

	//登録入力画面表示 
	@RequestMapping("/create/input")  
	public String createInput() { 
		//登録入力画面遷移 
		return "create/create_input"; 
	} 
   
	//登録処理の後、登録完了画面表示 
	@RequestMapping("/create/complete") 
	public String createComplete(ItemWithCategoryForm form, Model model) { 
		//レコード登録のため、エンティティのオブジェクトを作成 
		ItemWithCategory itemWithCategory = new ItemWithCategory(); 
		//フォームから受け取った name と price の値のみ itemWithCategory にコピー  
		BeanUtils.copyProperties(form, itemWithCategory);
		//category フィールドにカテゴリ情報を設定するためのオブジェクトを作成  
		Category category = new Category(); 
		//フォームで受け取ったカテゴリ ID を、Category オブジェクトのフィールド id  に代入 
		category.setId(form.getCategoryId());
		//category オブジェクトを itemWithCategory の category フィールドに代入  
		itemWithCategory.setCategory(category); 
		//save()メソッドを呼び出し、登録したレコード情報を itemWithCategory に戻す  
		itemWithCategory = repository.save(itemWithCategory); 
		model.addAttribute("item", itemWithCategory);                               
		//登録完了画面遷移 
		return "create/create_complete"; 
	}
}