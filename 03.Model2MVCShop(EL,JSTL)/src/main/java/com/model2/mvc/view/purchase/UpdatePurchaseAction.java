package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdatePurchaseAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("UpdatePurchaseAction::시작 prodNo="+request.getParameter("prodNo"));

		int prodNo=Integer.parseInt(request.getParameter("prodNo"));

		HttpSession httpSession = request.getSession(true);
		User user=(User)httpSession.getAttribute("user");
		
		Product product=new Product();
		product.setProdNo(prodNo);
		
		Purchase purchase=new Purchase();
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("divyAddr"));
		purchase.setDivyRequest(request.getParameter("divyRequest"));
		purchase.setDivyDate(request.getParameter("divyDate"));
		
		PurchaseService service = new PurchaseServiceImpl();
		service.updatePurchase(purchase);
		
		System.out.println("UpdatePurchaseAction:: purchaseVO 저장"+purchase);

		return "redirect:/getPurchase.do?prodNo="+prodNo;
	}
}