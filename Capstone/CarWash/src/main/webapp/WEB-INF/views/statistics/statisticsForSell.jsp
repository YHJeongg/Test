<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="context"><%=request.getContextPath()%></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="description" content="statisticsForSell.jsp">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>내차세차</title>

	<link href="${context}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${context}/css/bootstrap-theme.css" rel="stylesheet">
	<link href="${context}/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
	<link href="${context}/css/plugins/dataTables.bootstrap.css" rel="stylesheet">

    <link href="${context}/css/sb-admin-2.css" rel="stylesheet">
    <link href="${context}/css/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${context}/font-awesome-4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<script src="${context}/js/jquery-1.9.1.js"></script>
	<script src="${context}/js/bootstrap.min.js"></script>

    <script src="${context}/js/plugins/metisMenu/metisMenu.min.js"></script>

    <script src="${context}/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="${context}/js/plugins/dataTables/dataTables.bootstrap.js"></script>

    <script src="${context}/js/sb-admin-2.js"></script>
    <script src="${context}/js/morris.min.js"></script>
    <script src="${context}/js/raphael.js"></script>
    
   

</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>
	<div class="container">
		<div class="jumbotron jumbotron-info" style="background-color: lightgray;">
			<h1><font color="black"><strong>세차장 등록</strong>&nbsp;<span class="glyphicon glyphicon-phone"></span></font></h1>
			<p>세차장 등록 페이지입니다.</p>
		</div>
		
		<div class="row">
			<div class="col-md-1 col-md-offset-11">
				<button type="button"  class="btn btn-success btn-lg"  style="float:right;" onclick="location.href='/spring/statistics/selectTest.jsp';">세차장 등록현황</button>
			</div>
		</div>

		<form method = "post"  enctype="multipart/form-data" action = "/spring/statistics/insertTest.jsp">
		<label class="control-label col-md-2"><b>세차장번호</b></label> <input class="form-control" type = "text" name = "carwash_id"><p>
        <label class="control-label col-md-2"><b>세차장명</b></label> <input class="form-control" type = "text" name = "carwash_name"><p>
         <label class="control-label col-md-2"><b>세차장 위도</b></label> <input class="form-control" type = "text" name = "carwash_latitude"><p>
          <label class="control-label col-md-2"><b>세차장 경도</b></label> <input class="form-control" type = "text" name = "carwash_hardness"><p>
          <label class="control-label col-md-2"><b>세차장 기본주소</b></label> <input class="form-control" type = "text" name = "carwash_address"><p>
  	<label class="control-label col-md-2"><b>세차장 우편번호</b></label> <input class="form-control" type = "text" name = "carwash_post"><p> 
  	<label class="control-label col-md-2"><b>세차장 상세주소</b></label> <input class="form-control" type = "text" name = "carwash_detail"><p>
            <label class="control-label col-md-2"><b>가격</b></label> <input class="form-control" type = "text" name = "carwash_salary"><p>
            <label class="control-label col-md-2"><b>전화번호</b></label> <input class="form-control" type = "text" name = "carwash_phone"><p>
            <label class="control-label col-md-2"><b>운영시간</b></label> <input class="form-control" type = "text" name = "carwash_time"><p>
            <label class="control-label col-md-2"><b>판매상품</b></label> <input class="form-control" type = "text" name = "carwash_shop"><p>
            <label class="control-label col-md-2"><b>운영시스템</b></label> <input class="form-control" type = "text" name = "carwash_system"><p>
            <label class="control-label col-md-2"><b>편의시설</b></label> <input class="form-control" type = "text" name = "carwash_facilities"><p>
    		
    		<div class="form-group">
				<label class="control-label col-md-2"><b>세차장사진</b></label>
				<img id="pic" style="margin-left: 15px;" height="180px" width="150px" src="${context}/backgroundImage/defaultpic.png"><br/>
				<div class="col-md-6">
					<input type="hidden" id="productImage" name="carwash_image" required="required">
				</div>
			</div>             
           
            
      
      
	<form id="ajaxform" action="${context}/work/product/saveFile.do" method="post" enctype="multipart/form-data" role="form">
		<div class="form-group">
		<label class="control-label col-md-2"></label>
			<div class="col-md-6">
				<input class="form-control" type="file" id="imageFile" name="imageFile" onchange="fn_upload()" onclick="return fn_checkCategory()"/>
				<input type="hidden" id="imageFolder" name="imageFolder">
			</div>
		</div>
		<br><br><br>
	</form>
	<input type = "submit" value = "보내기">
      </form>
 
		
		<div class="row">
			<div class="col-md-1 col-md-offset-11">
				<button type="button" class="btn btn-success btn-lg"  style="float:right;" onclick="fn_back()">뒤로가기</button>
			</div>
		</div>
<jsp:include page="../common/foot.jsp"></jsp:include>
</body>
</html>