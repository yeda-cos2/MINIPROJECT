package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("Get.java시작, prodNo 넘겨받았나: " + prodNo);

		ProductService service = new ProductServiceImpl();
		Product product = service.getProduct(prodNo);
		System.out.println("productVO 저장:" + product);

		request.setAttribute("product", product);
		System.out.println("menu: " + request.getParameter("menu"));

		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			Cookie newCookie = new Cookie("history", prodNo + ",");
			response.addCookie(newCookie);

			System.out.println("NULL일 때 저장될 쿠키값" + newCookie.getValue());
		}

		String cv = "/" + prodNo;

		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];

				System.out.println("history 쿠키없음 : " + cookie.getName());

				Cookie newCookie = new Cookie("history", request.getParameter("prodNo"));
				response.addCookie(newCookie);

				if (cookie.getName().equals("history")) {

					String str = cookie.getValue() + cv;

					Cookie newCookie02 = new Cookie("history", str);
					response.addCookie(newCookie02);

					System.out.println("Not NULL일 때 저장된 쿠키값" + cookie.getValue());
					System.out.println("Not NULL일 때 저장될 쿠키값" + str);
				}
			}
		}

		return "forward:/product/getProduct.jsp?menu=" + request.getParameter("menu");
	}
}