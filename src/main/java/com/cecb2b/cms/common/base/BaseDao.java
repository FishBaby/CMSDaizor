package com.cecb2b.cms.common.base;

import com.cecb2b.cms.util.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * 公共Dao类, 所有dao都要继承该类
 *
 * Created by LuoGuanHai on 2017/2/21.
 */
public interface BaseDao<T extends BaseDO, PK extends Serializable> {

	T add(T entity);

	void delete(PK id);

	void update(T entity);

	T get(PK id);

	T get(Class<T> clazz, PK id);

	List<T> list(String hql, Object[] args, Map<String, Object> alias);

	List<T> list(String hql, Object[] args);

	List<T> list(String hql, Object args);

	List<T> listByAlias(String hql, Map<String, Object> alias);

	List<T> list(String hql);

	PageInfo<T> listPage(String hql, Object[] args, Map<String, Object> alias);

	PageInfo<T> listPage(String hql, Object[] args);

	PageInfo<T> listPage(String hql, Object args);

	PageInfo<T> listPageByAlias(String hql, Map<String, Object> alias);

	PageInfo<T> listPage(String hql);

	Object queryObject(String hql, Object[] args, Map<String, Object> alias);

	Object queryObject(String hql, Object[] args);

	Object queryObject(String hql, Object args);

	Object queryObjectByAlias(String hql, Map<String, Object> alias);

	Object queryObject(String hql);

	void update(String hql, Object[] args);

	void update(String hql, Object args);

	void update(String hql);

	List<? extends Object> list(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clazz, boolean isPoEntity);

	List<? extends Object> list(String sql, Object[] args, Class<?> clazz,
			boolean isPoEntity);

	List<? extends Object> list(String sql, Object args, Class<?> clazz,
			boolean isPoEntity);

	List<? extends Object> listByAlias(String sql, Map<String, Object> alias,
			Class<?> clazz, boolean isPoEntity);

	List<? extends Object> list(String sql, Class<?> clazz, boolean isPoEntity);

	PageInfo<? extends Object> listPage(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clazz, boolean isPoEntity);

	PageInfo<? extends Object> listPage(String sql, Object[] args,
			Class<?> clazz, boolean isPoEntity);

	PageInfo<? extends Object> listPage(String sql, Object args,
			Class<?> clazz, boolean isPoEntity);

	PageInfo<? extends Object> listPageByAlias(String sql,
			Map<String, Object> alias, Class<?> clazz, boolean isPoEntity);

	PageInfo<? extends Object> listPage(String sql, Class<?> clazz,
			boolean isPoEntity);

}
