<%@page import="com.taiwan.service.customer.CustomerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>忘記密碼</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/company/css/login.css">
<%@ include file="/common/head.jsp"%>
<style>
span.errorMsg {
	color: red;
	font-size: 16px;
}

span#errorMsg {
	color: orange;
	font-size: 16px;
}
</style>



</head>
<body>
 <div class="form" style="margin-top:80px;">
		<div class="form-toggle"></div>

		<div class="form-panel one" style="padding:50px calc(5% + 60px) 50px 60px">

			<div class="form-header">
				<h1>忘記密碼</h1>
			</div>

			<div class="form-content">
				<form method="post" action="cust/CustomerLogin">
					
					<div class="form-group">
						<label for="account">帳號</label>
						<input type="text" placeholder="輸入帳號，台玩會寄送信件告知您您的密碼" id="account" name="account" required="required" value="${customer.account}"/>
					</div>
					
					<span class="errorMsg" style="">${customer.message}</span>
					
					<div class="form-group" style="margin-top:10px;">
						</label><a class="form-recovery" href="<%=request.getContextPath()%>/front-end/rejest/custmomer_reject.jsp" style="margin-left: 380px;">註冊會員🧸</a>
					</div>
					<div class="form-group">
						<button type="submit" id="login">取得密碼</button>
					</div>
					<input type="hidden" name="action" value="forgetPassword">
				</form>
			</div>

		</div>

	</div>

	<script src='<%=request.getContextPath() %>/front-end/company/js/jquery.min.js'></script>
	<script src="<%=request.getContextPath() %>/front-end/company/js/script.js"></script>
</body>
</html>