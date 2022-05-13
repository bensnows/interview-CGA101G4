<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房型詳情</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>台玩旅遊平台 - 廠商修改頁面</title>

<link
	href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700i"
	rel="stylesheet">

<!-- Bootstrap Core CSS -->
<link href="common/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="common/css/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="common/css/startmin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="common/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<style>
input[type=text] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	box-sizing: border-box;
	border: 3px solid #ccc;
	-webkit-transition: 0.5s;
	transition: 0.5s;
	outline: none;
}

input[type=text]:focus {
	border: 3px solid #555;
}
</style>

</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.html">台玩 - 旅遊平台</a>
			</div>

<!-- 			<button type="button" class="navbar-toggle" data-toggle="collapse" -->
<!-- 				data-target=".navbar-collapse"> -->
<!-- 				<span class="sr-only">Toggle navigation</span> <span -->
<!-- 					class="icon-bar"></span> <span class="icon-bar"></span> <span -->
<!-- 					class="icon-bar"></span> -->
<!-- 			</button> -->

<!-- 			<ul class="nav navbar-nav navbar-left navbar-top-links"> -->
<!-- 				<li><a href="#"><i class="fa fa-home fa-fw"></i> Website</a></li> -->
<!-- 			</ul> -->

<!-- 			<ul class="nav navbar-right navbar-top-links"> -->
<!-- 				<li class="dropdown navbar-inverse"><a class="dropdown-toggle" -->
<!-- 					data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i> -->
<!-- 						<b class="caret"></b> -->
<!-- 				</a> -->
<!-- 					<ul class="dropdown-menu dropdown-alerts"> -->
<!-- 						<li><a href="#"> -->
<!-- 								<div> -->
<!-- 									<i class="fa fa-comment fa-fw"></i> New Comment <span -->
<!-- 										class="pull-right text-muted small">4 minutes ago</span> -->
<!-- 								</div> -->
<!-- 						</a></li> -->
<!-- 						<li><a href="#"> -->
<!-- 								<div> -->
<!-- 									<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span -->
<!-- 										class="pull-right text-muted small">12 minutes ago</span> -->
<!-- 								</div> -->
<!-- 						</a></li> -->
<!-- 						<li><a href="#"> -->
<!-- 								<div> -->
<!-- 									<i class="fa fa-envelope fa-fw"></i> Message Sent <span -->
<!-- 										class="pull-right text-muted small">4 minutes ago</span> -->
<!-- 								</div> -->
<!-- 						</a></li> -->
<!-- 						<li><a href="#"> -->
<!-- 								<div> -->
<!-- 									<i class="fa fa-tasks fa-fw"></i> New Task <span -->
<!-- 										class="pull-right text-muted small">4 minutes ago</span> -->
<!-- 								</div> -->
<!-- 						</a></li> -->
<!-- 						<li><a href="#"> -->
<!-- 								<div> -->
<!-- 									<i class="fa fa-upload fa-fw"></i> Server Rebooted <span -->
<!-- 										class="pull-right text-muted small">4 minutes ago</span> -->
<!-- 								</div> -->
<!-- 						</a></li> -->
<!-- 						<li class="divider"></li> -->
<!-- 						<li><a class="text-center" href="#"> <strong>See -->
<!-- 									All Alerts</strong> <i class="fa fa-angle-right"></i> -->
<!-- 						</a></li> -->
<!-- 					</ul></li> -->
<!-- 				<li class="dropdown"><a class="dropdown-toggle" -->
<!-- 					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i> -->
<!-- 						secondtruth <b class="caret"></b> -->
<!-- 				</a> -->
<!-- 					<ul class="dropdown-menu dropdown-user"> -->
<!-- 						<li><a href="#"><i class="fa fa-user fa-fw"></i> User -->
<!-- 								Profile</a></li> -->
<!-- 						<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a> -->
<!-- 						</li> -->
<!-- 						<li class="divider"></li> -->
<!-- 						<li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> -->
<!-- 								Logout</a></li> -->
<!-- 					</ul></li> -->
<!-- 			</ul> -->
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
					
						<li>
							<a href="company/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}&">
								<i class="fa fa-dashboard fa-fw"></i>
								廠商首頁
							</a>
						</li>					
					
						<li>
							<a href="roomtype/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i>
								房型管理
							</a>
						</li>
						
						<li>
							<a href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i>
								廠商資料
							</a>
						</li>
						
						<li>
							<a href="roomOrder/cmpFindOrder?cmpId=${sessionScope.loginCompany.cmpId}">
								<i class="fa fa-dashboard fa-fw"></i>
								訂單管理
							</a>
						</li>
					
						<li>
							<a href="javascript:history.go(-1)">
								<i class="fa fa-dashboard fa-fw"></i>
								返回列表
							</a>
						</li>
						
						<li>
							<a href="company/logout">
								<i class="fa fa-dashboard fa-fw"></i>
								登出
							</a>
						</li>
<!-- 						<li class="sidebar-search"> -->
<!-- 							<div class="input-group custom-search-form"> -->
<!-- 								<input type="text" class="form-control" placeholder="Search..."> -->
<!-- 								<span class="input-group-btn"> -->
<!-- 									<button class="btn btn-primary" type="button"> -->
<!-- 										<i class="fa fa-search"></i> -->
<!-- 									</button> -->
<!-- 								</span> -->
<!-- 							</div> /input-group -->
<!-- 						</li> -->
<!-- 						<li><a href="index.html"><i class="fa fa-dashboard fa-fw"></i> -->
<!-- 								Dashboard</a></li> -->
<!-- 						<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> -->
<!-- 								Charts<span class="fa arrow"></span></a> -->
<!-- 							<ul class="nav nav-second-level"> -->
<!-- 								<li><a href="flot.html">Flot Charts</a></li> -->
<!-- 								<li><a href="morris.html">Morris.js Charts</a></li> -->
<!-- 							</ul> /.nav-second-level</li> -->
<!-- 						<li><a href="tables.html"><i class="fa fa-table fa-fw"></i> -->
<!-- 								Tables</a></li> -->
<!-- 						<li><a href="forms.html"><i class="fa fa-edit fa-fw"></i> -->
<!-- 								Forms</a></li> -->
<!-- 						<li><a href="#"><i class="fa fa-wrench fa-fw"></i> UI -->
<!-- 								Elements<span class="fa arrow"></span></a> -->
<!-- 							<ul class="nav nav-second-level"> -->
<!-- 								<li><a href="panels-wells.html">Panels and Wells</a></li> -->
<!-- 								<li><a href="buttons.html">Buttons</a></li> -->
<!-- 								<li><a href="notifications.html">Notifications</a></li> -->
<!-- 								<li><a href="typography.html">Typography</a></li> -->
<!-- 								<li><a href="icons.html"> Icons</a></li> -->
<!-- 								<li><a href="grid.html">Grid</a></li> -->
<!-- 							</ul> /.nav-second-level</li> -->
<!-- 						<li><a href="#"><i class="fa fa-sitemap fa-fw"></i> -->
<!-- 								Multi-Level Dropdown<span class="fa arrow"></span></a> -->
<!-- 							<ul class="nav nav-second-level"> -->
<!-- 								<li><a href="#">Second Level Item</a></li> -->
<!-- 								<li><a href="#">Second Level Item</a></li> -->
<!-- 								<li><a href="#">Third Level <span class="fa arrow"></span></a> -->
<!-- 									<ul class="nav nav-third-level"> -->
<!-- 										<li><a href="#">Third Level Item</a></li> -->
<!-- 										<li><a href="#">Third Level Item</a></li> -->
<!-- 										<li><a href="#">Third Level Item</a></li> -->
<!-- 										<li><a href="#">Third Level Item</a></li> -->
<!-- 									</ul> /.nav-third-level</li> -->
<!-- 							</ul> /.nav-second-level</li> -->
<!-- 						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> -->
<!-- 								Sample Pages<span class="fa arrow"></span></a> -->
<!-- 							<ul class="nav nav-second-level"> -->
<!-- 								<li><a href="blank.html">Blank Page</a></li> -->
<!-- 								<li><a href="login.html">Login Page</a></li> -->
<!-- 							</ul> /.nav-second-level</li> -->
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">房間詳情頁面</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">

							<div class="panel-heading">房間資料</div>

							<div class="panel-heading">
								<c:forEach var="RoomImg" items="${requestScope.roomtype.roomImgs}">
        							<img src="${RoomImg.roomImg}" height="128px" width="128px" class="uploadedImg">
    							</c:forEach>
							</div>
								


								<div class="panel-body">
									<div class="row">
										<div class="col-lg-6">
											<form role="form">
												<div class="form-group">
													<label for="roomtypeName">房型名稱:</label> <input type="text"
														name="roomtypeName" id="roomtypeName"
														value="${requestScope.roomtype.roomtypeName}"><br>
												</div>

												<div class="form-group">
													<label for="roomtpyeAmount">房型數量:</label> <input
														type="text" name="roomtypeAmount" id="roomtypeAmount"
														value="${requestScope.roomtype.roomtypeAmount}"><br>
												</div>

												<div class="form-group">
													<label for="roomtypePeople">入住人數:</label> <input
														type="text" name="roomtypePeople" id="roomtypePeople"
														value="${requestScope.roomtype.roomtypePeople}"><br>
												</div>

												<div class="form-group">
													<label for="totalScope">總評分:</label> <input type="text"
														name="totalScore" id="totalScore"
														value="${requestScope.roomtype.totalScore}"><br>
												</div>

												<div class="form-group">
													<label for="totalPeople">評分總人數:</label> <input type="text"
														name="totalPeople" id="totalPeople"
														value="${requestScope.roomtype.totalPeople}"><br>
												</div>

												<div class="form-group">
													<label for="roomtypePrice">價格:</label> <input type="text"
														name="roomtypePrice" id="roomtypePrice"
														value="${requestScope.roomtype.roomtypePrice}"><br>
												</div>

												<div class="form-group">
													<label for="roomtypeStatus">狀態:</label> <input type="text"
														name="roomtypeStatus" id="roomtypeStatus"
														value="${requestScope.roomtype.roomtypeStatus}"><br>
												</div>

												<div class="form-group">
													<label for="roomtypeArea">平方公尺:</label> <input type="text"
														name="roomtypeArea" id="roomtypeArea"
														value="${requestScope.roomtype.roomtypeArea}"><br>
												</div>


												<div class="form-group">
													<label for="roomtypeIntroduce">房型介紹</label><br>
													<textarea rows="6" cols="40" name="canx" id="canx" required>${requestScope.roomtype.roomtypeIntroduce}</textarea>
													<br>
												</div>
											</form>
										</div>
									</div>
									<!-- /.row (nested) -->
								</div>
								<!-- /.panel-body -->
							</div>
							<!-- /.panel -->
						</div>
						<!-- /.col-lg-12 -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /#page-wrapper -->

		</div>
		<!-- /#wrapper -->

		<!-- jQuery -->
		<script src="common/js/jquery.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="common/js/bootstrap.min.js"></script>

		<!-- Metis Menu Plugin JavaScript -->
		<script src="common/js/metisMenu.min.js"></script>

		<!-- Custom Theme JavaScript -->
		<script src="common/js/startmin.js"></script>
</body>
</html>