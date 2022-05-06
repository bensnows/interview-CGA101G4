<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>tktOrder_index</title>
<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}

#page-wrapper {
	background-color: rgb(221, 221, 241) ;
	height: 800px;
}

input[type="submit"] {
	box-shadow: inset 0px 0px 11px 0px #bbdaf7;
	background: linear-gradient(to bottom, #79bbff 5%, #378de5 100%);
	background-color: #79bbff;
	border-radius: 20px;
	display: inline-block;
	cursor: pointer;
	color: black;
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
	font-style: italic;
	padding: 12px 35px;
	text-decoration: none;
	text-shadow: 5px 7px 0px #528ecc;
	border: none;
}

input[type="submit"]:hover {
	background: linear-gradient(to bottom, #378de5 5%, #79bbff 100%);
	background-color: #378de5;
}

input[type="submit"]:active {
	position: relative;
	top: 1px;
}

b {
	font-size: 25px;
	vertical-align: middle;
}

.container-fluid>div {
	margin-bottom: 10px;
}

select[name="roomOrderStatus"] {
	height: 25px;
	border: 1px solid black;
}
</style>
</head>
<body>
	<div id="page-wrapper">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">票券訂單管理首頁</h1>
                    </div>
                </div>
                <div id="findAll_div">
                    <form action="tktOrder/findAll" method="post">
                        <input type="submit" value="搜尋全部">
                    </form>
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
                <div>
                    <FORM METHOD="post" ACTION="tktOrder/findById">
                        <b>輸入票券訂單編號來做查詢:</b>
                        <input type="text" name="tktOrderId">
                        <input type="submit" value="送出">
                    </FORM>
                </div>

                <div>
                    <FORM METHOD="post" ACTION="tktOrder/findByCustId">
                        <b>輸入會員編號來做查詢:</b>
                        <input type="text" name="custId">
                        <input type="submit" value="送出">
                    </FORM>
                </div>


                <div>
                    <FORM METHOD="post" ACTION="tktOrder/findByDate">
                        <b>根據日期來做查詢來做查詢:</b> <br>
                        <label for="from">From</label>
						<input type="text"  name="startdate" id="start_date">
						<label for="to">to</label>
						<input type="text"  name="enddate" id="end_date">
                        <input type="submit" value="送出">
                    </FORM>
                </div>

            </div>
        </div>

	




	<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->

<script src="datetimepicker/jquery.datetimepicker.full.js"></script>

<script type="text/javascript">
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#start_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_date').val()?$('#end_date').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#end_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_date').val()?$('#start_date').val():false
	   })
	  },
	  timepicker:false
	 });
});

</script>




</body>
</html>