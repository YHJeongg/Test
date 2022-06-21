<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var ="context"><%=request.getContextPath()%></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="description" content="productListR.jsp">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"
name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>상품 목록</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/fonts/icomoon/style.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/aos.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/plugins/metisMenu/metisMenu.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/plugins/dataTables/dataTables.bootstrap.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.js"></script>

    <script>

	var existFolder = '';
	var imageFolder = '';
	var path = '';

    $(document).ready(function() {
        $('#dataTables-example').dataTable();
// 		fn_setImage();
    });

// 	function fn_setImage(){
// 		alert("fn_setImage()");
//         var productCategoryCd = '${dsProductList[0].PRODUCT_CATEGORY_CD}';

//         alert("productCategoryCd : " + productCategoryCd);

// 		if(productCategoryCd == 'O'){
// 			imageFolder = "officeImg";
// 		}else if(productCategoryCd == 'P'){
// 			imageFolder = "penImg";
// 		}else if(productCategoryCd == 'S'){
// 			imageFolder = "storageImg";
// 		}else if(productCategoryCd == 'D'){
// 			imageFolder = "designImg";
// 		}else if(productCategoryCd == 'B'){
// 			imageFolder = "binderImg";
// 		}
// 	}
<%--
	function fn_showResult(paramVoteCode, paramVoteCount){
		if(paramVoteCount == 0){
			alert("참여자가 없습니다.");
		}else{
			location.href = "${context}/work/result/retrieveResult.do?voteCode=" + paramVoteCode;
		}
	}

	function fn_checkVote(paramVoteCode){
		var param = {};

		param["voteCode"] = paramVoteCode;

		$.ajax({
			url:"${context}/work/result/retrieveExampleNo.do",
			contentType:"application/json",
			dataType:"json",
			data:param,
			success:function(result){
				if(result["checkMsg"] == "success"){
					fn_doVote(paramVoteCode);
					return true;
				}else if(result["checkMsg"] == "fail"){
					alert("이미 투표하셨습니다.");
					return false;
				}
			}
		});
	}
--%>
    </script>
</head>
<body>
<jsp:include page="../common/top.jsp"></jsp:include>
	<br><br><br>
	<div class="container">
		<div class="page-header">
			<h1>${dsProductList[0].PRODUCT_CATEGORY_CD_NM}</h1>
		</div>
			<div class="row">
				<c:forEach items="${dsProductList}" var="dsProductList" varStatus="dsProductIdx">
					<div class="col-md-6">
					<div class="block-4 text-center border">
					<figure class="block-4-image">
						<a href="${context}/work/product/retrieveProduct.do?productCode=${dsProductList.PRODUCT_CODE}"><img name="image" src="${context}/binderImg/${dsProductList.PRODUCT_IMAGE}" alt="Image placeholder" class="img-fluid"></a>
						</figure>
						<script type="text/javascript">
						 	var productCategoryCd = '${dsProductList.PRODUCT_CATEGORY_CD}';

							if(productCategoryCd == 'O'){
								imageFolder = "officeImg";
							}else if(productCategoryCd == 'P'){
								imageFolder = "penImg";
							}else if(productCategoryCd == 'S'){
								imageFolder = "storageImg";
							}else if(productCategoryCd == 'D'){
								imageFolder = "designImg";
							}else if(productCategoryCd == 'B'){
								imageFolder = "binderImg";
							}
							path = $("img[name='image']").eq('${dsProductIdx.index}').attr("src");

							existFolder = path.split("/")[2];
							$("img[name='image']").eq('${dsProductIdx.index}').attr("src", path.replace(existFolder, imageFolder));
						</script>
						<div class="block-4-text p-4">
							<div class="col-md-12">
								<h3><font color="black"><b>${dsProductList.PRODUCT_NAME}</b></font></h3>
					        </div>
					        <div class="col-md-12">
					        	<h4 class="mb-0" style="font-family: inherit;"><b>${dsProductList.PRODUCT_UNIT_PRICE}원</b></h4>
					        </div>
					        <div class="col-md-12">
						        <h4 class="text-primary font-weight-bold" style="font-family: inherit;"><font color="lightblack"><b>남은 수량 : ${dsProductList.PRODUCT_COUNT}</b></font></h4>
					        </div>
				        </div>
				        <c:if test="${dsProductIdx.index % 2 == 1}">
							&nbsp;
						</c:if>
						</div>
					</div>
				</c:forEach>
				</div>
		</div>
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