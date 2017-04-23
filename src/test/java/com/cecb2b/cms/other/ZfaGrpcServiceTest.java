package com.cecb2b.cms.other;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.Test;

/**
 * Created by LuoGuanHai on 2017/2/24.
 */
public class ZfaGrpcServiceTest {

    @Test
    public void grpcClientTest() throws Exception {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("218.247.213.198", 8883)
                .usePlaintext(true)
                .build();
        com.zfa.api.support.GreeterGrpc.GreeterBlockingStub stub = com.zfa.api.support.GreeterGrpc.newBlockingStub(channel);
        com.zfa.api.support.AuthorityRequest req = com.zfa.api.support.AuthorityRequest.newBuilder().setAppId(2).setUname("wangye").build();
        com.zfa.api.support.AuthorityReply reply;
        reply = stub.getAuthority(req);
        System.out.println("data=" + reply.getData());
    }
}
