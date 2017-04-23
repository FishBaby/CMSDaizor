package com.cecb2b.cms.service;

import com.cecb2b.cms.model.Demo;
import com.cecb2b.cms.util.PageInfo;

import java.util.List;

public interface DemoService {

	PageInfo listPage(Demo demo);

	Demo add(Demo demo);

	void delete(Long id);

	void update(Demo demo);

	List<Demo> queryByName(String name);

}
