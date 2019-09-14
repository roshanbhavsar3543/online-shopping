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
<title>Online Shopping - ${title}</title>		
<!-- Bootstrap core JavaScript -->
		<script type="text/javascript" src="${js}/jquery.js"></script>
		<%-- <script type="text/javascript" src="${js}/jquery.validate.js"></script> --%>
		<script type="text/javascript" src="${js}/jquery.validate.min.js"></script>
		
		<%-- <script type="text/javascript" src="${js}/popper.min.js"></script> --%>
		<script type="text/javascript" src="${js}/bootstrap.min.js"></script>
		<!-- DataTable Plugin -->
		<script type="text/javascript" src="${js}/jquery.dataTables.js"></script>		
		
		<!-- DataTable Bootstrap Script -->
		<script type="text/javascript" src="${js}/dataTables.bootstrap.js"></script>
		
		<!-- BootBox -->
		<script type="text/javascript" src="${js}/bootbox.min.js"></script>
		
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
		<style> 
    body {
        padding-top: 60px;
    }
</style>

</head>
<body>
	<div class="wrapper">
		
		<%@include file="flows-navbar.jsp" %>
		
		<!-- Page content -->
		<div class="content">