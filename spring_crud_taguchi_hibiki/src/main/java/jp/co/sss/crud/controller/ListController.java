package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class ListController {
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/list")
	public String showList(Model model) {
		//全件表示のための情報を取得
		model.addAttribute("empList", empRepo.findAll());
		return "list/list";
	}
	
	@GetMapping("/list/empName")
	public String showListByName(String empName, Model model) {
		model.addAttribute("empList", empRepo.findByName(empName));
		return "list/list";
	}
	
	@GetMapping("/list/deptId")
	public String showListByDeptId(Integer deptId, Model model) {
		model.addAttribute("empList", empRepo.findByDeptId(deptId));
		model.addAttribute("selectedDeptId", deptId);
		return "list/list";
	}
}
