<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>台玩</title>
<%@ include file="/common/head.jsp"%>

<!-- Favicon title的小圖 -->
<link rel="icon" href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">

<!-- Style CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/classy-nav-first.css">

<!-- 搜尋bar -->
<!-- Google font -->
<link href="https://fonts.googleapis.com/css?family=Cardo:700" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,700" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.css"
	integrity="sha512-UTNP5BXLIptsaj5WdKFrkFov94lDx+eBvbKyoe1YAfjeRPC+gT5kyZ10kOHCfNZqEui1sxmqvodNUx3KbuYI/A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css"
	integrity="sha512-sMXtMNL1zRzolHYKEujM2AqCLUR9F2C4/05cdbxjjLSRvMQIciEPCQZo++nk7go3BtSuK9kfa/s+a4f4i5pLkw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>

<style>
label{
font-size:1em;
}
select{
height:30px;
}
input{
backgroung-color:#fff;
height:30px;
}
form div{
display:inline-block;
margin:40px;
}
</style>
</head>
<body>
	<%@ include file="/front-end/homepage/header.jsp" %>
	<%@ include file="/front-end/cust/side-bar.jsp" %>
	
	<main id="main" class="main" style="padding-left:70px;padding-top:40px;overflow:auto;">

    <div class="pagetitle">
      <h1>關注廠商</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">首頁</a></li>
          <li class="breadcrumb-item">會員功能</li>
          <li class="breadcrumb-item active">關注廠商</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->
    
   	<!-- 選擇要加入關注的商家 -->
   	<form method="post" action="cust/InsertFollow">
		<div>
			<label for="company">選取要關注的商家</label><br>
			<select id="company" name="company">
			<c:forEach var="company" items="${selectCompanyList}">
				<option value="${company.cmpId}">${company.cmpName}</option>			
			</c:forEach>
			</select>
		</div>
		
		<button class="btn btn-primary">加入關注</button>
	</form> 
	
    <section class="section">
      <div class="row">
        <div class="col-lg-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">關注廠商列表</h5>

              <!-- Table with stripped rows -->
              <table class="table table-striped">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">商家名稱</th>
                    <th scope="col">取消關注</th>
                  </tr>
                </thead>
                <tbody>
                <% int count=0; %>
                <c:forEach var="follow" items="${list}">
                  <tr>
                    <th scope="row"><%= ++count %></th>
                  <c:forEach var="company" items="${companyList}">
                   <c:if test="${follow.cmpId==company.cmpId}">
                    <td><a href="roomOrder12/ROSelectCmp?ckin=&ckout=&cmpId=${follow.cmpId}">${company.cmpName}</a></td>
                   </c:if>
                  </c:forEach>
                    <td>
                    	<form method="post" action="cust/DeleteFollow">
                    		<button type="submit" class="btn btn-primary">取消關注</button>
                    		<input type="hidden" name="custId" value="${customer.custId}">
                    		<input type="hidden" name="cmpId" value="${follow.cmpId}">
                    	</form>
                    </td>
                  </tr>
                 </c:forEach>
                 </tbody>
                </table>
                <!-- End Table with stripped rows -->

              </div>
            </div>

          </div>
        </div>
      </section>

    </main><!-- End #main -->
	  <%@ include file="/front-end/homepage/footer.jsp" %>
</body>
</html>