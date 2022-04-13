package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class AddProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		System.out.println("AddProductAction:: ����");
		Product product=new Product();
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));
		product.setRemain(Integer.parseInt(request.getParameter("remain")));
		System.out.println("AddProductAction::productVO ����= "+product);
		
		ProductService service=new ProductServiceImpl();
		service.addProduct(product);
		service.insertManage(product);
		request.setAttribute("product", product);
		
		return "forward:/product/readProduct.jsp";
	}
}