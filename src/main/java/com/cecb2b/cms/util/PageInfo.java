/**
 * @Version 1.0<br>
 * @author haojf<br>
 *         本软件版权属于北京金科林通信科技发展有限公司研发部<br>
 */
package com.cecb2b.cms.util;

import java.io.Serializable;
import java.util.List;

/**
 * title:分页查询<br>
 * description:分页查询<br>
 */
public class PageInfo<T> implements Serializable {

    /**
     * 总页数
     */
    private int totalPage = 1;

    /**
     * 前一页
     */
    private int prePage = 1;

    /**
     * 下一页
     */
    private int nextPage = 1;

    /**
     * 总记录数
     */
    private int totalRec = 0;

    /**
     * 默认每页记录数
     */
    static int defaultPageSize = 10;

    /**
     * 每页记录数
     */
    private int pageSize = defaultPageSize;

    /**
     * 当前页码
     */
    private int pageIndex = 1;

    /**
     * 全部页码，从1开始
     */
    private int[] pageNumbers;

    /**
     * 要显示的页码个数
     */
    private int showPageCount = 9;

    private boolean hasNextPage = false;

    private boolean hasPrevPage = false;

    private List<T> list;

    /**
     * @param start
     *            本页数据在数据库中的起始位置
     * @param totalSize
     *            数据库中总记录条数
     * @param pageSize
     *            本页容量
     * @param list
     *            本页包含的数据
     */
    public PageInfo(int start, int totalSize, int pageSize, List list) {
        this.setPageSize(pageSize);
        this.setTotalRec(totalSize);
        this.list = list;
        int startIndex = (this.getPageIndex() - 1) * this.getPageSize();
        int pagesize = this.getPageSize();
        int[] pageNumbers = new int[this.getTotalPage()];
        for (int i = 0; i < this.getTotalPage(); i++) {
            pageNumbers[i] = (i + 1);
        }
        this.setPageNumbers(pageNumbers);
        this.setPrePage(this.getPageIndex() - 1);
        this.setNextPage(this.getPageIndex() + 1);
        // setPageIndex(start);
        getCurrentPageNo(start);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        // System.out.println("set pageindex:" + pageIndex);
        this.pageIndex = pageIndex > 0 ? pageIndex : 1;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage > this.totalPage ? this.totalPage : nextPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize > 0 ? pageSize : defaultPageSize;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage < 1 ? 1 : prePage;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage > 0 ? totalPage : 1;
    }

    public int getTotalRec() {
        return totalRec;
    }

    public void setTotalRec(int totalRec) {
        this.totalRec = totalRec > -1 ? totalRec : 0;
        this.setTotalPage((totalRec % this.pageSize == 0) ? (totalRec / this.pageSize) : (totalRec / this.pageSize) + 1);
    }

    public int[] getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(int[] pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public List getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int[] getShowPageNumbers() {
        if (totalPage <= showPageCount) {
            return pageNumbers;
        } else {
            int[] newPageNumbers = new int[showPageCount];
            int startPageNumber = 1;
            if (pageIndex > (int) (showPageCount + 1) / 2) {
                startPageNumber += (pageIndex - (int) (showPageCount + 1) / 2);
            }
            boolean flag = false;
            if (startPageNumber + showPageCount > totalPage) {
                startPageNumber = totalPage - showPageCount;
                flag = true;
            }
            for (int i = 0; i < showPageCount; i++) {
                if (flag) {
                    newPageNumbers[i] = startPageNumber + i + 1;
                } else {
                    newPageNumbers[i] = startPageNumber + i;
                }
            }
            return newPageNumbers;
        }
    }

    public int[] getShowDescPageNumbers() {

        int[] ascPageNumbers = getShowPageNumbers();
        int[] newPageNumbers = new int[ascPageNumbers.length];
        for (int i = ascPageNumbers.length; i > 0; i--) {
            newPageNumbers[ascPageNumbers.length - i] = ascPageNumbers[i - 1];
        }
        return newPageNumbers;
    }

    public int getShowPageCount() {
        return showPageCount;
    }

    public void setShowPageCount(int showPageCount) {
        this.showPageCount = showPageCount;
    }

    public boolean isHasNextPage() {
        if (pageIndex < totalPage) {
            hasNextPage = true;
        }
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPrevPage() {
        if (pageIndex > 1) {
            hasPrevPage = true;
        }
        return hasPrevPage;
    }

    public void setHasPrevPage(boolean hasPrevPage) {
        this.hasPrevPage = hasPrevPage;
    }

    public PageInfo() {
    }

    public PageInfo(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 数据库从第几行开始查询
     */
    public int getIndexRec() {
        return (pageIndex - 1) * pageSize + 1;
    }

    @Override
    public String toString() {
        return "PageInfo{" + "totalPage=" + totalPage + ", prePage=" + prePage + ", nextPage=" + nextPage
                + ", totalRec=" + totalRec + ", defaultPageSize=" + defaultPageSize + ", pageSize=" + pageSize
                + ", pageIndex=" + pageIndex + ", showPageCount=" + showPageCount + ", hasNextPage=" + hasNextPage
                + ", hasPrevPage=" + hasPrevPage + '}';
    }

    /**
     * 取该页当前页码,页码从1开始.
     */
    public void getCurrentPageNo(int start) {
        setPageIndex(start / pageSize + 1);
    }

    /**
     * 获取任一页第一条数据在数据集的位置.
     * 
     * @param pageNo
     *            从1开始的页号
     * @param pageSize
     *            每页记录条数
     * @return 该页第一条数据
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    public static PageInfo processPageInfoPageNumbers(PageInfo page) {
        int totalPage = page.getTotalPage();
        if (totalPage > 0) {
            int[] pageNumbers = new int[totalPage];
            for (int i = 0; i < totalPage; i++) {
                pageNumbers[i] = (i + 1);
            }
            page.setPageNumbers(pageNumbers);
            page.setPrePage(page.getPageIndex() - 1);
            page.setNextPage(page.getPageIndex() + 1);
        }

        return page;
    }

    /**
     * 验证PageInfo对象
     */
    public static PageInfo validPageInfo(PageInfo pageInfo) {
        PageInfo result = null;
        if (pageInfo != null) {
            if (pageInfo.getPageIndex() < 1) {
                pageInfo.setPageIndex(1);
            }
            result = pageInfo;
        } else {
            result = new PageInfo();
            result.setPageIndex(1);
            result.setPageSize(defaultPageSize);
        }
        return result;
    }
}
