<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<title>Online Shopping - ${title}</title>		
<!-- Bootstrap core JavaScript -->
		<script type="text/javascript" src="${js}/jquery.js"></script>
		<%-- <script type="text/javascript" src="${js}/jquery.validate.js"></script> --%>
		<script type="text/javascript" src="${js}/jquery.validate.min.js"></script>
		
		<%-- <script type="text/javascript" src="${js}/popper.min.js"></script> --%>
		<script type="text/javascript" src="${js}/bootstrap.min.js"></script>
		
		<%-- <script type="text/javascript" src="${js}/bootstrap.bundle.min.js"></script> --%>
		
		<%-- <script type="text/javascript" src="${js}/bootstrap.js"></script> --%>
		
		<!-- DataTable Plugin -->
		<script type="text/javascript" src="${js}/jquery.dataTables.js"></script>
		
		<%-- <script type="text/javascript" src="${js}/dataTables.jqueryui.js"></script> --%>
		
		
		
		<!-- DataTable Bootstrap Script -->
		<script type="text/javascript" src="${js}/dataTables.bootstrap.js"></script>
		
		<!-- BootBox -->
		<script type="text/javascript" src="${js}/bootbox.min.js"></script>
		 
		<%-- <script type="text/javascript" src="${js}/bootbox.all.min.js"></script> 
		<script type="text/javascript" src="${js}/bootbox.js"></script>
		<script type="text/javascript" src="${js}/bootbox.locales.js"></script>  
		<script type="text/javascript" src="${js}/bootbox.locales.min.js"></script> --%>
		
		<script> window.menu = '${title}';
				window.contextRoot ='${contextRoot}';
		</script>
		
		<!-- Self Code -->
		<script type="text/javascript" src="${js}/myapp.js"></script> 
		
		
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

	<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

	<!-- Theme from BootsWatch.com -->
<%-- <link href="${css}/bootswatch.css" rel="stylesheet"> --%>
<!-- <script>
  $(document).ready(function() {
    $("#myCategoryModal").modal();
  });
</script> -->
</head>
<body>
	<div class="wrapper">
		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<div class="content">
			<!-- Page Content -->
			<c:if test="${userClickHome == true}">
				<%@include file="home.jsp"%>
			</c:if>

			<c:if test="${userClickAbout == true}">
				<%@include file="about.jsp"%>
			</c:if>

			<c:if test="${userClickContact == true}">
				<%@include file="contact.jsp"%>
			</c:if>
			
			<c:if test="${userClickCategoryProduct == true or userClickAllProducts == true}">
				<%@include file="listProduct.jsp"%>
			</c:if>
			
			<!-- Show Single Product -->
			<c:if test="${userClickedOnShowSingleProduct == true}">
				<%@include file="singleProduct.jsp"%>
			</c:if>
			
			<!-- Manage Products -->
			<c:if test="${userClickManageProducts == true}">
				<%@include file="managedProducts.jsp"%>
			</c:if>
			<!-- Show Cart -->
			<c:if test="${userClickShowCart == true}">
				<%@include file="cart.jsp"%>
			</c:if>
		</div>
		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>
		
	</div>
</body>

</html>