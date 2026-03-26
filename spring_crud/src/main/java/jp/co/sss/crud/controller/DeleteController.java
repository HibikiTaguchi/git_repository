package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;
/**
 * 社員情報削除機能にまつわるメソッドをまとめたコントローラ 
 */
@Controller
public class DeleteController {
	/**
	 * オブジェクトの呼び出しを不要にしたEmployeeRepositoryインターフェースのフィールド
	 */
	@Autowired
	EmployeeRepository empRepo;
	/**
	 * オブジェクトの呼び出しを不要にしたDepartmentRepositoryインターフェースのフィールド
	 */
	@Autowired
	DepartmentRepository deptRepo;
	/**
	 * 削除ボタンから削除確認画面へ遷移するためのメソッド
	 * 管理者権限の社員一覧表示画面の削除ボタンを押したときに実行される。
	 * URLパラメータから削除する社員IDを取得し、該当社員のオブジェクトを生成。
	 * 社員オブジェクトと所属部署名を削除確認画面に渡す。
	 * @param 削除する社員の社員ID
	 * @param メソッド内で使用するmodelのオブジェクト生成
	 * @return 削除確認画面へ
	 */
	@PostMapping("/delete/{empId}")
	public String showDeleteCheck(@PathVariable Integer empId, Model model) {
		Employee emp = empRepo.getReferenceById(empId);
		Department dept = deptRepo.getReferenceById(emp.getDepartment().getDeptId());
		String deptName = dept.getDeptName();
		model.addAttribute("emp", emp);
		model.addAttribute("deptName", deptName);
		return "delete/delete_check";
	}
	
	/**
	 * 社員情報削除を実行するメソッド
	 * 削除確認画面から送られた社員IDを元に該当社員の情報をDBから物理削除
	 * @param 情報を削除する社員の社員ID
	 * @return 削除完了画面へ
	 */
	@PostMapping("/delete/doDelete")
	public String deleteEmployee(Integer empId) {
		empRepo.delete(empRepo.getReferenceById(empId));
		return "delete/delete_complete";
	}
}
