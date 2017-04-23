package com.cecb2b.cms.common.helper;

/**
 * Created by LuoGuanHai on 2017/2/21.
 */
public class ConditionHelper {

    private static ThreadLocal<ConditionExt> conditionExtThreadLocal = new ThreadLocal<ConditionExt>();

    public static ConditionExt getConditionExt() {
        ConditionExt ext = conditionExtThreadLocal.get();
        if (null == ext){
            ext = new ConditionExt();
            conditionExtThreadLocal.set(ext);
        }
        return ext;
    }

    public static void setPageNum(Integer pageNum){
        getConditionExt().setPageNum(pageNum);
    }

    public static void setPageSize(Integer pageSize) {
        getConditionExt().setPageSize(pageSize);
    }

    public static void setOrderBy(String orderBy) {
        getConditionExt().setOrderBy(orderBy);
    }

    public static void clear() {
        conditionExtThreadLocal.remove();
    }

    public static class ConditionExt {

        private Integer pageNum;

        private Integer pageSize;

        private String orderBy;

        private ConditionExt() {

        }

        public Integer getPageNum() {
            return pageNum;
        }

        public void setPageNum(Integer pageNum) {
            this.pageNum = pageNum;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }
    }

//    public static void main(String[] args) {
//        ConditionHelper.setPageNum(100);
//        ConditionHelper.setPageSize(5);
//        ConditionHelper.setOrderBy("id, dateCreated desc ");
//
//        ConditionExt ext = ConditionHelper.getConditionExt();
//
//        System.out.println("pageNum=" + ext.getPageNum() + ", pageSize=" + ext.getPageSize() + ", orderBy=" + ext.getOrderBy());
//    }

}
