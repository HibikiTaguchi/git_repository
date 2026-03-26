package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;
import jp.co.sss.crud.util.BeanCopy;

/** 新規登録にまつわるメソッドを集めたコントローラクラス */
@Controller
public class RegistrationController {
	/** オブジェクトの呼び出しを不要にしたEmployeeRepositoryインターフェースのフィールド */
	@Autowired
	EmployeeRepository empRepo;
	
	/**
	 * 新規社員登録＿入力画面表示のためのメソッド
	 * @param 入力チェックのために引数でEmployeeFormオブジェクト生成
	 * @return 新規社員登録＿入力画面へ
	 */
	@GetMapping("/regist/input")
	public String showRegistInput(@ModelAttribute EmployeeForm form, Employee employee) {
		return "regist/regist_input";
	}
	
	/**
	 * 新規社員登録＿入力画面での入力値をチェックして確認画面に進むかを決めるメソッド
	 * @param 入力チェックのために引数でEmployeeFormオブジェクト生成
	 * @param 入力チェックのために引数でBindingResultオブジェクト生成
	 * @param リクエストスコープ仕様のために引数でModelオブジェクト生成
	 * @return 新規登録社員情報入力画面	
	 * 			入力チェックに引っかかった場合
	 * @return 入力社員情報確認画面
	 * 			入力情報に誤りが無ければ遷移
	 */	
	@PostMapping("/regist/check")
	public String checkInfo(@Valid @ModelAttribute EmployeeForm form,
	  BindingResult result ,Model model) {
		if (result.hasErrors()) {
			return "regist/regist_input";
		}
		model.addAttribute("employee", BeanCopy.copyFormToEmployee(form));
		return "regist/regist_check";
	}
	
	/**
	 * 社員登録確認画面で登録を押下したらこのメッソドで実際にDBに登録する
	 * @param 新規社員情報登録画面で表示されている社員のEmployeeクラスを受け取る
	 * @return 新規社員情報登録完了画面へ
	 */
	@PostMapping("/regist/doRegist")
	public String doRegist(Employee emp) {
		//レポジトリのsave()メソッドで実際にDBへ登録
		empRepo.save(emp);
		return "regist/regist_complete";
	}
	/**
	 * 社員登録確認画面から社員登録入力画面へ戻るためのメソッド
	 * @param 新規社員情報登録画面で表示されている社員のEmployeeクラスを受け取る
	 * @param リクエストスコープ仕様のために引数でModelオブジェクト生成
	 * @return 社員登録入力画面へ
	 */
	@PostMapping("/regist/back")
	public String backToInputWithInfo(Employee employee, Model model) {
		//空のEmployeeFormオブジェクトを生成
		EmployeeForm form = new EmployeeForm();
		//フォームに確認画面から取得したEmployeeオブジェクトの内容をコピーしてリクエストスコープに保存
		model.addAttribute("employeeForm", BeanCopy.copyEntityToForm(employee, form));
		return "regist/regist_input";
	}
}
