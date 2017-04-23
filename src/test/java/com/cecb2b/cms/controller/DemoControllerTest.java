package com.cecb2b.cms.controller;

import com.cecb2b.cms.AbstractControllerTest;
import com.cecb2b.cms.common.helper.ConditionHelper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Created by LuoGuanHai on 2017/2/23.
 */
public class DemoControllerTest extends AbstractControllerTest {

    @Test
    public void listPageTest() throws Exception {

        ConditionHelper.setPageSize(2);
        String uri = "/demo/list/10";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        System.out.println("status=" + status);
        System.out.println("content=" + content);
    }
}
