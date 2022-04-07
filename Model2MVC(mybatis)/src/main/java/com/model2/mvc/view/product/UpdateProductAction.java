package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("UpdateProductAction:: 시작 menu="+request.getParameter("menu")+"prodNo="+request.getParameter("prodNo"));
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		Product productVO = new Product();
		
		System.out.println("UpdateProductAction:: null아니겠지 "+request.getParameter("prodName"));
		productVO.setProdNo(prodNo);
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate").replace("-", ""));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));

		ProductService service = new ProductServiceImpl();
		service.updateProduct(productVO);

		return "redirect:/getProduct.do?prodNo=" + prodNo+"&menu="+request.getParameter("menu");
	}
}