package com.cecb2b.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cecb2b.cms.model.UserMenu;
import com.cecb2b.cms.service.NavigationService;

@Controller
@RequestMapping("/SecondaryNavigation")
public class SecondaryNavigationController {

	@Autowired
	private NavigationService navigationService;

	@RequestMapping("/aboutsystem")
	public String aboutsystem() {
		return "aboutsystem";
	}

	@RequestMapping("/menumenagment")
	public String menumenagment(ModelMap modelMap) {
		List<UserMenu> list=navigationService.getAllNameList();
		modelMap.put("names", list);
		return "menumenagment";
	}
}
