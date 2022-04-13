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

public class AddPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		
		System.out.println("AddPurchaseAction::시작");
		System.out.println("menu="+request.getParameter("menu")+"prodNo="+request.getParameter("prodNo")+"buyerId"+request.getParameter("buyerId"));
	
		int prodNo=Integer.parseInt(request.getParameter("prodNo")) ;
		Product product=new Product();
		product.setProdNo(prodNo);
		
		Purchase purchase=new Purchase();
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("receiverDate"));	
		purchase.setCount(Integer.parseInt(request.getParameter("count")));
		purchase.setTranCode("100");
		
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		purchase.setBuyer(user);
		
		System.out.println("AddPurchaseAction::purchaseVO 저장: "+purchase);
		purchase.setPurchaseProd(product);
		
		PurchaseService service=new PurchaseServiceImpl();
		service.addPurchase(purchase);
		request.setAttribute("purchase", purchase);

		return "forward:/purchase/readPurchase.jsp";
	}
}