package com.adel.action;

import java.util.List;

import com.adel.dao.IProductDAO;
import com.adel.model.Product;
import com.opensymphony.xwork2.ActionSupport;
 
public class ListProductAction extends ActionSupport {
    private IProductDAO productDAO;
    private List<Product> listProduct;
 
    public void setProductDAO(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }
 
    public String execute() {
        listProduct = productDAO.list();
        return SUCCESS;
    }
 
    public List<Product> getListProduct() {
        return listProduct;
    }
}