package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeAction extends Action{
	@Override
	public String execute(	HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		System.out.println("UpdateTranCodeAction::시작 tranCode="+request.getParameter("tranCode")+"menu="+request.getParameter("menu"));

		
		String tranCode=request.getParameter("tranCode");
		
		if(tranCode.equals("100")) {
			tranCode="200";
		}else if(tranCode.equals("200")) {
			tranCode="300";
		}
		Product product=new Product();
		product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		
		Purchase purchase=new Purchase();
		purchase.setTranCode(tranCode);
		purchase.setPurchaseProd(product);
		
		PurchaseService service=new PurchaseServiceImpl();
		service.updateTranCode(purchase);
	
		request.setAttribute("purchase", purchase);
		
		Search search = new Search();

		int currentPage = 1;

		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}


		// web.xml meta-data 로 부터 상수 추출
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		
		search.setPageSize(pageSize);
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		// Business logic 수행
		ProductService service1 = new ProductServiceImpl();
		Map<String, Object> map = service1.getProductList(search);

		Page resultPage = new Page(currentPage, ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);

		// Model 과 View 연결
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);

		System.out.println("마지막 Trancode="+tranCode);
		
		if(tranCode.equals("200")) {
		return "redirect:/listSale.do?menu="+request.getParameter("menu");
		}else {
		return "redirect:/listPurchase.do?menu="+request.getParameter("menu");
		}
		

	}
}
