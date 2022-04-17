<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>회원정보수정</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncUpdatePurchase() {

	
	document.detailForm.submit();
}


function resetData() {
	document.detailForm.reset();
}
-->
</script>
<script type="text/javascript" src="../javascript/calendar.js">
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<form name="detailForm"  method="post" action="/updatePurchase.do">

<input type="hidden" name="prodNo" value="${purchase.purchaseProd.prodNo}">

<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매정보수정</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			구매자아이디 
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">${purchase.buyer.userId}</td>
					<td>	</td>
				</tr>
			</table>
		</td>
	</tr>
	
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			구매방법 
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
		<c:if test="${purchase.paymentOption!='2'}">
		현금구매
		</c:if>
		<c:if test="${purchase.paymentOption=='2'}">
		신용구매
		</c:if>
		</td>
	</tr>
	
	
		<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			구매자이름
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="receiverName" value="${purchase.receiverName}" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="50" >
		</td>
	</tr>
	
		<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			구매자 연락처 
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="receiverPhone" value="${purchase.receiverPhone}" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="50" >
		</td>
	</tr>
	
		<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			구매자주소 
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
		
		<c:choose>
		<c:when test="${!(purchase.purchaseProd.proTranCode=='200'||purchase.purchaseProd.proTranCode=='300') }">
			<input type="text" name="divyAddr" value="${purchase.divyAddr}" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="50" >
						
						</c:when>
						
		<c:when test="${purchase.purchaseProd.proTranCode=='200' }">
						
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">${purchase.divyAddr}</td>
					<td>	</td>
				</tr>
			</table>
			</c:when>
			
		
			</c:choose>
		</td>
	</tr>
	

	
	
		<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			구매 요청사항 
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="divyRequest" value="${purchase.divyRequest}" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="50" >
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">배송희망일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td width="200" class="ct_write01">
			<input 	type="text"  name="receiverDate" class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20" value="${purchase.divyDate}"/>
			<img 	src="../images/ct_icon_date.gif" width="15" height="15"	
						onclick="show_calendar('document.detailForm.receiverDate', document.detailForm.receiverDate.value)"/>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncUpdatePurchase();">수정</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:resetData();">취소</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>

</body>
</html>
