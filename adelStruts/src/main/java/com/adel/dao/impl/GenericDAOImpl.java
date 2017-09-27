package com.adel.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adel.dao.GenericDAO;

public class GenericDAOImpl<E, K extends Serializable> implements GenericDAO<E, K> {
	

	@Autowired
    private SessionFactory sessionFactory;	
	
	protected Class<? extends E> daoType;

    /**
     * By defining this class as abstract, we prevent Spring from creating instance of this class
     * If not defined abstract getClass().getGenericSuperClass() would return Object.
     * There would be exception because Object class does not have constructor with parameters.
     */
    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }
    
    
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    /**
     * Create new entity
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public E create(E entity) {
        currentSession().save(entity);
        return entity;
    }

    /**
     * Update the entity
     * @param entity : the {@link Entity}
     * @return entity : the {@link Entity}
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public E update(E entity) {
        currentSession().saveOrUpdate(entity);
        return entity;
    }

    /**
     * delete the entity with id equal to parameter
     * @param key : the {@link Entity} identifier
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(K key) {
    	currentSession().delete(currentSession().get(daoType, key));
    }
    
    /**
     * delete the entity
     * @param entity : the {@link Entity}
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void remove(E entity) {
    	currentSession().delete(entity);
    }

    /**
     * Get entity by id  entity
     * @param key : the id of {@link Entity}
     * @return entity : the {@link Entity}
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public E find(K key) {
    	return (E) currentSession().get(daoType, key);
    }

    /**
     * Get all {@link Entity} depending to the specified parameters
     * @param params : the specified parameters
     * @return {@link List} of the {@link Entity} 
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<E> getAll(final Map<String, Object> params) {
    	final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");

        queryString.append(daoType.getSimpleName()).append(" o ");
        queryString.append(this.getQueryClauses(params, null));

        final Query query = currentSession().createQuery(queryString.toString());

        return query.list();
    }
    
 
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public long countAll(final Map<String, Object> params) {

        final StringBuffer queryString = new StringBuffer(
                "SELECT count(o) from ");

        queryString.append(daoType.getSimpleName()).append(" o ");
        queryString.append(this.getQueryClauses(params, null));

        final Query query = currentSession().createQuery(queryString.toString());

        return (Long) query.uniqueResult();
    }
    
    /**
	 * Constructs the sql "WHERE" clause and "ORDER BY" clause  based on the provided parameters<br>
	 * <b> WHERE CLAUSE</b><br>
	 * if param is a number n, append <b>"param = n"</b><br> 
	 * if param is a boolean, append <b>"param = true or false"</b><br>
	 * if param is a string str, append <b>"param = 'str' " </b>etc...<br> 
	 * <b> ORDER BY CLAUSE </b><br>
	 * append <b> ORDER BY field_1, field_2 </b> etc..
     * @param params
     * @param orderParams
     * @return 
     */
    public String getQueryClauses(final Map<String, Object> params,
			final Map<String, Object> orderParams) {
		final StringBuffer queryString = new StringBuffer();
		if ((params != null) && !params.isEmpty()) {
			queryString.append(" where ");
			
			for (final Iterator<Map.Entry<String, Object>> it = params
					.entrySet().iterator(); it.hasNext();) {
				final Map.Entry<String, Object> entry = it.next();
				if (entry.getValue() instanceof Boolean) {
					queryString.append(entry.getKey()).append(" is ")
							.append(entry.getValue()).append(" ");
				} else {
					if (entry.getValue() instanceof Number) {
						queryString.append(entry.getKey()).append(" = ")
								.append(entry.getValue());
					} else {
						// string equality
						queryString.append(entry.getKey()).append(" = '")
								.append(entry.getValue()).append("'");
					}
				}
				if (it.hasNext()) {
					queryString.append(" and ");
				}
			}
		}
		if ((orderParams != null) && !orderParams.isEmpty()) {
			queryString.append(" order by ");
			for (final Iterator<Map.Entry<String, Object>> it = orderParams
					.entrySet().iterator(); it.hasNext();) {
				final Map.Entry<String, Object> entry = it.next();
				queryString.append(entry.getKey()).append(" ");
				if (entry.getValue() != null) {
					queryString.append(entry.getValue());
				}
				if (it.hasNext()) {
					queryString.append(", ");
				}
			}
		}
		return queryString.toString();
	}
    
    public void flush() {  
    	currentSession().flush();  
    }  
  
    public void clear() {  
    	currentSession().clear();  
    }
}
