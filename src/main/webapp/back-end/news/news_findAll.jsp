<%@page import="com.taiwan.beans.News"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
List<News> list = (List<News>) request.getAttribute("list");
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<meta charset="UTF-8">
<title>所有最新消息資料 newsFindAll</title>
<style>
#page-wrapper {
	background-color: rgb(221, 221, 241) !important;
	/*     height: 600px;   */
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
					<h1 class="page-header">所有最新消息</h1>
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
					<th>最新消息編號</th>
					<th>最新消息標題</th>
					<th>最新消息介紹</th>
					<th>撰寫日期</th>
					<th>圖片</th>
					<th>修改</th>
					<th>刪除</th>
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach items="${list}" var="news" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${news.newsId}</td>
						<td>${news.title}</td>
						<td>${news.content}</td>
						<td><fmt:formatDate value="${news.createDate}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td><img src="${news.img}" /></td>
						<td>
							<FORM METHOD="post" ACTION="news/news2Update" style="margin-bottom: 0px;">
								<input type="submit" value="修改"> 
								<input type="hidden" name="newsId" value="${news.newsId}"> 
								<input type="hidden" name="whichPage" value="<%=whichPage%>">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post" ACTION="news/newsDelete" style="margin-bottom: 0px;">
								<input type="submit" value="刪除"> 
								<input type="hidden" name="newsId" value="${news.newsId}"> 
								<input type="hidden" name="whichPage" value="<%=whichPage%>">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2.file"%>

			<div>
				<a href='back-end/news/news_index.jsp'>回最新消息首頁</a>
			</div>



		</div>
	</div>


	
</body>
</html>