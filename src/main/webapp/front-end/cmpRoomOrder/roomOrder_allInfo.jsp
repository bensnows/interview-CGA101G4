<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#page-wrapper {
	background-color: rgb(221, 221, 241) !important;
	height: 900px;
}

span {
	font-size: 18px;
}

#back_index {
	position: fixed;
	right: 10%;
	bottom: 10%;
}

#back_index a {
	font-size: 20px;
	color: blue;
}

#back_index a:hover {
	color: red;
	text-decoration: none;
}

#pre {
	margin-left: 50%;
}
</style>

</head>

<div id="page-wrapper">
	<div class="container-fluid">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">訂單詳情</h1>
			</div>
		</div>
		<div>
			<div>
				<span>訂單編號 :</span> <span>${roomOrder.roomOrderId}</span>
			</div>
			<div>
				<span>會員編號 :</span> <span>${roomOrder.custId}</span>
			</div>
			<div>
				<span>會員姓名 :</span> <span>${roomOrder.customer.name}</span>
			</div>
			<div>
				<span>廠商編號 :</span> <span>${roomOrder.cmpId}</span>
			</div>
			<div>
				<span>訂單成立日期 :</span> <span><fmt:formatDate
						value="${roomOrder.roomOrderDate}" pattern="yyyy-MM-dd " /></span>
			</div>
			<div>
				<span>入住日期 :</span> <span><fmt:formatDate
						value="${roomOrder.checkinDate}" pattern="yyyy-MM-dd " /></span>
			</div>
			<div>
				<span>退房日期 :</span> <span><fmt:formatDate
						value="${roomOrder.checkoutDate}" pattern="yyyy-MM-dd " /></span>
			</div>
			<div>
				<span>訂單狀態 :</span> <span>${roomOrder.roomOrderStatus}</span>
			</div>
			<div>
				<span>取消原因 :</span> 
				<textarea rows="6" cols="40" name="canx" id="canx" readonly="readonly" style="resize: none;">${roomOrder.cancel}</textarea><br>
			</div>
			<div>
				<span>原始金額 :</span> <span>${roomOrder.roomOrderPrice}</span>
			</div>
			<div>
				<span>總金額 :</span> <span>${roomOrder.totalPrice}</span>
			</div>
			<div>
				<span>會員優惠券編號 :</span> <span>${(roomOrder.custCopId== null)? "未使用優惠券" : roomOrder.custCopId}</span>
			</div>
			==========================================================================
			<div>
				<div>
					<span>房型編號 :</span> <span>${roomItemVO.roomId}</span>
				</div>
				<div>
					<span>房型名稱 :</span> <span>${roomtype.roomtypeName}</span>
				</div>
				<div>
					<span>訂購數量 :</span> <span>${roomItemVO.roomItemAmount}</span>
				</div>
				<div>
					<span>評價分數 :</span> <span>${roomItemVO.roomItemEvaluateScore}</span>
				</div>
				<div>
					<span>評價內文 :</span>
					<textarea rows="6" cols="40" name="canx" id="canx" readonly="readonly" style="resize: none;">${roomItemVO.roomItemEvaluateMsg}</textarea><br>	
				</div>
			</div>
		</div>

		<button id="pre">上一頁</button>
	</div>
</div>
<script type="text/javascript">
		   const button1=document.querySelector('#pre');
		   button1.addEventListener('click',e=>{history.back();});
	</script>
<body>

</body>
</html>