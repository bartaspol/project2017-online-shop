<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CONTEXT ROOT VARIABLE -->
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!-- VARIABLES -->
<c:url var="search" value="/search" />
<!-- FONTS -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald"
	rel="stylesheet">
<!-- BOOTSTRAP -->
<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextRoot}/css/main.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>


<title><tiles:insertAttribute name="title" /></title>
</head>
<body>

	<!-- 	SEARCH -->
	<div class="row search-bar navbar-fixed-top">
		<div class="col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1">

			<form action="${search}" method="get">
<%-- 				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>

				<div class="input-group input-group-lg">
					<input type="text" class="form-control" name="s" placeholder="czego szukasz?"> 
					<span class="input-group-btn">
						<button id="search-button" class="btn btn-primary" type="submit">Szukaj</button>
					</span>
				</div>
			</form>
		</div>
	</div>

	<!-- 	NAVBAR -->
	<nav class="navbar navbar-inverse main-navbar">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextRoot}/"><i class="fa fa-home"
							aria-hidden="true"></i> Home</a></li>
							
<!-- 					<li class="dropdown"><a class="dropdown-toggle" -->
<!-- 						data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a> -->
<!-- 						<ul class="dropdown-menu"> -->
<!-- 							<li><a href="#">Page 1-1</a></li> -->
<!-- 							<li><a href="#">Page 1-2</a></li> -->
<!-- 							<li><a href="#">Page 1-3</a></li> -->
<!-- 						</ul></li> -->

					<li><a href="/cameras">Aparaty</a></li>
					<li><a href="/lenses">Obiektywy</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
				
<!-- 				LOGIN -->
					<sec:authorize access="!isAuthenticated()">
						<li><a href="${contextRoot}/register"><i class="fa fa-user-plus" aria-hidden="true"></i> Załóż konto</a></li>
						<li><a href="${contextRoot}/login"><i class="fa fa-sign-in" aria-hidden="true"></i> Zaloguj się</a></li>
					</sec:authorize>
					
<!-- 				SHOW IF ADMIN -->
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="${contextRoot}/addproduct"><i class="fa fa-plus-square" aria-hidden="true"></i> Dodaj produkt</a></li>
					</sec:authorize>
					
<!-- 				IF LOGGED IN -->
					<sec:authorize access="isAuthenticated()">
					
					<li><a href="${contextRoot}/profile"><i class="fa fa-user" aria-hidden="true"></i> Moje konto</a></li>
					
<!-- 				LOGOUT -->
						<li><a href="javascript:$('#logoutForm').submit();">
							<i class="fa fa-sign-out" aria-hidden="true"></i> Wyloguj się</a></li>
					</sec:authorize>
					
					<li><a href="#contact" data-toggle="modal"><i class="fa fa-phone-square" aria-hidden="true"></i> Kontakt</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<c:url var="logoutLink" value="/logout"/>
	<form id="logoutForm" method="post" action="${logoutLink}">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

<!-- 	CONTENT 	-->
<!-- 	OF 			-->
<!-- 	THE PAGE 	-->
	<tiles:insertAttribute name="content" />


	<!-- 	FOOTER -->
	<div class="navbar navbar-inverse navbar-fixed-bottom footer"
		role="navigation">
		<div class="container">
			<div class="navbar-text pull-left">
				<p>© 2017 Bartosz Piatek</p>
			</div>
			<div class="navbar-text pull-right">
				<a href="https://github.com/bartaspol"><i
					class="fa fa-github fa-2x" aria-hidden="true"></i></a>
			</div>
		</div>
	</div>

	<!-- 	CONTACT POPUP -->
	<tiles:insertAttribute name="contact-popup" />

	<!-- 	JAVA SCRIPT -->
	<!-- 	Bootstrap -->
	<script src="${contextRoot}/js/bootstrap.min.js"></script>
	<!-- 	Font awesome -->
	<script src="https://use.fontawesome.com/c3926bbda0.js"></script>
</body>