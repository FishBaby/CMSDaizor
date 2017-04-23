package com.cecb2b.cms.dao;

import com.cecb2b.cms.common.base.BaseDao;
import com.cecb2b.cms.model.Demo;

import java.util.List;

/**
 * Created by LuoGuanHai on 2017/2/22.
 */
public interface DemoDao  extends BaseDao<Demo, Long> {

    List<Demo> queryByName(String name);

}
