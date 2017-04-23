package com.cecb2b.cms.service;

import com.cecb2b.cms.AbstractTest;
import com.cecb2b.cms.common.helper.ConditionHelper;
import com.cecb2b.cms.model.Demo;
import com.cecb2b.cms.util.PageInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by LuoGuanHai on 2017/2/22.
 */
public class DemoServiceTest extends AbstractTest {

    @Resource
    private DemoService demoService;

    private final String TEST_NAME = "test_demoService";

    private final String UPDATE_TEST_NAME_TO = "update_test_demoService";

    @Before
    public void setUp(){

    }

    @Test
    public void listPage() throws Exception {
        Demo demo = null;
        ConditionHelper.setPageNum(1);
        ConditionHelper.setPageSize(2);
        PageInfo<Demo> page = demoService.listPage(demo);
        Assert.assertNotNull(page.getList());
    }

    @Test
    public void add() throws Exception {
        Demo demo = new Demo();
        demo.setName(TEST_NAME);
        demo.setDepict(TEST_NAME);
        demoService.add(demo);
    }


    @Test
    public void update() throws Exception {
        List<Demo> list = demoService.queryByName(TEST_NAME);
        Demo demo = list.get(0);
        Assert.assertTrue(TEST_NAME.equals(demo.getName()));
        demo.setName(UPDATE_TEST_NAME_TO);
        demo.setDepict(UPDATE_TEST_NAME_TO);
        demoService.update(demo);
    }

    @Test
    public void delete() throws Exception {
        List<Demo> list = demoService.queryByName(UPDATE_TEST_NAME_TO);
        Demo demo = list.get(0);
        Assert.assertTrue(UPDATE_TEST_NAME_TO.equals(demo.getName()));
        demoService.delete(demo.getId());
    }



}
