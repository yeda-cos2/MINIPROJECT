package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListSaleAction extends Action{

	
		@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			System.out.println("ListSaleAction::시작 menu="+request.getParameter("menu"));

			Search search = new Search();

			int currentPage = 1;

			if (request.getParameter("currentPage") != null && !(request.getParameter("currentPage")).equals("")) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}

			// web.xml meta-data 로 부터 상수 추출
			int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
			int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
			
			search.setPageSize(pageSize);
			search.setCurrentPage(currentPage);
			System.out.println("ListSaleAction:: currentPage="+currentPage);
			search.setSearchCondition(request.getParameter("searchCondition"));
			search.setSearchKeyword(request.getParameter("searchKeyword"));
			
			 HttpSession session = request.getSession(true);
			 User user=(User)session.getAttribute("user");
			System.out.println("ListSaleAction:: userId="+user.getUserId());
			
			// Business logic 수행
			PurchaseService service = new PurchaseServiceImpl();
			Map<String, Object> map = service.getPurchaseList(search,user.getUserId());

			Page resultPage = new Page(currentPage, ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
			System.out.println("ListPurchaseAction ::" + resultPage);

			// Model 과 View 연결
			request.setAttribute("list", map.get("list"));
			request.setAttribute("resultPage", resultPage);
			request.setAttribute("search", search);

			System.out.println("List.java map : " + map);

			return "forward:/purchase/listSale.jsp";
		}
	}


