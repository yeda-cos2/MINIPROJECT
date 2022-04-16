package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductDAO {

	public ProductDAO() {
		// TODO Auto-generated constructor stub
	}

	public void insertProduct(ProductVO productVO) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "INSERT into PRODUCT values (seq_product_prod_no.nextval,?,?,?,?,?,SYSDATE)";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, (productVO.getManuDate()).replace("-", ""));
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.executeUpdate();

		con.close();
	}

	public ProductVO findProduct(int prodNo) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "select * from Product WHERE PROD_NO=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();

		ProductVO productVO = null;
		while (rs.next()) {
			productVO = new ProductVO();
			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
			System.out.println("dao확인:" + productVO);
			return productVO;
		}

		con.close();

		return productVO;
	}

	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception {
		System.out.println("DAO시작+searchCondition"+searchVO.getSearchCondition());
		Connection con = DBUtil.getConnection();

		String sql = "select * from Product ";
		System.out.println(searchVO.getSearchCondition()+searchVO.getSearchKeyword());
		
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where PROD_NO='" + searchVO.getSearchKeyword() + "'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " where PROD_NAME like '%" + searchVO.getSearchKeyword() + "%'";
			} else if(searchVO.getSearchCondition().equals("2")){
				sql += "where PRICE='" + searchVO.getSearchKeyword() + "'";
			}
		}
		sql += " order by PROD_NO";
		System.out.println(sql);
		
		PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO productVO = new ProductVO();
				productVO.setProdNo(rs.getInt("PROD_NO"));
				productVO.setProdName(rs.getString("PROD_NAME"));
				productVO.setProdDetail(rs.getString("PROD_DETAIL"));
				productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
				productVO.setPrice(rs.getInt("PRICE"));
				productVO.setFileName(rs.getString("IMAGE_FILE"));
				productVO.setRegDate(rs.getDate("REG_DATE"));
				productVO.setProTranCode("판매중");
				System.out.println("DAO VO확인:" + productVO);
				list.add(productVO);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : " + list.size());
		map.put("list", list);
		System.out.println("map().size() : " + map.size());

		con.close();

		return map;
	}

	public void updateProduct(ProductVO productVO) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "update product set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,IMAGE_FILE=?,PRICE=? where PROD_NO=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setString(4, productVO.getFileName());
		stmt.setInt(5, productVO.getPrice());
		stmt.setInt(6, productVO.getProdNo());

		stmt.executeUpdate();

		con.close();
	}
}