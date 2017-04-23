package com.cecb2b.cms.dao.impl;

import com.cecb2b.cms.common.base.BaseDaoImpl;
import com.cecb2b.cms.dao.DemoDao;
import com.cecb2b.cms.model.Demo;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by LuoGuanHai on 2017/2/22.
 */
@Repository
@Transactional
public class DemoDaoImpl extends BaseDaoImpl<Demo, Long> implements DemoDao {

	@Override
	public List<Demo> queryByName(String name) {
		String hql = "from Demo where name = ?";
		Object[] args = { name };
		return this.list(hql, args);
	}
}
