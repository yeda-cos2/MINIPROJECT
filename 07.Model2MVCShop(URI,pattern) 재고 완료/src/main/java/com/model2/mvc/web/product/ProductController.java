package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	/// Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	// setter Method 구현 않음

	public ProductController() {
		System.out.println(this.getClass());
	}

	// ==> classpath:config/common.properties , classpath:config/commonservice.xml
	// 참조 할것
	// ==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

	//@RequestMapping("/addProductView.do")
	//public String addProductView() throws Exception {
	@RequestMapping( value="addProduct", method=RequestMethod.GET )
	public String addProduct() throws Exception {

		System.out.println("/product/addProduct : GET");

		return "redirect:/product/addProductView.jsp";
	}

	//@RequestMapping("/addProduct.do")
	@RequestMapping( value="addProduct", method=RequestMethod.POST )

	public String addProduct(@ModelAttribute("product") Product product,Model model) throws Exception {

		System.out.println("/product/addProduct : post");
		// Business Logic
		product.setManuDate(product.getManuDate().replace("-", ""));
		
		productService.addProduct(product);
		
		return "forward:/product/readProduct.jsp";
	}

	//@RequestMapping("/getProduct.do")
	@RequestMapping( value="getProduct")
	public String getProduct(@RequestParam("prodNo") int prodNo,@RequestParam("menu") String menu, Model model) throws Exception {

		System.out.println("/product/getProduct : post / get");
		// Business Logic
		Product product = productService.getProduct(prodNo);
		
		// Model 과 View 연결
		model.addAttribute("product", product);

		return "forward:/product/getProduct.jsp?menu="+menu;
	}

	//@RequestMapping("/updateProductView.do")
	@RequestMapping( value="updateProduct", method=RequestMethod.GET )
	public String updateProduct(@RequestParam("prodNo") int prodNo, Model model) throws Exception {

		System.out.println("/product/updateProduct :  get");
		// Business Logic
		Product product = productService.getProduct(prodNo);

		// Model 과 View 연결
		model.addAttribute("product", product);

		return "forward:/product/updateProductView.jsp";
	}

	//@RequestMapping("/updateProduct.do")
	@RequestMapping( value="updateProduct", method=RequestMethod.POST )
	public String updateProduct(@ModelAttribute("product") Product product, Model model, HttpSession session)
			throws Exception {

		System.out.println("/product/updateProduct :  POST");
		// Business Logic
		product.setManuDate(product.getManuDate().replace("-", ""));

		productService.updateProduct(product);

		return "forward:/product/getProduct.jsp?prodNo=" + product.getProdNo();
	}

	//@RequestMapping("/listProduct.do")
	@RequestMapping( value="listProduct" )
	public String listProduct(@ModelAttribute("search") Search search
			,@RequestParam("menu") String menu, Model model, HttpServletRequest request)
			throws Exception {

		
		System.out.println("/product/listProduct :  POST/get");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		System.out.println("sort"+search.getSortCondition());
		if(search.getSortCondition()==null) {
			search.setSortCondition("3");
		}
		search.setSortCondition(search.getSortCondition().replace(",", ""));
		System.out.println("뭐냐고요"+search.getSortCondition());
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String, Object> map = productService.getProductList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		System.out.println("머냐고"+map.get("list"));
		model.addAttribute("resultPage", resultPage);
		System.out.println("resultPage는"+resultPage);
		model.addAttribute("search", search);
		model.addAttribute("user",user);
		System.out.println("user::::"+user);

		return "forward:/product/listProduct.jsp?menu="+menu;
	}
}
