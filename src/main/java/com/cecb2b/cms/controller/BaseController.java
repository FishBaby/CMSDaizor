package com.cecb2b.cms.controller;

import com.cecb2b.cms.common.helper.ConditionHelper;
import com.cecb2b.cms.util.CastUtil;
import com.cecb2b.cms.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LuoGuanHai on 2017/2/23.
 */
public abstract class BaseController {

    public void buildPageQueryCondition(Integer pageNum){
        buildPageQueryCondition(pageNum, null);
    }

    public void buildPageQueryCondition(Integer pageNum, Integer pageSize){
        ConditionHelper.setPageNum(pageNum);
        ConditionHelper.setPageSize(pageSize);
    }

    public void buildPageQueryCondition(HttpServletRequest req){
        buildPageQueryCondition(req, null);
    }

    public void buildPageQueryCondition(HttpServletRequest req, Integer pageSize){
        String pageNumStr = req.getParameter("pageNum");
        int pageNum = 1;
        if (StringUtil.isNotEmpty(pageNumStr)) {
            pageNum = CastUtil.castInt(pageNumStr);
            ConditionHelper.setPageNum(pageNum);
        }

        if (null != pageSize && pageSize > 0){
            ConditionHelper.setPageSize(pageSize);
        }
    }

}
