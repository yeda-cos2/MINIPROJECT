<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>판매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
	function fncGetList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listSale.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">판매 목록조회</td>
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
		<td colspan="11" >
			전체  ${resultPage.totalCount } 건수,	현재 ${resultPage.currentPage} 페이지
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품번호</td>
		<td class="ct_line02"></td>
		<%-- <td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>--%>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>
		<td class="ct_line02"></td>
		
		<td class="ct_list_b">구매회원</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<c:set var="i" value="0" />
	<c:forEach var="purchase" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		<tr class="ct_list_pop">
			<td align="center">${ i }</td>
			
			<td></td>
			<td align="left"><a href="/getProduct.do?prodNo=${purchase.purchaseProd.prodNo }&menu=${param.menu}">${purchase.purchaseProd.prodNo }</a></td>
			<td></td>
			
			<%-- <td align="left">${purchase.purchaseProd.prodName }</td>
			<td></td>--%>
			
			<c:choose>
				<c:when test="${purchase.tranCode.equals('100')}">
					<td align="left">현재 구매완료 상태입니다.
					</td>
				</c:when>
				<c:when test="${purchase.tranCode.equals('200')}">
					<td align="left">현재 배송중 상태입니다.</td>
				</c:when>
				<c:otherwise>
					<td align="left">현재 배송완료 상태입니다.</td>
				</c:otherwise>
			</c:choose>
			
			
			
			<td></td>
			<td align="left">
			
			<c:if test="${user.role=='admin' }">
			<c:choose>
			<c:when test="${purchase.tranCode=='100'}">
			구매완료 
			<a href="/updateTranCode.do?menu=search&tranCode=${purchase.tranCode }&tranNo=${purchase.tranNo}">배송하기</a>
			</c:when>
			<c:when test="${purchase.tranCode=='200'}">
			배송중
			</c:when>
			<c:when test="${purchase.tranCode=='300'}">
			배송완료
			</c:when>
			<c:otherwise>
			판매중
			</c:otherwise>
			</c:choose>
			</c:if>
			
			
			
			
			
			
			<td></td>
			<td align="left">
				<a href="/getUser.do?userId=${purchase.buyer.userId}">${purchase.buyer.userId}</a>
			
		</tr>
		<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	</c:forEach>	
	
</table>

<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
			
			<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!-- PageNavigation End... -->

</form>
</div>

</body>
</html>