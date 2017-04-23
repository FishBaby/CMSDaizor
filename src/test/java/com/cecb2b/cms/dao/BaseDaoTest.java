package com.cecb2b.cms.dao;

import com.cecb2b.cms.AbstractTest;
import com.cecb2b.cms.common.helper.ConditionHelper;
import com.cecb2b.cms.model.Demo;
import com.cecb2b.cms.util.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by LuoGuanHai on 2017/2/22.
 */
public class BaseDaoTest extends AbstractTest {
    @Resource
    private DemoDao dao;

    private Demo demo;

    private String hql;

    private String alias_hql;

    private String sql;

    private long id = 21;

    @Before
    public void setUp(){
        demo = new Demo();
        demo.setName("u_" + System.currentTimeMillis());
        demo.setDepict("depict_" + System.currentTimeMillis());

        hql = "from Demo where id = ?";
        sql = "from demo where id = ?";
        alias_hql = "from Demo d where d.name=? and d.id in (:demoid)"; // 注意hql别名必须放后面, 否则将会报错
    }

    @Test
    public void addTest() throws Exception {
        Demo u = dao.add(demo);
        Assert.assertNotNull(u);
        Assert.assertTrue(u.getId() > 1);
    }
    @Test
    public void deleteTest() throws Exception{
        List<Demo> list =  dao.list(hql);
        Assert.assertNotNull(list);

        int size = list.size();
        System.out.println("delete list size: " + size);

        dao.delete(list.get(0).getId());

        list = dao.list(hql);
        int size2 = list.size();
        System.out.println("delete list size2: " + size2);
        Assert.assertTrue(size != size2);
    }


    @Test
    public void updateTest() throws Exception {
        Demo demo = dao.get(id);
        Assert.assertTrue("u_1487741784749".equals(demo.getName()));
        String depict = demo.getDepict() + "_update";
        demo.setDepict(depict);
        dao.update(demo);
        Demo d = dao.get(id);
        Assert.assertTrue(demo.getDepict().equals(d.getDepict()));
    }

    @Test
    public void listByHqlTest() throws Exception {
        Object[] arges = {21L};
        List<Demo> list = dao.list(hql, arges);
        Assert.assertNotNull(list);
    }


    @Test
    public void listByAliasHqlTest() throws Exception {
        Object[] args = {"u_1487741784749"};

        List<Object> aliaslist = Lists.newArrayList();
        aliaslist.add(20L);
        aliaslist.add(21L);
        Map<String, Object> alias = Maps.newHashMap();
        alias.put("demoid", aliaslist);
        List<Demo> datas = dao.list(alias_hql, args, alias);
        Assert.assertNotNull(datas);
    }

    @Test
    public void listPageByHqlTest() throws Exception {
        String hql = "from Demo where id >= ? and id in (:demoids)";
        Object[] args = {19L};
        Map<String, Object> alias = Maps.newHashMap();
        List<Object> list = Lists.newArrayList();
        list.add(20L);
        list.add(21L);
        alias.put("demoids", list);

        ConditionHelper.setPageNum(1);
        ConditionHelper.setPageSize(1);
        PageInfo<Demo> page = dao.listPage(hql, args, alias);
        Assert.assertNotNull(page);
        Assert.assertTrue(page.getTotalPage() == 2);
    }

    @Test
    public void queryObjectTest() throws Exception {
        String name = "u_1487748492118";
        String hql = "from Demo where name=? and id in (:demoids)";
        Object[] args = {name};
        Map<String, Object> alias = Maps.newHashMap();
        List<Long> demoids = Lists.newArrayList();
        demoids.add(20L);
        demoids.add(21L);
        demoids.add(22L);
        demoids.add(23L);
        alias.put("demoids", demoids);
        Demo demo = (Demo) dao.queryObject(hql, args, alias);
        Assert.assertTrue(name.equals(demo.getName()));
    }

    @Test
    public void updateByHqlTest() throws Exception {
        String hql = "update Demo set name=? where id = ?";
        String name = "name_updateByHqlTest";
        long id = 22L;
        Object[] args = {name, id};
        dao.update(hql, args);

        Demo demo = dao.get(id);
        Assert.assertTrue(name.equals(demo.getName()));

    }

    @Test
    public void listBySqlTest() throws Exception {
        String sql = "select name from demo where name = ? and id in (:demoids) ";
        String name = "u_1487741752898";

        Object[] args = {name};
        List<Long> demoids = Lists.newArrayList();
        demoids.add(20L);
        demoids.add(21L);
        demoids.add(22L);
        demoids.add(23L);
        Map<String, Object> alias = Maps.newHashMap();
        alias.put("demoids", demoids);
        boolean isPoEntity = true;
        List<Demo> datas = (List<Demo>) dao.list(sql, args, alias, Demo.class, isPoEntity);
        Assert.assertNotNull(datas);
        Demo d = datas.get(0);
        Assert.assertTrue(name.equals(d.getName()));
    }

    @Test
    public void listPageBySqlTest() throws Exception {
        String sql = "select name from demo where name = ? and id in (:demoids) ";
        String name = "u_1487748492118";

        Object[] args = {name};
        List<Long> demoids = Lists.newArrayList();
        demoids.add(20L);
        demoids.add(21L);
        demoids.add(22L);
        demoids.add(23L);
        Map<String, Object> alias = Maps.newHashMap();
        alias.put("demoids", demoids);
        boolean isPoEntity = false;

        PageInfo<Demo> page = (PageInfo<Demo>) dao.listPage(sql, args, alias, Demo.class, isPoEntity);
        Assert.assertTrue(page.getTotalPage() == 1);

    }

}
