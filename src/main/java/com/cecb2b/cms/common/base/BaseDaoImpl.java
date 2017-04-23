package com.cecb2b.cms.common.base;

import com.cecb2b.cms.common.helper.ConditionHelper;
import com.cecb2b.cms.common.exception.DaoExcepton;
import com.cecb2b.cms.util.PageInfo;
import com.cecb2b.cms.util.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 */
@Transactional
public class BaseDaoImpl<T extends BaseDO, PK extends Serializable> implements BaseDao <T, PK> {

    @Resource
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public Class<T> getClazz(){
        if (null == clazz) {
            //通过反射获取T的类型信息实例
            this.clazz = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()) .getActualTypeArguments()[0];
        }
        return this.clazz;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T add(T entity) {
        try {
            getSession().save(entity);
            return entity;
        } catch (Exception e) {
            throw new DaoExcepton(e);
        }
    }

    @Override
    public void delete(PK id) {
        try {
            getSession().delete(get(id));
        } catch (Exception e) {
            throw new DaoExcepton(e);
        }
    }

    @Override
    public void update(T entity) {
        try {
            getSession().merge(entity);
        } catch (Exception e) {
            throw new DaoExcepton(e);
        }
    }

    @Override
    public T get(PK id) {
        return get(getClazz(), id);
    }

    @Override
    public T get(Class<T> clazz, PK id) {
        try {
            return (T) getSession().get(clazz, id);
        } catch (Exception e) {
            throw new DaoExcepton("dao get entity excepton, [id="+id+"]", e);
        }
    }

    @Override
    public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
        hql = initSort(hql, true);
        Query query = getSession().createQuery(hql);
        setAliasParameter(query, alias);
        setParameter(query, args);
        return query.list();
    }

    @Override
    public List<T> list(String hql, Object[] args) {
        return list(hql,args, null);
    }

    @Override
    public List<T> list(String hql, Object args) {
        return list(hql, new Object[]{args}, null);
    }

    @Override
    public List<T> listByAlias(String hql, Map<String, Object> alias) {
        return list(hql, null, alias);
    }

    @Override
    public List<T> list(String hql) {
        return list(hql, null);
    }

    @Override
    public PageInfo<T> listPage(String hql, Object[] args, Map<String, Object> alias) {
        String countHql = getCountSql(hql);
        hql = initSort(hql, true);

        Query q = getSession().createQuery(hql);
        Query c = getSession().createQuery(countHql);

        // 别名
        setAliasParameter(q, alias);
        setAliasParameter(c, alias);

        // 条件
        setParameter(q, args);
        setParameter(c, args);

        int count = ((Long) c.uniqueResult()).intValue();
        PageInfo page = new PageInfo();
        setPageInfoQueryCondition(q, page);
        List<T> datas = null;
        if (count > 0) {
            datas = q.list();
        }
        setPageInfoResult(page, count, datas);
        return page;
    }

    @Override
    public PageInfo<T> listPage(String hql, Object[] args) {
        return listPage(hql,args, null);
    }

    @Override
    public PageInfo<T> listPage(String hql, Object args) {
        return listPage(hql, new Object[]{args}, null);
    }

    @Override
    public PageInfo<T> listPageByAlias(String hql, Map<String, Object> alias) {
        return listPage(hql, null, alias);
    }

    @Override
    public PageInfo<T> listPage(String hql) {
        return listPage(hql, null);
    }

    @Override
    public Object queryObject(String hql, Object[] args, Map<String, Object> alias) {
        hql = initSort(hql, true);
        Query query = getSession().createQuery(hql);
        setAliasParameter(query, alias);
        setParameter(query, args);
        return query.uniqueResult();
    }

    @Override
    public Object queryObject(String hql, Object[] args) {
        return queryObject(hql, args, null);
    }

    @Override
    public Object queryObject(String hql, Object args) {
        return queryObject(hql, new Object[]{args}, null);
    }

    @Override
    public Object queryObjectByAlias(String hql, Map<String, Object> alias) {
        return queryObject(hql, null, alias);
    }

    @Override
    public Object queryObject(String hql) {
        return queryObject(hql, null);
    }

    @Override
    public void update(String hql, Object[] args) {
        try {
            Query q = getSession().createQuery(hql);
            setParameter(q, args);
            q.executeUpdate();
        } catch (Exception e) {
            throw new DaoExcepton("update by hql err, [hql=" + hql + "]", e);
        }
    }

    @Override
    public void update(String hql, Object args) {
        update(hql, new Object[]{args});
    }

    @Override
    public void update(String hql) {
        update(hql, null);
    }

    @Override
    public List<? extends Object> list(String sql, Object[] args, Map<String, Object> alias, Class<?> clazz, boolean isPoEntity) {
        SQLQuery query = getSession().createSQLQuery(sql);
        sql = initSort(sql, false);

        setAliasParameter(query, alias);
        setParameter(query, args);

        if (isPoEntity) {
            query.addEntity(clazz);
        } else {
            query.setResultTransformer(Transformers.aliasToBean(clazz));
        }

        return query.list();
    }

    @Override
    public List<? extends Object> list(String sql, Object[] args, Class<?> clazz, boolean isPoEntity) {
        return list(sql, args, null, clazz, isPoEntity);
    }

    @Override
    public List<? extends Object> list(String sql, Object args, Class<?> clazz, boolean isPoEntity) {
        return list(sql, new Object[]{args}, clazz, isPoEntity);
    }

    @Override
    public List<? extends Object> listByAlias(String sql, Map<String, Object> alias, Class<?> clazz, boolean isPoEntity) {
        return list(sql, null, alias, clazz, isPoEntity);
    }

    @Override
    public List<? extends Object> list(String sql, Class<?> clazz, boolean isPoEntity) {
        return list(sql, null, null, clazz, isPoEntity);
    }

    @Override
    public PageInfo<? extends Object> listPage(String sql, Object[] args, Map<String, Object> alias, Class<?> clazz, boolean isPoEntity) {
        String countSql = getCountSql(sql);
        sql = initSort(sql, false);
        Query c = getSession().createSQLQuery(countSql);
        Query q = getSession().createSQLQuery(sql);

        setAliasParameter(c, alias);
        setAliasParameter(q, alias);

        setParameter(c, args);
        setParameter(q, args);

        PageInfo<Object> page = new PageInfo<Object>();
        setPageInfoQueryCondition(q, page);
        int count = ((BigInteger) c.uniqueResult()).intValue();
        List<Object> datas = null;
        if (count > 0) {
             datas = q.list();
        }
        setPageInfoResult(page, count, datas);
        return page;
    }

    @Override
    public PageInfo<? extends Object> listPage(String sql, Object[] args, Class<?> clazz, boolean isPoEntity) {
        return listPage(sql, args, null, clazz, isPoEntity);
    }

    @Override
    public PageInfo<? extends Object> listPage(String sql, Object args, Class<?> clazz, boolean isPoEntity) {
        return listPage(sql, new Object[]{args}, null, clazz, isPoEntity);
    }

    @Override
    public PageInfo<? extends Object> listPageByAlias(String sql, Map<String, Object> alias, Class<?> clazz, boolean isPoEntity) {
        return listPage(sql, null, alias, clazz, isPoEntity);
    }

    @Override
    public PageInfo<? extends Object> listPage(String sql, Class<?> clazz, boolean isPoEntity) {
        return listPage(sql, null, null, clazz, isPoEntity);
    }

    private String initSort(String hql, boolean isHql){
        String orderByStr = ConditionHelper.getConditionExt().getOrderBy();
        if (StringUtil.isNotEmpty(orderByStr)) {
            hql += " order by " + orderByStr;
        }

        if (isHql){
            hql.replaceAll("fetch", "");
        }
        return hql;
    }

    private void setParameter(Query query, Object[] args){
        if (null == query || null == args || args.length <= 0){
            return;
        }

        int index = 0;
        for (Object o : args) {
            query.setParameter(index++, o);
        }
    }

    private void setAliasParameter(Query query, Map<String, Object> alias) {

        if (null == query || null == alias || alias.isEmpty()) {
            return;
        }

        for (Entry<String, Object> e : alias.entrySet()) {
            String key = e.getKey();
            Object v = e.getValue();
            if (v instanceof Collection){
                query.setParameterList(key, (Collection)v);
            } else {
                query.setParameter(key, v);
            }
        }
    }

    private String getCountSql(String hql) {
        String countHql = "select count(*) " + hql.substring(hql.indexOf("from"));
        return countHql;
    }

    private void setPageInfoQueryCondition(Query query, PageInfo pageInfo) {
        Integer pageNum = ConditionHelper.getConditionExt().getPageNum();
        pageInfo.setPageIndex(null == pageNum || pageNum < 1 ? 1 : pageNum);

        Integer pageSize = ConditionHelper.getConditionExt().getPageSize();
        if (null != pageSize && pageSize > 0){
            pageInfo.setPageSize(pageSize);
        }
        int size = pageInfo.getPageSize();
        int offset = (pageInfo.getPageIndex() - 1) * size;
        query.setFirstResult(offset).setMaxResults(size);
    }

    private void setPageInfoResult(PageInfo pageInfo, int totalCount, List<?> datas){
        pageInfo.setTotalRec(totalCount);
        int totalPage = pageInfo.getTotalPage();
        int[] pageNumbers = new int[pageInfo.getTotalPage()];
        for (int i = 0; i < totalPage; i++) {
            pageNumbers[i] = (i + 1);
        }
        pageInfo.setPageNumbers(pageNumbers);
        pageInfo.setPrePage(pageInfo.getPageIndex() - 1);
        pageInfo.setNextPage(pageInfo.getPageIndex() + 1);
        pageInfo.setList(datas);
    }
}
