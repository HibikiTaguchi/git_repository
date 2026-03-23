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
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class UpdateController {
	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	DepartmentRepository deptRepo;
	
	@PostMapping("/update/{empId}")
	public String showUpdateInput(@PathVariable Integer empId, Model model) {
		Employee emp = empRepo.getReferenceById(empId);
		EmployeeBean empBean = new EmployeeBean();
		BeanUtils.copyProperties(emp, empBean);
		System.out.println(empBean.getDeptId());
		model.addAttribute("empBean", empBean);
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
		//入力されたフォームの値をBeanにコピー
		EmployeeBean empBean = new EmployeeBean();
		BeanUtils.copyProperties(userForm, empBean);
		System.out.println(empBean.getDeptId());
		//フォームのdeptIdから部署名を取得
		Integer deptId = userForm.getDeptId();
		Department dept = deptRepo.getReferenceById(deptId);
		String deptName = dept.getDeptName();
		model.addAttribute("empBean", empBean);
		model.addAttribute("deptName", deptName);
		return "update/update_check";
	}
	
	@PostMapping("/update/doUpdate")
	public String doUpdate(EmployeeBean empBean) {
		Employee emp = new Employee();
		BeanUtils.copyProperties(empBean, emp);
		Department dept = new Department();
		dept.setDeptId(empBean.getDeptId());
		emp.setDepartment(dept);
		empRepo.save(emp);
		return "update/update_complete";
	}
	
	@PostMapping("/update/back")
	public String backToInputWithInfo(EmployeeBean empBean, Model model) {
		model.addAttribute("empBean", empBean);
		return "update/update_input";
	}
}
