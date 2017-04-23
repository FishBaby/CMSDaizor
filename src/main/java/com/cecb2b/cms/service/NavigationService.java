package com.cecb2b.cms.service;

import java.util.List;

import com.cecb2b.cms.model.UserMenu;

public interface NavigationService {
	List<UserMenu> getNameList();

	List<UserMenu> getPidList(Integer id);

	UserMenu getIdList(Integer id);

	List<UserMenu> getAllNameList();
}
