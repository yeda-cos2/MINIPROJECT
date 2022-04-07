package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListProductAction:: 시작 menu=" + request.getParameter("menu"));
		Search search = new Search();

		int currentPage = 1;

		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
//		String sortCondition=prodNo;
//		if(request.getParameter("sortCondition")!=null) {
//			sortCondition=request.getParameter("sortCondition");
//		}

		// web.xml meta-data 로 부터 상수 추출
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		
		search.setPageSize(pageSize);
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		System.out.println("ListProductAction:: sortCondition="+request.getParameter("sortCondition"));
		search.setSortCondition(request.getParameter("sortCondition"));
//		search.setSortCondition(sortCondition);
		
		// Business logic 수행
		ProductService service = new ProductServiceImpl();
		Map<String, Object> map = service.getProductList(search);

		Page resultPage = new Page(currentPage, ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListProductAction :: currentPage=" + resultPage);
		System.out.println("ListProductAction :: resultPage=" + resultPage);

		// Model 과 View 연결
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);

		System.out.println("ListProductAction:: map=" + map);

		return "forward:/product/listProduct.jsp";
	}
}