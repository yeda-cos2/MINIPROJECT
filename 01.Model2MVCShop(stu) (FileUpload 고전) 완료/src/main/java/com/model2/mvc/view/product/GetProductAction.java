package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class GetProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("Get.java시작, prodNo 넘겨받았나: "+prodNo);
		
		ProductService service=new ProductServiceImpl();
		ProductVO productVO=service.getProduct(prodNo);
		System.out.println("productVO 저장:"+productVO);
		
		request.setAttribute("productVO", productVO);
		System.out.println("menu: "+request.getParameter("menu"));

		return "forward:/product/getProduct.jsp?menu="+request.getParameter("menu");
	}
}