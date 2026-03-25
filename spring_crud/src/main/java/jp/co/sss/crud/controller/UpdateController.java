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

@Controller
public class UpdateController {
	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	DepartmentRepository deptRepo;
	
	@PostMapping("/update/{empId}")
	public String showUpdateInputPost(@PathVariable Integer empId, Model model) {
		Employee emp = empRepo.getReferenceById(empId);
		EmployeeForm employeeForm = new EmployeeForm();
		BeanUtils.copyProperties(emp, employeeForm);
		System.out.println(employeeForm.getDeptId());
		model.addAttribute("employeeForm", employeeForm);
		return "update/update_input";
	}
	
	@GetMapping("/update/input")
	public String showUpdateInputGet(@RequestParam("empId") Integer empId, Model model) {
		Employee emp = empRepo.getReferenceById(empId);
		EmployeeForm employeeForm = new EmployeeForm();
		BeanUtils.copyProperties(emp, employeeForm);
		System.out.println(employeeForm.getDeptId());
		model.addAttribute("employeeForm", employeeForm);
		return "update/update_input";
	}
	
	@PostMapping("/update/check")
	public String checkInput(@Valid @ModelAttribute
	  EmployeeForm employeeForm, BindingResult result ,Model model) {
		if (result.hasErrors()) {
			return "update/update_input";
		}
		EmployeeBean empBean = new EmployeeBean();
		BeanUtils.copyProperties(employeeForm, empBean);
		System.out.println(empBean.getDeptId());
		//フォームのdeptIdから部署名を取得
		Integer deptId = employeeForm.getDeptId();
		Department dept = deptRepo.getReferenceById(deptId);
		String deptName = dept.getDeptName();
		model.addAttribute("empBean", empBean);
		model.addAttribute("deptName", deptName);
		return "update/update_check";
	}
	
	@PostMapping("/update/doUpdate")
	public String doUpdate(EmployeeBean empBean, HttpSession session) {
		Employee emp = new Employee();
		BeanUtils.copyProperties(empBean, emp);
		Department dept = new Department();
		dept.setDeptId(empBean.getDeptId());
		emp.setDepartment(dept);
		empRepo.save(emp);
		Employee loginUser = (Employee) session.getAttribute("user");
		Integer loginId = loginUser.getEmpId();
		session.removeAttribute("user");
		session.setAttribute("user", empRepo.getReferenceById(loginId));
		return "update/update_complete";
	}
	
	@PostMapping("/update/back")
	public String backToInputWithInfo(EmployeeBean empBean, Model model) {
		EmployeeForm employeeForm = new EmployeeForm();
		BeanUtils.copyProperties(empBean, employeeForm);
		model.addAttribute("employeeForm", employeeForm);
		return "update/update_input";
	}
}
