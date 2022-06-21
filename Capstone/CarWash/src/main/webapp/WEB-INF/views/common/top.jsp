<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="context"><%=request.getContextPath()%></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"
name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
	<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</head>


<c:set var="homeUrl">${context}/work/product/goMain.do</c:set>
<c:set var="loginUrl">${context}/work/user/login.do</c:set>

<c:set var="officeUrl">${context}/work/product/retrieveProductList.do?category=O</c:set>
<c:set var="penUrl">${context}/work/product/retrieveProductList.do?category=P</c:set>
<c:set var="binderUrl">${context}/work/product/retrieveProductList.do?category=B</c:set>
<c:set var="designUrl">${context}/work/product/retrieveProductList.do?category=D</c:set>
<c:set var="storageUrl">${context}/work/product/retrieveProductList.do?category=S</c:set>

<body>
<div class="site-wrap">
    <header class="site-navbar" role="banner">
      <div class="site-navbar-top">
        <div class="container">
          <div class="row align-items-center">
          
          <div class="col-6 col-md-4 order-2 order-md-1 site-search-icon text-left">
          <a class="block-2-item" href="${context}/work/product/goMain.do">
             <img src="${pageContext.request.contextPath}/resources/designImg/m1.png" height="80px" width="150px">
            </a>
            </div>
            
            <div class="col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center">
              <div class="site-logo">
                <a href="${context}/work/product/goMain.do" class="js-logo-clone" style="font-size: 40px;"><strong>내차세차</strong></a>
              </div>
            </div>
            
	<div class="col-6 col-md-4 order-3 order-md-3 text-right">
		
			<ul class="nav navbar-nav navbar-right">
				<li>
					<c:if test="${sessionScope.id == null}">
						<a href="${context}/board/list.do"><font color="black"><strong>공지사항</strong></font></a>
					</c:if>
					<c:if test="${sessionScope.id != null && sessionScope.grade != 'A'}">
						<a href="${context}/board/list.do"><font color="black"><strong>공지사항</strong></font></a>
					</c:if>
					<c:if test="${sessionScope.id != null && sessionScope.grade == 'A'}">
						<a href="${context}/board/list.do"><font color="black"><strong>공지사항</strong></font></a>
					</c:if>
				</li>
				<li>
					<c:if test="${sessionScope.id != null && sessionScope.grade != 'A'}">
						<a href="${context}/work/cart/retrieveCartList.do"><font color="black"><strong>장바구니</strong></font></a>
					</c:if>
					<c:if test="${sessionScope.id != null && sessionScope.grade == 'A'}">
						<a href="${context}/work/product/retrieveProductListForManage.do"><font color="black"><strong>상품관리</strong></font></a>
					</c:if>
				</li>
				<li>
					<c:if test="${sessionScope.id != null && sessionScope.grade != 'A'}">
						<a href="${context}/work/sell/retrieveBuyList.do"><font color="black"><strong>구매내역</strong></font></a>
					</c:if>
					<c:if test="${sessionScope.id != null && sessionScope.grade == 'A'}">
						<a href="${context}/work/sell/retrieveStatisticsForProduct.do"><font color="black"><strong>세차장 등록</strong></font></a>
					</c:if>
				</li>
				<li>
					<c:if test="${sessionScope.id == null}">
						<a href="${context}/work/user/createUser.do"><font color="black"><strong>회원가입</strong></font></a>
					</c:if>
					<c:if test="${sessionScope.id != null && sessionScope.grade != 'A'}">
						<a href="${context}/work/user/updateUser.do"><font color="black"><strong>정보수정</strong></font></a>
					</c:if>
					<c:if test="${sessionScope.id != null && sessionScope.grade == 'A'}">
						<a href="${context}/work/product/retrieveStatisticsForStock.do?productCategoryCd=P"><font color="black"><strong>세차장 예약현황</strong></font></a>
					</c:if>
				</li>
				<li>
					<c:if test="${sessionScope.id == null}">
						<a href="${context}/work/user/login.do"><font color="black"><strong>LOGIN</strong></font></a>
					</c:if>
					<c:if test="${sessionScope.id != null}">
						<a href="${context}/work/user/logout.do"><font color="black"><strong>LOGOUT</strong></font></a>
					</c:if>
				</li>
			</ul>
		</div>
	</div>
		</div>
    </div>
      <nav class="site-navigation text-center text-md-center" role="navigation">
        <div class="container">
          <ul class="site-menu js-clone-nav d-none d-md-block">
            <li class="has-children active">
			<c:if test="${sessionScope.grade != 'A'}">
			<li><a style="font-size: 20px;" onclick="javascript:fn_isLogin('${sessionScope.userCode}','${loginUrl}','${penUrl}')"><strong>카샴푸&nbsp;&nbsp;|</strong></a></li>
		    <li><a style="font-size: 20px;" onclick="javascript:fn_isLogin('${sessionScope.userCode}','${loginUrl}','${officeUrl}')"><strong>드라잉타월&nbsp;&nbsp;|</strong></a></li>
		    <li><a style="font-size: 20px;" onclick="javascript:fn_isLogin('${sessionScope.userCode}','${loginUrl}','${storageUrl}')"><strong>크리너&nbsp;&nbsp;|</strong></a></li>
		    <li><a style="font-size: 20px;" onclick="javascript:fn_isLogin('${sessionScope.userCode}','${loginUrl}','${binderUrl}')"><strong>광택제&nbsp;&nbsp;|</strong></a></li>
		    <li><a style="font-size: 20px;" onclick="javascript:fn_isLogin('${sessionScope.userCode}','${loginUrl}','${designUrl}')"><strong>기타&nbsp;&nbsp;</strong></a></li>
	  	</c:if>
	  	<c:if test="${sessionScope.id != null && sessionScope.grade == 'A'}">
	  		<h1>관리자 모드입니다.</h1>
	  	</c:if>
	  	</ul>
	</div>
	</nav>
	</header>
	</div>
	 <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/aos.js"></script>

  <script src="src/main/webapp/resources/js/main.js"></script>
</body>
</html>