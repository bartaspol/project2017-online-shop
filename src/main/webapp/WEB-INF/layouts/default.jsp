<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<!-- FONT -->
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

			<form action="${search}" method="post">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<div class="input-group input-group-lg">
					<input type="text" class="form-control" name="s"
						placeholder="czego szukasz?"> <span
						class="input-group-btn">
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
					<li class="active"><a href="/"><i class="fa fa-home"
							aria-hidden="true"></i> Home</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Page 1-1</a></li>
							<li><a href="#">Page 1-2</a></li>
							<li><a href="#">Page 1-3</a></li>
						</ul></li>
					<li><a href="#">Page 2</a></li>
					<li><a href="/addproduct">Dodaj produkt</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"> Załóż konto</a></li>
					<li><a href="#"><i class="fa fa-sign-in"
							aria-hidden="true"></i> Zaloguj się</a></li>
					<li><a href="#"><i class="fa fa-phone-square" aria-hidden="true"></i> Kontakt</a></li>
				</ul>
			</div>
		</div>
	</nav>


	<!-- 		SITE CONTENT -->
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


	<!-- 	JAVA SCRIPT -->
	<!-- 	Bootstrap -->
	<script src="${contextRoot}/js/bootstrap.min.js"></script>
	<!-- 	Font awesome -->
	<script src="https://use.fontawesome.com/c3926bbda0.js"></script>
</body>