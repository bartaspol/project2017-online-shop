<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<div class="container home">

	<c:choose>
		<c:when test="${fn:length(orders) > 0}">
			<div class="page-header">
				<h1 class="text-danger text-center text-uppercase">HISTORIA ZAMÓWIEŃ</h1>
			</div>
		</c:when>

		<c:otherwise>
			<div class="jumbotron text-center">
				<h1>NIE DOKONAŁEŚ JESZCZE ŻADNYCH ZAMÓWIEŃ</h1>
				<h3 class="text-danger">
					<a href="${contextRoot}/"> wróć do strony głównej</a>
				</h3>
			</div>
		</c:otherwise>
	</c:choose>

	
	<section class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<c:forEach var="order" items="${orders}">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<h3 class="text-uppercase text-danger"> Zamówienie nr ${order.id }</h3> 
			
			
				<c:forEach var="prod" items="${order.products}">
					<div class="col col-xs-12 col-sm-3 col-md-2">
						<a href="${contextRoot}/product/${prod.id}" title="Lorem ipsum" class="image-order">
							<img src="${contextRoot}/productphoto?id=${prod.id}" alt="Lorem ipsum" />
						</a>
					</div>
					
					<div class="col col-xs-12 col-sm-3 col-md-4">
						<h2 class="text-uppercase"><a href="${contextRoot}/product/${prod.id}" title=""><strong>${prod.name}</strong></a></h2>
						<h3 class="text-danger">${prod.price} ZŁ</h3>
					</div>
					<br>

				</c:forEach>
			</div>
		</c:forEach>
	</section>

</div>