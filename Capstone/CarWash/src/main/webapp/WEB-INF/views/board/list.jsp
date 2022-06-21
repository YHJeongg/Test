<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.css">
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
<title>게시판 글 목록</title>
<link href="${pageContext.request.contextPath}/resources/css/board.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.js"></script>
<script type="text/javascript">

function selectedOptionCheck(){
    $("#type > option[value=<%=request.getParameter("type")%>]").attr("selected", "true");
}

function moveAction(where){
	switch (where) {
		case 1:
		location.href = "write.do";
		break;

		case 2:
		location.href = "list.do";
		break;
	}
}

</script>
</head>
<body onload="selectedOptionCheck()">
<jsp:include page="../common/top.jsp"></jsp:include>
	<div class="wrapper">
		<table border="0" class="table table-hover">
		<thead class="thead-light">
		<tr>
   			<th>글번호</th><th>제목</th><th>작성자</th><th>댓글수</th><th>조회수</th><th>추천수</th><th>작성일</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="board" items="${boardList}">
		<tr>
			<td class="idx">${board.bno}</td>
			<td align="left" class="subject">
			<c:if test="${board.replycnt >= 10}"><img src="${pageContext.request.contextPath}/resources/img/hit.jpg" /></c:if>
			<a href="view.do?bno=${board.bno}">${board.title}</a></td>
			<td class="writer">  ${board.id}</td>
			<td class="comment">${board.replycnt}</td>
			<td class="hitcount">${board.viewcnt}</td>
			<td class="recommendcount">${board.recommendcnt}</td>
			<td class="writeDate">${board.regDate}</td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
		<br />
		${pageHttp}
		<div class="navBar">
		<form action="list.do" modelAttribute="searchVO" method="get">
			<select id="type" name="type">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="id">작성자</option>
			</select>
			<input type="text" id="keyword" name="keyword" 
			value="<%if(request.getParameter("keyword") != null)
           				{ out.print(request.getParameter("keyword")); } 
         			else { out.print(""); }%>" />
			<input type="submit" value="검색" class="btn" /><br />
		</form>
		</div><br /><br />
		<input type="button" value="목록" class="btn" onclick="moveAction(2)"/>
		<c:if test="${sessionScope.grade == 'A'}">
		<input type="button" value="쓰기" class="btn" onclick="moveAction(1)"/>
		</c:if>
		</div>
		<jsp:include page="../common/foot.jsp"></jsp:include>
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