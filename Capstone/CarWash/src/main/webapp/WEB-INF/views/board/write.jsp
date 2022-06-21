<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>새 글 쓰기</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/board.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.js"></script>
<script type="text/javascript">
   function writeFormCheck() {
if($("#title").val() == null || $("#title").val() == ""){
alert("제목을 입력해 주세요!");
$("#title").focus();
	return false;
}
if($("#content").val() == null || $("#content").val() == ""){
alert("내용을 입력해 주세요!");
$("#content").focus();
return false;
}
return true;
   }
</script>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>
<div class="wrapper">
<h3>새 글 쓰기</h3>
<form action="write.do" method="post" onsubmit="return writeFormCheck()" enctype="multipart/form-data">
<table class="table">
<tr>
<th><label for="subject">제목</label></th>
<td>
<input type="text" id="title" name="title" class="boardSubject"/>
<input type="hidden" id="writer" name="writer" value="${userName}" /> <!-- 세션변수 -->
<input type="hidden" id="id" name="id" value="${id}" />
</td>
</tr>
<tr>
<th><label for="content">내용</label></th>
<td><textarea id="content" name="content" class="boardContent"></textarea></td>
</tr>
<tr>
<th><label for="file">파일</label></th>
<td><input type="file" id="file" name="file" multiple />
<span class="date">&nbsp;&nbsp;*&nbsp;임의로 파일명이 변경될 수 있습니다.</span></td>
</tr>
</table>
<br />
<input type="reset" value="재작성" class="writeBt"/>
<input type="submit" value="확인" class="writeBt"/>
</form>
</div>
	<jsp:include page="../common/foot.jsp"></jsp:include>
</body>
</html>
