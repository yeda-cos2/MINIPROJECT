package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdatePurchaseViewAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		System.out.println("UpdatePurchaseViewAction::시작 prodNo="+request.getParameter("prodNo"));

		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		
		PurchaseService service=new PurchaseServiceImpl();
		Purchase purchase=service.getPurchase(prodNo);

		System.out.println("purchaseVO 확인:"+purchase);
		
		request.setAttribute("purchase", purchase);
		return "forward:/purchase/updatePurchaseView.jsp";
	}
}
