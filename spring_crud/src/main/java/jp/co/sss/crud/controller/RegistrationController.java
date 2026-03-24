package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;
import jp.co.sss.crud.util.BeanCopy;

@Controller
public class RegistrationController {
	@Autowired
	EmployeeRepository empRepo;
	
	/**
	 * 
	 * @param form
	 * @return regist/regist_input.html
	 */
	@GetMapping("/regist/input")
	public String showRegistInput(@ModelAttribute EmployeeForm form, Employee employee) {
		return "regist/regist_input";
	}
	
	/**
	 * 
	 * @param form
	 * @param result
	 * @param model
	 * @return regist/regist_input.html 
	 * 			新規登録社員情報入力画面	
	 * 			入力チェックに引っかかった場合
	 * @return regist/regist_check.html
	 * 			入力社員情報確認画面
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
	 * 
	 * @param emp
	 * @return regist/regist_complete.html
	 * 			新規社員情報登録完了
	 */
	@PostMapping("/regist/doRegist")
	public String doRegist(Employee emp, HttpSession session) {
		empRepo.save(emp);
		return "regist/regist_complete";
	}
	
	@PostMapping("/regist/back")
	public String backToInputWithInfo(Employee employee, Model model) {
		EmployeeForm form = new EmployeeForm();
		model.addAttribute("employeeForm", BeanCopy.copyEntityToForm(employee, form));
		return "regist/regist_input";
	}
}
