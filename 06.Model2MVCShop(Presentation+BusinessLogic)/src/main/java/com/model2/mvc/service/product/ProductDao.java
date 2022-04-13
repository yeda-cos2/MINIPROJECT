package com.model2.mvc.service.product;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

//==> 회원관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
public interface ProductDao {

	// INSERT
	public void addProduct(Product product) throws Exception;

	public List<Product> getProductList(Search search) throws Exception;

	public Product getProduct(int prodNo) throws Exception;

	public void updateProduct(Product product) throws Exception;

	public int getTotalCount(Search search) throws Exception ;

}