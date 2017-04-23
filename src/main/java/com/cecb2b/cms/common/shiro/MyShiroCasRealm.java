/*
package com.cecb2b.cms.common.shiro;

import com.cecb2b.cms.common.helper.ConfigHelper;
import com.cecb2b.cms.config.ShiroCasConfiguration;
import com.google.common.collect.Sets;
import com.zfa.api.support.AuthorityReply;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.Set;

*/
/**
 * 授权认证用rpc调用
 *
 * Created by LuoGuanHai on 2017/2/23.
 *//*

//@Service("myShiroCasRealm")
public class MyShiroCasRealm extends CasRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroCasRealm.class);

    @PostConstruct
    public void initProperty(){
        setCasServerUrlPrefix(ConfigHelper.getCasServerUrlPrefix());
        // 客户端回调地址
        setCasService(ConfigHelper.getCurrSystemDomainUrl() + ShiroCasConfiguration.casFilterUrlPattern);
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection);
        // 获取rpc远程权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> permissions = Sets.newHashSet();
        permissions.add("demo:add");
        permissions.add("demo:update");
        permissions.add("demo:del");
        permissions.add("demo:show");


        AuthorityReply  authority = getUserAuthorityReply();
        System.out.println("================== Authority = " + authority);

        info.setStringPermissions(permissions);
        return info;
    }

    public AuthorityReply getUserAuthorityReply(){
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("218.247.213.198", 8883)
                .usePlaintext(true)
                .build();
        com.zfa.api.support.GreeterGrpc.GreeterBlockingStub stub = com.zfa.api.support.GreeterGrpc.newBlockingStub(channel);
        com.zfa.api.support.AuthorityRequest req = com.zfa.api.support.AuthorityRequest.newBuilder().setAppId(2).setUname("wangye").build();
        com.zfa.api.support.AuthorityReply reply;
        reply = stub.getAuthority(req);
        return reply;
    }

}
*/
