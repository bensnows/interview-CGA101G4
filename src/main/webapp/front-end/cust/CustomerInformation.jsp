<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/head.jsp"%>
<style type="text/css">
table {
	margin: 20px auto; //
	border-collapse: collapse;
}

table tr {
	background: repeating-linear-gradient(0, #c0c000 0%, #cfcf00 1%, #f0f000 2%, #f9f900
		3%, #ff0 4%, #ff3 5%, #ffc 6%, #ffc 100%); //
	border-bottom: 1px solid black;
}

table th {
	padding: 15px;
}

table td {
	display: inline-block;
	padding: 10px;
}
</style>
</head>
<body>
	<div
		style="display: inline-block; border: 2px solid blue; vertical-align: middle">
		<form method="post" action="cust/CustomerInformation">
			<input type="submit" value="會員基本資料"> <input type="hidden"
				name="customer" value="${customer.custId}">
		</form>
		<form method="post" action="cust/ShowFollow">
			<input type="submit" value="關注廠商"> <input type="hidden"
				name="customer" value="${customer.custId}">
		</form>
		<form method="post" action="cust/ShowCustCoupon">
			<input type="submit" value="管理優惠劵">
		</form>

		<form method="post" action="cust/showRepCmp">
			<input type="submit" value="瀏覽檢舉"> <input type="hidden"
				name="customer" value="${customer.custId}">
		</form>
		<form method="post" action="cust/ShowEmail">
			<input type="submit" value="訊息管理">
		</form>
		<form method="post">
			<input type="submit" value="瀏覽訂單">
		</form>
	</div>
	<div style="display: inline-block; vertical-align: middle">
		<form method="post" action="cust/UpdateCustomerInformation">
			<input type="submit" value="修改基本資料"> <input type="hidden"
				name="customer" value="${customer.custId}">
		</form>
		<table>
			<tr>
				<th>姓名</th>
				<td>${customer.name}</td>
			</tr>
			<tr>
				<th>性別</th>
				<td>${(customer.sex=="f")?'女':'男'}</td>
			</tr>
			<tr>
				<th>電話</th>
				<td>${customer.tel}</td>
			</tr>
			<tr>
				<th>電子信箱</th>
				<td>${customer.email}</td>
			</tr>
			<tr>
				<th>聯絡地址</th>
				<td>${(customer.address=="")?'無聯絡地址':customer.address}</td>
			</tr>
			<tr>
				<th>身分證字號</th>
				<td>${customer.idCard}</td>
			</tr>
			<tr>
				<th>生日</th>
				<td>${customer.birth}</td>
			</tr>
			<tr>
				<th>帳號</th>
				<td>${customer.account}</td>
			</tr>
			<tr>
				<th>密碼</th>
				<td>${customer.password}</td>
			</tr>
			<tr>
				<th>圖片</th>
				<td style="height: 80px"><img style="height: 100%;"
					src="${customer.img}"></td>
			</tr>
			<tr>
				<th>信用卡卡號</th>
				<td>${(customer.card=="")?'您尚未輸入信用卡卡號':customer.card}</td>
			</tr>
		</table>
	</div>
</body>
</html>