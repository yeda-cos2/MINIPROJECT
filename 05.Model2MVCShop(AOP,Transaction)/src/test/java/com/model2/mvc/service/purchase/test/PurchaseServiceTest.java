package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;


/*
 *	FileName :  PurchaseServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		
		Product product=new Product();
		product.setProdNo(10001);
		purchase.setPurchaseProd(product);
		System.out.println(product);
		User user=new User();
		user.setUserId("user44");
		purchase.setBuyer(user);
		System.out.println(purchase.getBuyer());
		purchase.setPaymentOption("1");
		purchase.setReceiverName("문예다");
		purchase.setReceiverPhone("010-222-2222");
		purchase.setDivyAddr("수원");
		purchase.setDivyRequest("빨리주쇼");
		purchase.setTranCode("100");
		purchase.setDivyDate("2022-04-30");
		
		purchaseService.addPurchase(purchase);

	}
	
	//@Test
	public void testGetPurchase() throws Exception {
		Purchase purchase = purchaseService.getPurchase(10019);

		//==> console 확인
		System.out.println(purchase);
		
		//==> API 확인
		Assert.assertEquals("수원", purchase.getDivyAddr());
//		Assert.assertEquals("1", purchase.getPaymentOption());
		Assert.assertEquals("문예다", purchase.getReceiverName());
		Assert.assertEquals("010-222-2222", purchase.getReceiverPhone());
		Assert.assertEquals("100", purchase.getTranCode());
		Assert.assertEquals("빨리주쇼", purchase.getDivyRequest());

		Assert.assertNotNull(purchaseService.getPurchase(10010));
//		Assert.assertNotNull(purchaseService.getPurchase(10000));
	}
	
	//@Test
	 public void testUpdatePurchase() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10019);
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals("수원", purchase.getDivyAddr());
		Assert.assertEquals("문예다", purchase.getReceiverName());
		Assert.assertEquals("010-222-2222", purchase.getReceiverPhone());
		Assert.assertEquals("100", purchase.getTranCode());
		Assert.assertEquals("빨리주쇼", purchase.getDivyRequest());

		purchase.setReceiverName("이상민");
		purchase.setReceiverPhone("000-111-222");
		purchase.setDivyAddr("비트캠프");
		purchase.setDivyRequest("아직안옴");
		purchase.setDivyDate("2022-04-11");
		
		purchaseService.updatePurchase(purchase);
		
		Purchase rpurchase = purchaseService.getPurchase(10019);
		Assert.assertNotNull(purchase);
		
		//==> console 확인
		System.out.println(purchase);
			
		//==> API 확인
		Assert.assertEquals("비트캠프", purchase.getDivyAddr());
		Assert.assertEquals("이상민", purchase.getReceiverName());
		Assert.assertEquals("000-111-222", purchase.getReceiverPhone());
		Assert.assertEquals("100", purchase.getTranCode());
		Assert.assertEquals("아직안옴", purchase.getDivyRequest());
	 }
	 
	
	 //==>  주석을 풀고 실행하면....
	@Test
	 public void testGetPurchaseListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, "user09");
	 	System.out.println("안나오냐"+map);
	 	List<Object> list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
//	 	search.setCurrentPage(1);
//	 	search.setPageSize(3);
//	 	search.setSearchCondition("0");
//	 	search.setSearchKeyword("");
//	 	map = purchaseService.getPurchaseList(search,"user44");
//	 	
//	 	list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
//	 	System.out.println(list);
//	 	
//	 	totalCount = (Integer)map.get("totalCount");
//	 	System.out.println(totalCount);
	 }
	 
	
}