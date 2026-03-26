package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sss.crud.repository.EmployeeRepository;

/** 社員一覧表示にまつわるメソッドを集めたコントローラクラス */
@Controller
public class ListController {
	/** オブジェクトの呼び出しを不要にしたEmployeeRepositoryインターフェースのフィールド */
	@Autowired
	EmployeeRepository empRepo;
	
	/**
	 * ログイン直後や登録、変更、削除入力画面から戻った時に社員情報を全件表示するメソッド
	 * @param DB上の全社員情報をリクエストスコープに保存するためにModelオブジェクトを生成
	 * @return 社員一覧全件表示画面へ
	 */
	@GetMapping("/list")
	public String showList(Model model) {
		//全件表示のための情報を取得
		model.addAttribute("empList", empRepo.findAll());
		return "list/list";
	}
	/**
	 * 社員名検索用メソッド
	 * @param サイドバーの社員名検索窓に入力された文字列
	 * @param リクエストスコープを使うためにModelオブジェクトを生成
	 * @return 社員一覧表示画面へ
	 */
	@GetMapping("/list/empName")
	public String showListByName(String empName, Model model) {
		//EmployeeRepositoryに定義された社員名検索メソッドを実行して結果をリクエストスコープに保存
		model.addAttribute("empList", empRepo.findByName(empName));
		return "list/list";
	}
	/**
	 * 部署名検索用メソッド
	 * @param サイドバーの部署名検索窓に入力された文字列
	 * @param リクエストスコープを使うためにModelオブジェクトを生成
	 * @return 社員一覧表示画面へ
	 */
	@GetMapping("/list/deptId")
	public String showListByDeptId(Integer deptId, Model model) {
		//EmployeeRepositoryに定義された部署名検索メソッドを実行して結果をリクエストスコープに保存
		model.addAttribute("empList", empRepo.findByDeptId(deptId));
		//初回検索以降は直前に選んだ部署が選択済みになるよう変数をリクエストスコープに保存
		model.addAttribute("selectedDeptId", deptId);
		return "list/list";
	}
}
