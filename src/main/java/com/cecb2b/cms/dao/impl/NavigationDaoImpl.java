package com.cecb2b.cms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cecb2b.cms.common.base.BaseDO;
import com.cecb2b.cms.common.base.BaseDaoImpl;
import com.cecb2b.cms.dao.NavigationDao;
import com.cecb2b.cms.model.UserMenu;

@Repository
@Transactional
public class NavigationDaoImpl extends BaseDaoImpl<UserMenu, Long> implements
		NavigationDao {

	@Override
	public List<UserMenu> getNameList() {
		StringBuffer hql = new StringBuffer();
		hql.append("from UserMenu where pid = 0");
		List<UserMenu> list = this.list(hql.toString());
		System.out.println(list);
		return list;
	}

	@Override
	public List<UserMenu> getPidList(Integer id) {
		StringBuffer hql = new StringBuffer();
		hql.append("from UserMenu where pid=" + id);
		List<UserMenu> list = this.list(hql.toString());
		return list;
	}

	@Override
	public UserMenu getIdList(Integer id) {
		Long ids = (long) id;
		UserMenu userMenu = this.get(ids);
		return userMenu;
	}

	@Override
	public List<UserMenu> getAllNameList() {
		StringBuffer hql = new StringBuffer();
		hql.append("from UserMenu");
		List<UserMenu> list = this.list(hql.toString());
		return list;
	}
}
