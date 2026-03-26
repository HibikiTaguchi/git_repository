package jp.co.sss.crud.controller;

import java.util.ArrayList;
import java.util.List;

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
	/** オブジェクトの呼び出しを不要にしたEmployeeRepositoryインターフェースのフィールド */
	@Autowired
	EmployeeRepository employeeRepository;
	/** オブジェクトの呼び出しを不要にしたセッションのフィールド  */
	@Autowired
	HttpSession session;
	/** ログインIDとパスワードが正しいかどうかのフラグ。エラー文表示のために使う。 */
	boolean isCorrect = true;
	
	/**
	 * ログイン画面を表示するプロジェクト全体の入り口となるメソッド
	 * @param 入力チェックのために引数でLoginFormオブジェクト生成
	 * @param リクエストスコープを使うために引数でModelオブジェクト生成
	 * @return ログイン画面へ
	 */
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute LoginForm loginForm, Model model) {
		//念のため最初にセッションを破棄
		session.invalidate();
		//初回アクセス時にはエラーは無いのでフラグは降ろす
		model.addAttribute("isCorrect", isCorrect);
		return "index";
	}
	
	/**
	 * ログイン画面での入力値からログイン可否を決めるメソッド
	 * @param 入力チェックのために引数でLoginFormオブジェクト生成
	 * @param 入力チェックのために引数でBindingResultオブジェクト生成
	 * @param セッションスコープを使うために引数でHttpSessionオブジェクト生成
	 * @param リクエストスコープを使うために引数でModelオブジェクト生成
	 * @return ログイン成功時：社員一覧表示画面へ
	 * 			ログイン失敗時：ログイン画面へ
	 */
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute LoginForm form, BindingResult result, HttpSession session, Model model) {
		session.removeAttribute("user");
		//ログイン判定の前に入力値チェック
		//入力値に異常があればログイン判定をせずにログイン画面に戻す
		if (result.hasErrors()) {
			model.addAttribute("isCorrect", isCorrect);
			return "index";
		}
		//入力値に異常が無ければログイン判定をする
		//フォームの内容をセッションコープに保存
		session.setAttribute("loginForm", form);
		
		//入力された社員IDがDB上に存在するか確認
		List<Integer> empIdList = new ArrayList<Integer>();
		for (Employee emp : employeeRepository.findAll()) {
			 empIdList.add(emp.getEmpId());
		}
		int count = 0;
		for (Integer empId : empIdList) {
			if (empId == form.getEmpId()) {
				count++;
			}
		}
		if (count == 0) {
			//入力値がDB上に無ければエラーフラグをたててログイン画面に戻す
			isCorrect = false;
			model.addAttribute("isCorrect", isCorrect);
			return "index";
		}
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
			isCorrect = false;
			model.addAttribute("isCorrect", isCorrect);
			return "index";
		}
	}
	
	/**
	 * ログアウトメソッド
	 * @param セッションを操作するためにHttpSessionオブジェクトを引数で生成
	 * @return ログイン画面へ
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//セッションも破棄し何も情報を引き継がないようにリダイレクトする
		session.invalidate();
		return "redirect:/";
	}
}
