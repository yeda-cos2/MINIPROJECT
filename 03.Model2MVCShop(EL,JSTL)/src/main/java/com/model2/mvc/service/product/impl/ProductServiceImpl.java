package com.model2.mvc.service.product.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;

public class ProductServiceImpl implements ProductService {

	// Field
	private ProductDAO productDAO;

	// Constructor
	public ProductServiceImpl() {
		productDAO = new ProductDAO();
	}

	// Method
	public void addProduct(Product product) throws Exception {
		productDAO.insertProduct(product);
	}


	public Map<String, Object> getProductList(Search search) throws Exception {
		return productDAO.getProductList(search);
	}

	public Product getProduct(int prodNo) throws Exception {
		return productDAO.findProduct(prodNo);
	}
	public void updateProduct(Product product) throws Exception {
		productDAO.updateProduct(product);
	}
	
	public void insertManage(Product product) throws Exception{
		productDAO.insertManage(product);
	}

}
