<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>這是首頁</title>
	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
<!-- 	<a href="cmp_login/login.jsp">從首頁跳轉到廠商登陸頁面</a><br> -->
<!-- 	<a href="cmp_login/regist.jsp">從首頁跳轉到廠商註冊頁面</a><br> -->
<jsp:forward page="front-end/homepage/index.jsp"></jsp:forward>
</body>
</html>