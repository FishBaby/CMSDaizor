package com.cecb2b.cms.service.impl;

import com.cecb2b.cms.dao.DemoDao;
import com.cecb2b.cms.model.Demo;
import com.cecb2b.cms.service.DemoService;
import com.cecb2b.cms.util.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by LuoGuanHai on 2017/2/22.
 */
@Service
@Transactional
public class DemoServiceImpl implements DemoService {

	@Resource
	private DemoDao demoDao;

	@Override
	public PageInfo listPage(Demo demo) {
		String hql = "from Demo";
		return demoDao.listPage(hql);
	}

	@Override
	public Demo add(Demo demo) {
		return demoDao.add(demo);
	}

	@Override
	public void delete(Long id) {
		demoDao.delete(id);
	}

	@Override
	public void update(Demo demo) {
		demoDao.update(demo);
	}

	@Override
	public List<Demo> queryByName(String name) {
		return demoDao.queryByName(name);
	}
}
