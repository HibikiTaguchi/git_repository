package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class IndexController {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute LoginForm loginForm) {
		session.invalidate();
		return "index";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute LoginForm form, BindingResult result, HttpSession session, Model model) {
		//ログイン判定の前に入力値チェック
		if (result.hasErrors()) {
			return "index";
		}
		//フォームの内容をリクエストスコープに保存
		session.setAttribute("loginForm", form);
		//EmployeeRepositoryのID検索メソッドを使って入力されたIDの従業員情報を取得
		int id = form.getEmpId();
		Employee employee = new Employee();
		employee = employeeRepository.getReferenceById(id);
		//フォームに入力されたパスワードを取得
		String inputPass = form.getEmpPass();
		//入力されたIDに対応する正規のパスワードを取得
		String truePass = employee.getEmpPass();
		
		//入力されたIDとパスワードをDBと照合し整合性を確認
		if(inputPass.equals(truePass)) {
			//入力値と登録されたパスワードが一致したのでログイン処理を行う
			System.out.println("ログイン");
			session.setAttribute("user", employee);
			return "redirect:/list";
		} else {
			//パスワード不一致のため再度ログイン画面へ
			System.out.println("ログイン失敗");
			return "index";
		}
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
}
