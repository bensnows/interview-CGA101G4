<%@page import="com.taiwan.beans.TktOrder"%>
<%@page import="com.taiwan.service.TktOrderService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taiwan.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	List<TktOrder> list = (List<TktOrder>)request.getAttribute("list");
	pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有訂單資料 - listAllOrder.jsp</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>所有訂單資料</h3>
		 <h4><a href="front-end/tktOrder/tktOrderIndex.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.key} : ${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>票券訂單編號</th>
		<th>會員編號</th>
		<th>原始金額</th>
		<th>訂購日期</th>
		<th>總金額</th>
		<th>會員優惠券編號</th>
		<th>詳情</th>
	</tr>
	<c:forEach var="tktOrder" items="${list}" >
		
		<tr>
			<td>${tktOrder.tktOrderId}</td>
			<td>${tktOrder.custId}</td>
			<td>${tktOrder.originalPrice}</td>
			<td><fmt:formatDate value="${tktOrder.orderdate}" pattern="yyyy-MM-dd HH:mm"/></td>
			<td>${tktOrder.ttlPrice}</td>
			<td>${tktOrder.custCopId}</td>
<%-- 		<td>${empVO.deptno}-[${empVO.deptVO.dname}]</td> --%>
<td>
		  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tktItem/selectById" style="margin-bottom: 0px;">
			     <input type="submit" value="查詢明細">
			     <input type="hidden" name="tktOrderId" value="${tktOrder.tktOrderId}">
			     <input type="hidden" name="action"	value="get_orderItem">
		    </FORM>
		</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>