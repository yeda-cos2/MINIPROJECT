package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;

public class ProductDAO {

	public ProductDAO() {
		// TODO Auto-generated constructor stub
	}

	public void insertProduct(Product product) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "INSERT INTO PRODUCT VALUES (seq_product_prod_no.nextval,?,?,?,?,?,SYSDATE)";
		// sql 키워드: 대문자, identifier: 소문자
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, (product.getManuDate()));
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getFileName());
		stmt.executeUpdate();

		stmt.close(); // close
		con.close();
	}
	
	public Map<String, Object> getProductList(Search search) throws Exception {
	//hashmap대신 map으로 인터페이스 코딩
	Map<String , Object>  map = new HashMap<String, Object>();

	System.out.println("DAO시작");
	
	Connection con = DBUtil.getConnection();

	String sql = "select "
			+ "p.PROD_NO,p.PROD_NAME,p.PROD_DETAIL,p.MANUFACTURE_DAY,p.PRICE,p.IMAGE_FILE,p.REG_DATE,t.TRAN_STATUS_CODE "
			+ "from transaction t, product p where t.prod_no(+)=p.prod_no";
	
	System.out.println("DAO searchCondition"+search.getSearchCondition() +"searchKeyword"+ search.getSearchKeyword()+"sortCondition"+search.getSortCondition());

	if (search.getSearchCondition() != null) {
		if (search.getSearchCondition().equals("0")&&!search.getSearchCondition().equals("")) {
			sql += " and p.PROD_NO like '%" + search.getSearchKeyword() + "%'";
		} else if (search.getSearchCondition().equals("1")) {
			sql += " and p.PROD_NAME like '%" + search.getSearchKeyword() + "%'";
		} else if (search.getSearchCondition().equals("2")&&!search.getSearchCondition().equals("")) {
			sql += " and p.PRICE like'%" + search.getSearchKeyword() + "%'";
		}
	}
	
	if(search.getSortCondition()!=null) {
		if(search.getSortCondition().equals("0")&&!search.getSortCondition().equals("")) {
			sql += " ORDER BY p.price ASC";
		}else if(search.getSortCondition().equals("1")) {
			sql += " ORDER BY p.price DESC";

		}else if(search.getSortCondition().equals("2")) {
			sql += " ORDER BY p.reg_date DESC";
		}
	}else {
		sql += " ORDER BY p.prod_no";

	}
	
	System.out.println("UserDAO::Original SQL :: " +sql);

	//==> TotalCount GET
	int totalCount = this.getTotalCount(sql);
	System.out.println("UserDAO :: totalCount  :: " + totalCount);
	

	//==> CurrentPage 게시물만 받도록 Query 다시구성
	sql = makeCurrentPageSql(sql, search);
	PreparedStatement pStmt = con.prepareStatement(sql);
	ResultSet rs = pStmt.executeQuery();
					
	//HashMap<String, Object> map = new HashMap<String, Object>();
	//HashMap: 구체적 코딩=>list사용
	
	List<Product> list = new ArrayList<Product>();
	while(rs.next()){
		Product product = new Product();
		product.setProdNo(rs.getInt("PROD_NO"));
		product.setProdName(rs.getString("PROD_NAME"));
		product.setProdDetail(rs.getString("PROD_DETAIL"));
		product.setManuDate(rs.getString("MANUFACTURE_DAY"));
		product.setPrice(rs.getInt("PRICE"));
		product.setFileName(rs.getString("IMAGE_FILE"));
		product.setRegDate(rs.getDate("REG_DATE"));
		product.setProTranCode(rs.getString("TRAN_STATUS_CODE"));
		
		System.out.println("DAO VO확인:" + product);
		list.add(product);
		
	}
	
	//==> totalCount 정보 저장
	map.put("totalCount", new Integer(totalCount));

	//==> currentPage 의 게시물 정보 갖는 List 저장
	map.put("list", list);

	rs.close();
	pStmt.close();
	con.close();
	
	return map;
}

	public Product findProduct(int prodNo) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "select "
				+ "p.PROD_NO,p.PROD_NAME,p.PROD_DETAIL,p.MANUFACTURE_DAY,p.PRICE,p.IMAGE_FILE,p.REG_DATE,t.TRAN_STATUS_CODE "
				+ "from transaction t, product p where t.prod_no(+)=p.prod_no and p.prod_no=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();

		Product product = null;
		while (rs.next()) {
			product = new Product();
			product.setProdNo(rs.getInt("prod_no"));
			product.setProdName(rs.getString("prod_name"));
			product.setProdDetail(rs.getString("prod_detail"));
			product.setManuDate(rs.getString("manufacture_day"));
			product.setPrice(rs.getInt("price"));
			product.setFileName(rs.getString("image_file"));
			product.setRegDate(rs.getDate("reg_date"));
			product.setProTranCode(rs.getString("TRAN_STATUS_CODE"));
			System.out.println("DAO 확인:" + product);
			return product;
		}
		rs.close();
		stmt.close();
		con.close(); //셋 다 close

		return product;
	}



	public void updateProduct(Product product) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "UPDATE product SET prod_name=?,prod_detail=?,manufacture_day=?,image_file=?,price=? WHERE prod_no=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManuDate());
		stmt.setString(4, product.getFileName());
		stmt.setInt(5, product.getPrice());
		stmt.setInt(6, product.getProdNo());

		stmt.executeUpdate();
		
		stmt.close();
		con.close();
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	private int getTotalCount(String sql) throws Exception {
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
		private String makeCurrentPageSql(String sql , Search search){
			System.out.println(search.getCurrentPage()+" "+search.getPageSize());
			sql = 	"SELECT * "+ 
						"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
										" 	FROM (	"+sql+" ) inner_table "+
										"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
						"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
			
			System.out.println("ProductDAO :: make SQL :: "+ sql);	
			
			return sql;
		}
	}