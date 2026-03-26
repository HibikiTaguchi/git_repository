package jp.co.sss.crud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;
/** 社員情報変更にまつわるメソッドを集めたコントローラクラス */
@Controller
public class UpdateController {
	/** オブジェクトの呼び出しを不要にしたEmployeeRepositoryインターフェースのフィールド */
	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	/** オブジェクトの呼び出しを不要にしたDepartmentRepositoryインターフェースのフィールド */
	DepartmentRepository deptRepo;
	
	/**
	 * 社員一覧表示画面から変更ボタン押下時に社員情報変更＿入力画面を表示するためのメソッド
	 * @param URLパラメータから社員IDを取得
	 * @param リクエストスコープを使用するためにModelオブジェクトを生成
	 * @return 社員情報変更＿入力画面へ
	 */
	@PostMapping("/update/{empId}")
	public String showUpdateInputPost(@PathVariable Integer empId, Model model) {
		//社員IDから該当社員のオブジェクトを生成
		Employee emp = empRepo.getReferenceById(empId);
		//空のフォームにEmployeeオブジェクトの内容をコピー
		EmployeeForm employeeForm = new EmployeeForm();
		BeanUtils.copyProperties(emp, employeeForm);
		//リクエストスコープにフォームを保存
		model.addAttribute("employeeForm", employeeForm);
		return "update/update_input";
	}
	/**
	 * ヘッダーのログインユーザーのリンクから社員情報を変更するためのメソッド
	 * @param リクエストパラメータから社員IDを取得
	 * @param リクエストスコープを使用するためにModelオブジェクトを生成
	 * @return 社員情報変更＿入力画面へ
	 */
	@GetMapping("/update/input")
	public String showUpdateInputGet(@RequestParam("empId") Integer empId,
	  Model model) {
		//社員IDから該当社員のオブジェクトを生成
		Employee emp = empRepo.getReferenceById(empId);
		//空のフォームにEmployeeオブジェクトの内容をコピー
		EmployeeForm employeeForm = new EmployeeForm();
		BeanUtils.copyProperties(emp, employeeForm);
		//リクエストスコープにフォームを保存
		model.addAttribute("employeeForm", employeeForm);
		return "update/update_input";
	}
	/**
	 * 社員情報変更＿入力画面で入力された値をチェックして確認画面へ進むか決めるメソッド
	 * @param 社員情報を扱うフォームクラス
	 * @param 入力チェックのためのBindingResultオブジェクトを生成
	 * @param リクエストスコープを使用するためにModelオブジェクトを生成
	 * @return 入力チェック成功時：社員情報変更＿確認画面へ
	 * 			入力チェック失敗時：社員情報変更＿入力画面へ
	 */
	@PostMapping("/update/check")
	public String checkInput(@Valid @ModelAttribute
	  EmployeeForm employeeForm, BindingResult result ,Model model) {
		//最初に入力チェックをしてエラーがあれば入力画面へ
		if (result.hasErrors()) {
			return "update/update_input";
		}
		//空のEmployeeBeanオブジェクトにフォームの入力値をコピー
		EmployeeBean empBean = new EmployeeBean();
		BeanUtils.copyProperties(employeeForm, empBean);
		//フォームのdeptIdから部署名を取得
		Integer deptId = employeeForm.getDeptId();
		Department dept = deptRepo.getReferenceById(deptId);
		String deptName = dept.getDeptName();
		//リクエストスコープにEmployeeBeanオブジェクトと部署名を保存
		model.addAttribute("empBean", empBean);
		model.addAttribute("deptName", deptName);
		return "update/update_check";
	}
	/**
	 * 確認画面で変更ボタン押下時に変更そのものを行うメソッド
	 * @param 社員情報変更＿確認画面から渡されたEmployeeBeanオブジェクト
	 * @param セッションスコープを利用するためのHttpSessionオブジェクトを生成
	 * @return 社員情報変更＿完了画面へ
	 */
	@PostMapping("/update/doUpdate")
	public String doUpdate(EmployeeBean empBean, HttpSession session) {
		//変更内容の入ったBeanをEmployeeオブジェクトにコピー
		Employee emp = new Employee();
		BeanUtils.copyProperties(empBean, emp);
		//beanとEmployeeでは部署がコピーできないので補完
		Department dept = new Department();
		dept.setDeptId(empBean.getDeptId());
		emp.setDepartment(dept);
		//ここでDBに変更内容を上書き
		empRepo.save(emp);
		//以下はログイン中のユーザーの情報が書き換えられた場合を想定した処理
		//ログイン中のユーザー情報をセッションスコープから削除
		Employee loginUser = (Employee) session.getAttribute("user");
		Integer loginId = loginUser.getEmpId();
		session.removeAttribute("user");
		//社員情報変更済みの状態で再度ログイン中のユーザーの情報をセッションスコープに保存
		session.setAttribute("user", empRepo.getReferenceById(loginId));
		return "update/update_complete";
	}
	/**
	 * 社員情報変更_確認画面から社員情報変更＿入力画面へ戻るためのメソッド
	 * @param 確認画面から渡されたEmployeeBeanオブジェクト
	 * @param リクエストスコープを使用するためにModelオブジェクトを生成
	 * @return 社員情報変更＿入力画面へ
	 */
	@PostMapping("/update/back")
	public String backToInputWithInfo(EmployeeBean empBean, Model model) {
		//空のフォームオブジェクトに入力内容の入ったBeanをコピー
		EmployeeForm employeeForm = new EmployeeForm();
		BeanUtils.copyProperties(empBean, employeeForm);
		//リクエストスコープに保存
		model.addAttribute("employeeForm", employeeForm);
		return "update/update_input";
	}
}
