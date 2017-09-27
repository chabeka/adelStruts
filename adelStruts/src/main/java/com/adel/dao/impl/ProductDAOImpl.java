package com.adel.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;


import com.adel.dao.IProductDAO;
import com.adel.model.Product;

public class ProductDAOImpl implements IProductDAO {

	private SessionFactory sessionFactory;
	 
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    @Transactional
    public List<Product> list() {
        @SuppressWarnings("unchecked")
        List<Product> listProduct = (List<Product>)
            sessionFactory.getCurrentSession().createCriteria(Product.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        return listProduct;
    }

}
