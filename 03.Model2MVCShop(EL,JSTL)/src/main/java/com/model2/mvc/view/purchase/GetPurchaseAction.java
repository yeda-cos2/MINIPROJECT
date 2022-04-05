package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class GetPurchaseAction extends Action {
	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		System.out.println("GetPurchaseAction::시작 prodNo="+request.getParameter("prodNo"));

		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
				
		PurchaseService service=new PurchaseServiceImpl();
		Purchase purchase=service.getPurchase(prodNo);

		System.out.println("GetPurchaseAction:: purchaseVO 저장:"+purchase);

		request.setAttribute("purchase", purchase);

		return "forward:/purchase/getPurchase.jsp?menu="+request.getParameter("menu");
	}
}
