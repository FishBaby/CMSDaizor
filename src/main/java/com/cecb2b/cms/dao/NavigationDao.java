package com.cecb2b.cms.dao;

import java.util.List;

import com.cecb2b.cms.model.UserMenu;

public interface NavigationDao {
	List<UserMenu> getNameList();

	List<UserMenu> getPidList(Integer id);

	UserMenu getIdList(Integer id);

	List<UserMenu> getAllNameList();
		
}
