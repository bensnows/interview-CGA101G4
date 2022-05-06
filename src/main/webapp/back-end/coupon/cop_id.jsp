<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<meta charset="UTF-8">
<title>優惠券編號資料 CouponFindById</title>
<style>
#page-wrapper {
	background-color: rgb(221, 221, 241) !important;
	height: 800px; 
}

table {
	width: 1100px;
	background-color: rgb(221, 221, 241) !important;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 3px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

img {
	width: 150px;
	height: 150px;
}
</style>


</head>
<body>

	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">藉由編號搜尋優惠券</h1>
				</div>
			</div>
			<%--錯誤列表 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<table>
				<tr>
					<th>優惠券編號</th>
					<th>優惠券名稱</th>
					<th>優惠券介紹</th>
					<th>折扣</th>
					<th>開始日期</th>
					<th>結束日期</th>
					<th>圖片</th>
					<th>狀態</th>
					<th>修改</th>
					<th>刪除</th>
				</tr>
				<tr>
					<td>${couponVO.copId}</td>
					<td>${couponVO.copName}</td>
					<td>${couponVO.introduce}</td>
					<td>${couponVO.discount}</td>
					<td><fmt:formatDate value="${couponVO.startdate}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td><fmt:formatDate value="${couponVO.enddate}"	pattern="yyyy-MM-dd HH:mm" /></td>
					<td><img src="${couponVO.img}" /></td>
					<td>
						<FORM METHOD="post" ACTION="coupon/copStatusChange"	style="margin-bottom: 0px;">
							<input type="submit" name="status" value="${couponVO.status}" ${(couponVO.status == "已過期")? "disabled='disabled'":"" }>
							<input type="hidden" name="copId" value="${couponVO.copId}">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="coupon/cop2Update" style="margin-bottom: 0px;">
							<input type="submit" value="修改" ${(couponVO.status == "已過期")? "disabled='disabled'":"" }> 
							<input type="hidden"	name="copId" value="${couponVO.copId}">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="coupon/copDelete" style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> 
							<input type="hidden"	name="copId" value="${couponVO.copId}">
						</FORM>
					</td>
				</tr>
			</table>
			<div>
				<a href='back-end/coupon/cop_index.jsp'>回到優惠券首頁</a>
			</div>
		</div>
	</div>




</body>
</html>