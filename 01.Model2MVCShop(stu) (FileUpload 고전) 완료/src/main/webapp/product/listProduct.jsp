<%@ page language="java" contentType="text/html; charset=EUC-KR"%>

<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>
<%@ page import="com.model2.mvc.common.*" %>


<%
	HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");
	String menu=request.getParameter("menu");
	System.out.println("list.jsp 시작 map: "+map+"menu: "+request.getParameter("menu"));	
	
	
	int total=0;
	ArrayList<ProductVO> list=null;
	
	if(map != null){
		total=((Integer)map.get("count")).intValue();
		list=(ArrayList<ProductVO>)map.get("list");
	}
	
	int currentPage=searchVO.getPage();
	System.out.println("토탈"+total);
	
	int totalPage=0;
	int block=0;
	if(total > 0) {
		totalPage= total / searchVO.getPageUnit() ;
		if(total%searchVO.getPageUnit() >0)
			totalPage += 1;
		
		block=totalPage/10;
		if((total%10)!=0){
			block++;
		}
	}
	

%>

<html>
<head>

<title>상품조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetProductList(){
	document.detailForm.submit();
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					<% if(request.getParameter("menu").equals("manage")){%>
					상품 관리
					<% }else{ %>
					상품 목록조회
					<% }%>
	
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	<%
		System.out.println();
		if(searchVO.getSearchCondition() != null && !("null".equals(searchVO.getSearchCondition()))) {
	%>		
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
		<%
				System.out.println("뭔가요"+searchVO.getSearchCondition());
				if(searchVO.getSearchCondition().equals("0")){
		%>
				<option value="0" selected>상품번호</option>
				<option value="1">상품명</option>
				<option value="2">상품가격</option>
		<%
				}else if(searchVO.getSearchCondition().equals("1")) {
		%>
				<option value="0">상품번호</option>
				<option value="1" selected>상품명</option>
				<option value="2">상품가격</option>
				
		<%
				}else{
		%>
				<option value="0">상품번호</option>
				<option value="1">상품명</option>
				<option value="2" selected>상품가격</option>
				

		<% }%>
		
		
			</select>
			<input 	type="text" name="searchKeyword"  value="<%=searchVO.getSearchKeyword() %>"
							class="ct_input_g" style="width:200px; height:19px" >
		</td>
	
	<%
		}else{
	%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">상품번호</option>
				<option value="1">상품명</option>
				<option value="2">상품가격</option>
			</select>
			<input type="text" name="searchKeyword" class="ct_input_g" style="width:200px; height:19px" >
		</td>
	<%
		}
		System.out.println("여기도 널아니냐고"+searchVO.getSearchCondition());
	%>
	
	
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체  <%= total%> 건수, 현재 <%=currentPage %> 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<% 	
		int no=list.size();
		for(int i=0; i<list.size(); i++) {
			ProductVO productVO = (ProductVO)list.get(i);
	%>
	<tr class="ct_list_pop">
		<td align="center"><%=no--%></td>
	<td></td>
		<td align="left">
			
				<% if(menu.equals("manage")){%>
					<a href="/updateProductView.do?prodNo=<%=productVO.getProdNo() %>&menu=<%=menu %>">
					<% }else{ %>
					<a href="/getProduct.do?prodNo=<%=productVO.getProdNo() %>&menu=<%=menu %>
					">
					<% }%>
			
			<%= productVO.getProdName() %></a>
		</td>	
		
		<td></td>
		<td align="left"><%= productVO.getPrice() %>
		</td>
				
 	<td></td>
		<td align="left"><%= productVO.getManuDate() %>
		</td>		
		<td></td>
		<td align="left"><%= productVO.getProTranCode() %>
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<% } %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		
		<% for(int i=1;i<=totalPage;i++){%>
			<a href="/listProduct.do?page=<%=i%>&menu=<%=menu%>
			&searchCondition=<%=searchVO.getSearchCondition()%>
			&searchKeyword=<%=searchVO.getSearchKeyword()%>"><%=i %></a>
			
			<%}%>
			
			
		
		
			
			

		
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->
</form>
</div>

</body>
</html>