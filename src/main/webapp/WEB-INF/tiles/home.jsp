<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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
		<pagin:pagination size="3" conextRoot="${contextRoot}" page="${page}" />
	</div>
	<div class="row">

		<c:forEach var="product" items="${page.content}">

			<c:url var="editButtonLink" value="/editproduct?id=${product.id}"/>
			<c:url var="deleteButtonLink" value="/deleteproduct?id=${product.id}"/>
			
			<div class="col-sm-4 col-sm-offset-0">

				<a href="#" class="thumbnail"> <img src="img/test.jpg"
					alt="some name">
				</a>
				<h3 class="text-center text-uppercase">${product.name}</h3>
				<h2 class="text-danger text-center">CENA: 12 ZŁ</h2>
				<p>${product.description}</p>
				
<!-- 			SHOW IF ADMIN -->
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="text-center">
						<a href="${editButtonLink}" class="btn btn-info">Edytuj</a>
						<a onclick="return confirm('Usunąć produkt?')" 
							href="${deleteButtonLink}" class="btn btn-danger">Usuń</a> <br> <br>
					</div>
				</sec:authorize>
			</div>

		</c:forEach>
	</div>
</div>
