package com.cecb2b.cms.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cecb2b.cms.model.Demo;
import com.cecb2b.cms.model.UserMenu;
import com.cecb2b.cms.service.DemoService;
import com.cecb2b.cms.service.NavigationService;
import com.cecb2b.cms.util.DateUtils;
import com.cecb2b.cms.util.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminController extends BaseController {

	@Autowired
	private DemoService demoService;

	@Autowired
	private NavigationService navigationService;

	@RequestMapping("/demo/test")
	@ResponseBody
	public String hello() {
		return "Hello world! demo";
	}

	@RequestMapping("/demo/list/{pageNum}")
	@ResponseBody
	public Object listPage(HttpServletRequest req, HttpServletResponse resp,
			@PathVariable("pageNum") Integer pageNum, Model model) {
		buildPageQueryCondition(pageNum);
		Demo demo = new Demo();
		PageInfo<Demo> pageInfo = demoService.listPage(demo);
		return pageInfo;
	}

	@RequestMapping("/index")
	public String index(ModelMap modelMap) {
		List<UserMenu> list = navigationService.getNameList();
		modelMap.put("menu", list);
		return "index";
	}

	@RequestMapping("/childMenus/{id}")
	public String childrens(@PathVariable("id") Integer id, ModelMap modelMap) {
		List<UserMenu> list = navigationService.getPidList(id);
		UserMenu userMenu = navigationService.getIdList(id);
		modelMap.put("menu", list);
		modelMap.put("message", userMenu);
		return "childmenus";
	}

	
}
