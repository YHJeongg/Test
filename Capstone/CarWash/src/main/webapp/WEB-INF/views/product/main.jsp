<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="context"><%=request.getContextPath()%></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"
name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="main.jsp">
<title>내차세차</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/fonts/icomoon/style.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/aos.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>

<c:set var="loginUrl">${context}/work/user/login.do</c:set>

<c:set var="officeUrl">${context}/work/product/retrieveProductList.do?category=${dsProductList[0].PRODUCT_CATEGORY_CD}</c:set>
<c:set var="penUrl">${context}/work/product/retrieveProductList.do?category=${dsProductList[1].PRODUCT_CATEGORY_CD}</c:set>
<c:set var="binderUrl">${context}/work/product/retrieveProductList.do?category=${dsProductList[2].PRODUCT_CATEGORY_CD}</c:set>
<c:set var="designUrl">${context}/work/product/retrieveProductList.do?category=${dsProductList[3].PRODUCT_CATEGORY_CD}</c:set>
<c:set var="storageUrl">${context}/work/product/retrieveProductList.do?category=${dsProductList[4].PRODUCT_CATEGORY_CD}</c:set>

<c:set var="productManageUrl">${context}/work/product/retrieveProductListForManage.do</c:set>
<c:set var="statisticsForProductUrl">${context}/work/sell/retrieveStatisticsForProduct.do</c:set>
<c:set var="statisticsForStockUrl">${context}/work/product/retrieveStatisticsForStock.do?productCategoryCd=P</c:set>

<script type="text/javascript"></script>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>
			<c:if test="${sessionScope.grade != 'A'}">
				<div class="row">
					<div id="myCarousel" class="carousel slide carousel-fade" data-ride="carousel">
				    <!-- Indicators -->
					<ol class="carousel-indicators">
				    	<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				        <li data-target="#myCarousel" data-slide-to="1"></li>
				        <li data-target="#myCarousel" data-slide-to="2"></li>
				        <li data-target="#myCarousel" data-slide-to="3"></li>
				    </ol>
				    
				    <div class="site-navbar-top">
        			<div class="container">
          			<div class="row align-items-center">
					<div class="carousel-inner" role="listbox">
						<div class="item active">
					    	<a><img src="${pageContext.request.contextPath}/resources/images/car_1.jpg"></a>
					    </div>
						<div class="item">
					    	<a><img src="${pageContext.request.contextPath}/resources/images/car_2.jpg"></a>
					    </div>
					    <div class="item">
					    	<a><img src="${pageContext.request.contextPath}/resources/images/car_3.jpg"></a>
					    </div>
					    <div class="item">
					    	<a><img src="${pageContext.request.contextPath}/resources/images/car_4.jpg"></a>
					    </div>
					        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
					            <span class="glyphicon glyphicon-chevron-left"></span>
					            <span class="sr-only">Previous</span>
					        </a>
					        <a class="right carousel-control" href="#myCarousel" data-slide="next">
					            <span class="glyphicon glyphicon-chevron-right"></span>
					            <span class="sr-only">Next</span>
					        </a>
						</div>
					</div>
					</div>
					</div>
					</div>
					<hr>
				</div>
			<div class="container">
				<div class="page-header">
					<h1>광택제</h1>
				</div>
					<div class="row" >
						<c:forEach items="${dsBinderList}" var="dsBinderList">
							<div class="col-md-4">
								<a onclick="javascript:fn_isLogin('${sessionScope.userCode}','${loginUrl}','${binderUrl}')"><img src="${context}/binderImg/${dsBinderList.PRODUCT_IMAGE}"></a>
							</div>
						</c:forEach>
					</div>
			</div><br /><br />
			<div class="container">
				<div class="page-header">
					<h1>기타</h1>
				</div>
					<div class="row">
						<c:forEach items="${dsDesignList}" var="dsDesignList">
							<div class="col-md-3">
								<a onclick="javascript:fn_isLogin('${sessionScope.userCode}','${loginUrl}','${designUrl}')"><img src="${context}/designImg/${dsDesignList.PRODUCT_IMAGE}" class="img-circle" width="100%" height="100%"></a>
							</div>
						</c:forEach>
				</div>
			</div><br /><br />
			<div class="container">
				<div class="page-header">
					<h1>드라잉타월</h1>
				</div>
					<div class="row">
						<c:forEach items="${dsOfficeList}" var="dsOfficeList" varStatus="officeIdx">
							<div class="col-md-4">
								<a onclick="javascript:fn_isLogin('${sessionScope.userCode}','${loginUrl}','${officeUrl}')"><img src="${context}/officeImg/${dsOfficeList.PRODUCT_IMAGE}" class="img-thumbnail" width="100%" height="100%"></a>
								<c:if test="${officeIdx.index == 2}">
									&nbsp;
								</c:if>
							</div>
						</c:forEach>
				</div>
			</div><br /><br />
			<div class="container">
				<div class="page-header">
					<h1>카샴푸</h1>
				</div>
					<div class="row">
						<c:forEach items="${dsPenList}" var="dsPenList" varStatus="penIdx">
							<div class="col-md-3">
								<a onclick="javascript:fn_isLogin('${sessionScope.userCode}','${loginUrl}','${penUrl}')"><img src="${context}/penImg/${dsPenList.PRODUCT_IMAGE}" class="img-rounded" width="100%" height="100%"></a>
								<c:if test="${penIdx.index == 3}">
									&nbsp;
								</c:if>
							</div>
						</c:forEach>
					</div>
			</div><br /><br />
			<div class="container">
				<div class="page-header">
					<h1>크리너</h1>
				</div>
					<div class="row">
						<c:forEach items="${dsStorageList}" var="dsStorageList">
							<div class="col-md-2">
								<a onclick="javascript:fn_isLogin('${sessionScope.userCode}','${loginUrl}','${storageUrl}')"><img src="${context}/storageImg/${dsStorageList.PRODUCT_IMAGE}" class="img-rounded" width="100%" height="100%"></a>
							</div>
						</c:forEach>
				</div>
			</div>
			</c:if>
			<c:if test="${sessionScope.grade == 'A'}">
			<div class="container" style="margin-top: 10%; margin-bottom: 10%">
				<div class="row">
					<div class="col-md-4">
					    <a href="${productManageUrl}" class="btn btn-primary" style="width: 100%; height: 250px;" role="button">
							<h1><span class="glyphicon glyphicon-list-alt" style="font-size: 80px; margin-top: 5%;"></span> <br/>상품관리</h1>
						</a>
					</div>
					<div class="col-md-4">
					    <a href="${statisticsForProductUrl}" class="btn btn-danger" style="width: 100%; height: 250px;" role="button">
							<h1><span class="glyphicon glyphicon-phone" style="font-size: 80px; margin-top: 5%;"></span> <br/>세차장 등록</h1>
					    </a>
					</div>
					<div class="col-md-4">
					    <a href="${statisticsForStockUrl}" class="btn btn-info" style="width: 100%; height: 250px;" role="button">
							<h1><span class="glyphicon glyphicon-ok" style="font-size: 80px; margin-top: 5%;"></span> <br/>세차장 예약관리</h1>
		    		    </a>
					</div>
				</div>
			</div>
			</c:if>
  <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/aos.js"></script>

  <script src="src/main/webapp/resources/js/main.js"></script>
	<jsp:include page="../common/foot.jsp"></jsp:include>
</body>
</html>