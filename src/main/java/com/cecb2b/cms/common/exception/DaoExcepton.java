package com.cecb2b.cms.common.exception;

/**
 * Dao异常辅助类
 * Created by LuoGuanHai on 2017/2/20.
 */
public class DaoExcepton extends RuntimeException {

    public DaoExcepton(){
        super();
    }

    public DaoExcepton(String msg){
        super(msg);
    }

    public DaoExcepton(String msg, Throwable t) {
        super(msg, t);
    }

    public DaoExcepton(Throwable t) {
        super(t);
    }
}
