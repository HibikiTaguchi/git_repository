package jp.co.sss.advanced.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import jp.co.sss.advanced.bean.MenuitemBean;
import jp.co.sss.advanced.entity.Kind;
import jp.co.sss.advanced.entity.Menuitem;
import jp.co.sss.advanced.form.MenuitemForm;
import jp.co.sss.advanced.repository.KindRepository;
import jp.co.sss.advanced.repository.MenuitemRepository;

@Controller
public class AdminMenuitemController {
	@Autowired
	MenuitemRepository miRepo;
	@Autowired
	KindRepository kindRepo;
	
	@GetMapping("/admin/menuitem/list")
	public String showMenuitemList(Model model) {
		model.addAttribute("miList", miRepo.findAllByOrderByMenuitemIdAsc());
		return "/admin/menuitem/menuitem_list";
	}
	
	@GetMapping("/admin/menuitem/list/{kindId}")
	public String showMIListGroupByKindId(@PathVariable Integer kindId, Model model) {
		Kind kind = kindRepo.getReferenceById(kindId);
		model.addAttribute("miList", miRepo.findByKindOrderByMenuitemIdAsc(kind));
		model.addAttribute("kindName", kind.getKindName());
		return "/admin/menuitem/menuitem_list";
	}
	@GetMapping("/admin/menuitem/add")
	public String showAddInput(@Valid @ModelAttribute MenuitemForm menuitemForm, Model model) {
		model.addAttribute("kindList", kindRepo.findAll());
		return "/admin/menuitem/menuitem_add_input";
	}
	@PostMapping("/admin/menuitem/add/complete")
	public String addMenuitem(@Valid @ModelAttribute MenuitemForm menuitemForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "/admin/menuitem/menuitem_add_input";
		}
		Menuitem mi = new Menuitem();
		BeanUtils.copyProperties(menuitemForm, mi);
		mi.setKind(kindRepo.getReferenceById(menuitemForm.getKindId()));
		mi.setModifiedDate(new Date());
		miRepo.save(mi);
		MenuitemBean miBean = new MenuitemBean();
		BeanUtils.copyProperties(mi, miBean);
		miBean.setKindId(mi.getKind().getKindId());
		model.addAttribute("miBean", miBean);
		model.addAttribute("kindName", mi.getKind().getKindName());
		return "/admin/menuitem/menuitem_add_complete";
	}
	@PostMapping("/admin/menuitem/modify/{menuitemId}")
	public String showMenuitemModifyInput(@PathVariable Integer menuitemId, Model model) {
		Menuitem mi = miRepo.getReferenceById(menuitemId);
		MenuitemBean miBean = new MenuitemBean();
		BeanUtils.copyProperties(mi, miBean);
		model.addAttribute("miBean", miBean);
		return "/admin/menuitem/menuitem_modify_input";
	}
	@PostMapping("/admin/menuitem/modify/complete/{menuitemId}")
	public String modifyMenuitem(@PathVariable Integer menuitemId, MenuitemForm form,Model model) {
		Menuitem mi = new Menuitem();
		BeanUtils.copyProperties(form	, mi);
		Kind kind = kindRepo.getReferenceById(form.getKindId());
		mi.setKind(kind);
		mi.setModifiedDate(new Date());
		miRepo.save(mi);
		MenuitemBean miBean = new MenuitemBean();
		BeanUtils.copyProperties(mi, miBean);
		miBean.setKindId(mi.getKind().getKindId());
		model.addAttribute("miBean", miBean);
		return "/admin/menuitem/menuitem_modify_complete";
	}
}
