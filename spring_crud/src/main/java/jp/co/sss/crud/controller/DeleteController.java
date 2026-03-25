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

@Controller
public class DeleteController {
	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	DepartmentRepository deptRepo;
	
	@PostMapping("/delete/{empId}")
	public String showDeleteCheck(@PathVariable Integer empId, Model model) {
		Employee emp = empRepo.getReferenceById(empId);
		Department dept = deptRepo.getReferenceById(emp.getDepartment().getDeptId());
		String deptName = dept.getDeptName();
		model.addAttribute("emp", emp);
		model.addAttribute("deptName", deptName);
		return "delete/delete_check";
	}
	
	@PostMapping("/delete/doDelete")
	public String deleteEmployee(Integer empId) {
		empRepo.delete(empRepo.getReferenceById(empId));
		return "delete/delete_complete";
	}
}
