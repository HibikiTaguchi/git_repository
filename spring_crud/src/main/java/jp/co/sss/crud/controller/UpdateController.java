package jp.co.sss.crud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;
import jp.co.sss.crud.util.BeanCopy;

@Controller
public class UpdateController {
	@Autowired
	EmployeeRepository empRepo;
	
	@PostMapping("/update/{empId}")
	public String showUpdateInput(@PathVariable Integer empId, Model model) {
		EmployeeForm userForm = new EmployeeForm();
		Employee emp = empRepo.getReferenceById(empId);
		BeanUtils.copyProperties(emp, userForm);
		userForm.setDeptId(emp.getDepartment().getDeptId());
		//フォームにdeptIdが格納できているかデバッグ
		System.out.println(userForm.getDeptId());
		model.addAttribute("userForm", userForm);
		return "update/update_input";
	}
	
	@PostMapping("/update/check")
	public String checkInput(@Valid @ModelAttribute
	  EmployeeForm userForm, BindingResult result ,Model model) {
		if (result.hasErrors()) {
			return "update/update_input";
		}
		//デバッグ　フォームに値が入っているか
		System.out.println(userForm.getEmpName());
		System.out.println(userForm.getDeptId());
		Employee employee = BeanCopy.copyFormToEmployee(userForm);
		System.out.println(employee.getDepartment().getDeptName());
		model.addAttribute("employee", employee);
		return "update/update_check";
	}
	
	@PostMapping("/update/doUpdate")
	public String doUpdate(Employee employee) {
		empRepo.save(employee);
		return "update/update_complete";
	}
	
	@PostMapping("/update/back")
	public String backToInputWithInfo(Employee employee, Model model) {
		EmployeeForm form = new EmployeeForm();
		model.addAttribute("employeeForm", BeanCopy.copyEntityToForm(employee, form));
		return "update/update_input";
	}
}
