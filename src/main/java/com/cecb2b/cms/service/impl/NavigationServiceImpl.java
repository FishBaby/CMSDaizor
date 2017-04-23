package com.cecb2b.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cecb2b.cms.dao.NavigationDao;
import com.cecb2b.cms.model.UserMenu;
import com.cecb2b.cms.service.NavigationService;

@Service
@Transactional
public class NavigationServiceImpl implements NavigationService {
	@Resource
	NavigationDao navigationDao;

	@Override
	public List<UserMenu> getNameList() {
		return navigationDao.getNameList();
	}

	@Override
	public List<UserMenu> getPidList(Integer id) {
		return navigationDao.getPidList(id);
	}

	@Override
	public UserMenu getIdList(Integer id) {
		return navigationDao.getIdList(id);
	}

	@Override
	public List<UserMenu> getAllNameList() {
		return navigationDao.getAllNameList();
	}

}
