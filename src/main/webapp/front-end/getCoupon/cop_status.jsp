<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>所有優惠券 </title>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
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

	<h1>${couponVO.status}優惠券</h1>

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
		
		
			
		</tr>
		<c:forEach items="${requestScope.list}" var="couponVO">
			<tr>
				<td>${couponVO.copId}</td>
				<td>${couponVO.copName}</td>
				<td>${couponVO.introduce}</td>
				<td>${couponVO.discount}</td>
				<td><fmt:formatDate value="${couponVO.startdate}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td><fmt:formatDate value="${couponVO.enddate}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td><img src="${couponVO.img}" /></td>
			</tr>
		</c:forEach>
	</table>

	<div>
		<a href='front-end/getCoupon/getCouponSelect_page.jsp'>回到優惠券首頁</a>
	</div>

</body>
</html>