<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- security csrf meta tag : ajax post 전송시 함께 전송해야 security 통과 -->
	<!--  jQuery CSS--> 
	<link href="http://code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<!--  Bootstrap CSS--> 
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<!--  Datatable CSS--> 
	<!-- <link href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />  -->
    <link href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.datatables.net/responsive/2.2.6/css/responsive.bootstrap.min.css" rel="stylesheet" />
	<!--  Datatable Buttons CSS--> 
    <link href="https://cdn.datatables.net/buttons/1.6.4/css/buttons.dataTables.min.css" rel="stylesheet" />  
    <link href="https://cdn.datatables.net/buttons/1.6.4/css/buttons.bootstrap.min.css" rel="stylesheet" />  
	<!--  font-awesome CSS--> 
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />

	<!-- [jQuery Bootstrap Datatable JS] -->
	<!-- [jQuery] -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script> 
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
	<!-- ckEditor -->
	<script src="//cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script>
	<!-- [Bootstrap] -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<!-- [DataTable] -->	
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.6/js/dataTables.responsive.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.6/js/responsive.bootstrap.min.js"></script>
	<!-- [DataTable Buttons Excel PDF CSV] -->    
    <script src="https://cdn.datatables.net/buttons/1.6.4/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.6.4/js/buttons.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.6.4/js/buttons.flash.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.6.4/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.6.4/js/buttons.print.min.js"></script>

	<script src="<c:url value='/resources/js/jquery.serialize-object.js' />" type="text/javascript"></script>

	<script type="text/javascript" charset="utf-8">
		sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
	</script>
	<title>Invoice</title>

</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
		int pageNumber = 1;	//기본 페이지
		if(request.getParameter("pageNumber") != null){
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
	%>

	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/home">Spring Simple Invoice</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/board/qna_list">고객센터</a></li>
				<li><a href="${pageContext.request.contextPath}/product/category_insertform">카테고리 등록</a></li>
				<li><a href="${pageContext.request.contextPath}/product/category_list">카테고리 조회</a></li>
				<li><a href="${pageContext.request.contextPath}/product/product_insertform">제품 등록</a></li>
				<li><a href="${pageContext.request.contextPath}/product/product_list_datatable">제품 조회(DataTable)</a></li>
				<li><a href="${pageContext.request.contextPath}/sales/invoice_insertform">인보이스 등록</a></li>
				<li><a href="${pageContext.request.contextPath}/sales/invoice_list">인보이스 조회</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/join">회원가입</a></li>
				<li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
			</ul>

		</div>
	</nav>
	