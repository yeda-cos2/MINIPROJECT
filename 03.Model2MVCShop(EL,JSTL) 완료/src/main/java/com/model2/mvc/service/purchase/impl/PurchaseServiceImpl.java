package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;

public class PurchaseServiceImpl implements PurchaseService {

	// Field
	private PurchaseDAO purchaseDAO;

	// Constructor
	public PurchaseServiceImpl() {
		purchaseDAO = new PurchaseDAO();
	}

	// Method
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDAO.insertPurchase(purchase);
	}

	public Map<String, Object> getPurchaseList(Search search,String buyerId) throws Exception {
		return purchaseDAO.getPurchaseList(search,buyerId);
	}

	public Purchase getPurchase(int prodNo) throws Exception {
		return purchaseDAO.findPurchase(prodNo);
	}
	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDAO.updatePurchase(purchase);
	}
	
	public void updateTranCode(Purchase purchase) throws Exception{
		purchaseDAO.updateTranCode(purchase);
	}

}

