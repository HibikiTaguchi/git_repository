package jp.co.sss.advanced.controller;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.advanced.bean.StaffBean;
import jp.co.sss.advanced.entity.Staff;
import jp.co.sss.advanced.form.LoginForm;
import jp.co.sss.advanced.form.StaffForm;
import jp.co.sss.advanced.repository.StaffRepository;

@Controller
public class AdminStaffController {
	@Autowired
	StaffRepository stfRepo;
	@Autowired
	HttpSession session;
	
	@GetMapping("/admin/staff/list")
	public String showStaffList(Model model) {
		model.addAttribute("stfList", stfRepo.findAllByDeleteFlagOrderByStaffIdAsc(0));
		return "/admin/staff/staff_list";
	}
	@GetMapping("/admin/staff/register")
	public String showRegisterInput(@Valid @ModelAttribute 
	  StaffForm staffForm) {
		return "/admin/staff/staff_register_input";
	}
	@PostMapping("/admin/staff/register/complete")
	public String doRegister(@Valid @ModelAttribute 
	  StaffForm staffForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "/admin/staff/staff_register_input";
		}
		Staff staff = new Staff();
		BeanUtils.copyProperties(staffForm, staff);
		stfRepo.save(staff);
		StaffBean stfBean = new StaffBean();
		BeanUtils.copyProperties(staff, stfBean);
		model.addAttribute("staff", stfBean);
		return "/admin/staff/staff_register_complete";
	}
	@PostMapping("/admin/staff/update/{staffId}")
	public String showUpdateInput(@Valid @ModelAttribute StaffForm staffForm,
	  @PathVariable Integer staffId, StaffBean stfBean, Model model) {
		Staff staff = stfRepo.getReferenceById(staffId);
		BeanUtils.copyProperties(staff, stfBean);
		model.addAttribute("stfBean", stfBean);
		return "/admin/staff/staff_update_input";
	}
	@PostMapping("/admin/staff/update/complete/{staffId}")
	public String doUpdate(@Valid @ModelAttribute StaffForm staffForm,
	  BindingResult result, @PathVariable Integer staffId, Model model, Staff staff, StaffBean stfBean) {
		if (result.hasErrors()) {
			return "/admin/staff/staff_update_input";
		}
		staffForm.setStaffId(staffId);
		BeanUtils.copyProperties(staffForm, staff);
		stfRepo.save(staff);
		BeanUtils.copyProperties(staff, stfBean);
		model.addAttribute("stfBean", stfBean);
		return "/admin/staff/staff_update_complete";
	}
	@PostMapping("/admin/staff/delete/{staffId}")
	public String askDeleteOrNot(@PathVariable Integer staffId, StaffBean stfBean, Model model) {
		Staff staff = stfRepo.getReferenceById(staffId);
		BeanUtils.copyProperties(staff, stfBean);
		model.addAttribute("stfBean", stfBean);
		return "/admin/staff/staff_delete_confirm";
	}
	@PostMapping("/admin/staff/delete/complete/{staffId}")
	public String doDelete(@PathVariable Integer staffId, Staff staff, Model model) {
		staff = stfRepo.getReferenceById(staffId);
		staff.setDeleteFlag(1);
		stfRepo.save(staff);
		model.addAttribute("staff", staff);
		return "/admin/staff/staff_delete_complete";
	}
	@GetMapping("/admin")
	public String showLogin(@ModelAttribute LoginForm loginForm, Model model) {
		return "/admin/login";
	}
	@PostMapping("/admin/login")
	public String doLogin(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "/admin/login";
		}
		try {
			Staff staff = stfRepo.findByDeleteFlagAndAccessIdAndPassword(0, loginForm.getAccessId(), loginForm.getPassword());
			System.out.println(staff.getStaffName());
			if (Objects.equals(staff, null)) {
				return "forward:/admin";
			} else {
				session.setAttribute("loginUser", staff);
				return "redirect:/admin/staff/list";
			}
		} catch (Exception e) {
			String errMsg = "SQL実行エラー";
			model.addAttribute("errMsg", errMsg);
			return "forward:/admin";
		}
	}
	@GetMapping("/admin/logout")
	public String doLogout() {
		session.invalidate();
		return "redirect:/admin";
	}
}