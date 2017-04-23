package com.cecb2b.cms.common.exception;

/**
 * 服务层异常处理类
 * Created by LuoGuanHai on 2017/2/20.
 */
public class ServiceException  extends Exception {

    public ServiceException(){
        super();
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable t) {
        super(msg, t);
    }

    public ServiceException(Throwable t) {
        super(t);
    }

}
