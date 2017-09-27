package com.adel.dao;

import java.util.List;
import java.util.Map;


public interface GenericDAO<E, K> {
	
    /**
     * Create new entity
     */
    public E create(E entity) ;
    
    /**
     * Update the entity
     * @param entity : the {@link Entity}
     * @return entity : the {@link Entity}
     */
    public E update(E entity) ;
    
    /**
     * delete the entity with id equal to parameter
     * @param key : the {@link Entity} identifier
     */
    public void delete(K key);
    
    /**
     * delete the entity
     * @param entity : the {@link Entity}
     */
    public void remove(E entity);
    
    /**
     * Get entity by id  entity
     * @param key : the id of {@link Entity}
     * @return entity : the {@link Entity}
     */
    public E find(K key);
    
    /**
     * Get the count of  all {@link Entity} depending to the specified parameters
     * @param params : the specified parameters
     * @return {@link List} of the {@link Entity} 
     */
	public long countAll(Map<String, Object> params);
	
    /**
     * Get all {@link Entity} depending to the specified parameters
     * @param params : the specified parameters
     * @return {@link List} of the {@link Entity} 
     */
	public List<E> getAll(Map<String, Object> params);
	public void flush();
    public void clear();
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
			final Map<String, Object> orderParams);
}
