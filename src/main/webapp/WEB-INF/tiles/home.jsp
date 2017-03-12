<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="pagin" tagdir="/WEB-INF/tags"%>


<!-- CONTEXT ROOT VARIABLE -->
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<div class="container home">
	<div class="jumbotron jumbotron-home text-center">
		<h1>Witamy</h1>
		<p>życzymy udanych zakupów</p>
	</div>

	<div class="pag">
		<pagin:pagination size="5" url="${contextRoot}" page="${page}" />
	</div>
<!-- 	<div class="row"> -->
		<c:forEach var="product" items="${page.content}">
<!-- 		VARIABLES -->
			<c:url var="editButtonLink" value="editproduct?id=${product.id}"/>
			<c:url var="deleteButtonLink" value="deleteproduct?id=${product.id}"/>
			<c:url var="orderButtonLink" value="orderproduct?id=${product.id}"/>
<!-- 		PRODUCT  -->

		<section class="col-xs-12 col-sm-6 col-md-12">
			<article class="search-result row">
				<div class="col-xs-12 col-sm-12 col-md-3">
					<a href="${contextRoot}/product/${product.id}" title="Lorem ipsum" class="thumbnail"><img src="${contextRoot}/productphoto?id=${product.id}" alt="Lorem ipsum" /></a>
				</div>
	<!-- 			<div class="col-xs-12 col-sm-12 col-md-2"> -->
	<!-- 				<ul class="meta-search"> -->
	<!-- 					<li><i class="glyphicon glyphicon-calendar"></i> <span>02/15/2014</span></li> -->
	<!-- 					<li><i class="glyphicon glyphicon-time"></i> <span>4:28 pm</span></li> -->
	<!-- 					<li><i class="glyphicon glyphicon-tags"></i> <span>People</span></li> -->
	<!-- 				</ul> -->
	<!-- 			</div> -->
				<div class="col-xs-12 col-sm-12 col-md-7 excerpet">
					<h2 class="text-uppercase"><a href="${contextRoot}/product/${product.id}" title="">${product.name}</a></h2>
					<p>${product.description}</p>	
					<h2 class="text-danger">${product.price} ZŁ</h2>					
				</div>
				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="text-center">
						<a href="${editButtonLink}" class="btn btn-info">Edytuj</a>
						<a onclick="return confirm('Usunąć produkt?')" 
							href="${deleteButtonLink}" class="btn btn-danger">Usuń</a> <br> <br>
					</div>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_USER')">
					<div class="text-center">
						<a href="${orderButtonLink}" class="btn btn-lg btn-danger">Zamów</a>
					</div>
				</sec:authorize>
				<span class="clearfix borda"></span>
				<div class="hr-line-dashed"></div>
			</article>
			
			
		</section>
	
<!-- 			<div class="col-sm-6 col-md-4 text-center"> -->

<%-- 				<a href="/product/${product.id}" class="thumbnail"> <img src="/productphoto?id=${product.id}" --%>
<!-- 					alt="some name"> -->
<!-- 				</a> -->
				
<%-- 				<h3 class="text-uppercase">${product.name}</h3> --%>
<%-- 				<h2 class="text-danger">${product.price} ZŁ</h2> --%>
<%-- 				<p>${product.description}</p> --%>
				
<!-- 			SHOW IF ADMIN -->
<%-- 				<sec:authorize access="hasRole('ROLE_ADMIN')"> --%>
<!-- 					<div class="text-center"> -->
<%-- 						<a href="${editButtonLink}" class="btn btn-info">Edytuj</a> --%>
<!-- 						<a onclick="return confirm('Usunąć produkt?')"  -->
<%-- 							href="${deleteButtonLink}" class="btn btn-danger">Usuń</a> <br> <br> --%>
<!-- 					</div> -->
<%-- 				</sec:authorize> --%>
<!-- 			</div> -->

		</c:forEach>
<!-- 	</div> -->

	<div class="pag">
		<pagin:pagination size="5" url="${contextRoot}" page="${page}" />
	</div>
</div>