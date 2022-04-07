package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddPurchaseViewAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		System.out.println("AddPurchaseViewAction.java::시작 prodNo=" + request.getParameter("prodNo"));
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));

		ProductService service = new ProductServiceImpl();
		Product product = service.getProduct(prodNo);
		System.out.println("AddPurchaseViewAction::productVO 저장" + product);

		request.setAttribute("product", product);		
		

		return "forward:/purchase/addPurchaseView.jsp";
	}
}