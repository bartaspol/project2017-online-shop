<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<c:url var="editProfileLink" value="/editprofile" />
<c:url var="payLink" value="/pay" />

<div class="container home">

	<div class="jumbotron jumbotron-home text-center">
		<h1>SPRAWDZ ZAMÓWIENIE I ADRES</h1>
	</div>
<!-- 	<div class="page-header"> -->
<!-- 		<h1 class="text-danger text-center text-uppercase"></h1> -->
<!-- 	</div> -->
	
	
	
	<div class="col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
			<h2 class="text-danger text-center text-uppercase">${fn:length(order)}
				<c:choose>
					<c:when test="${fn:length(order) == 1}">PRODUKT</c:when>
					<c:otherwise>PRODUKTY</c:otherwise>
				</c:choose>
			</h2>
	</div>
	
	    <c:forEach var="o" items="${order}">
	    
	    	<section class="col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
	    		<div class="col-xs-12 col-sm-12 col-md-2">
					<a href="${contextRoot}/product/${o.id}" title="Lorem ipsum" class="image-order">
						<img src="${contextRoot}/productphoto?id=${o.id}" alt="Lorem ipsum" />
					</a>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-10 excerpet">
					<h2 class="text-uppercase"><a href="${contextRoot}/product/${o.id}" title=""><strong>${o.name}</strong></a></h2>
					<h3 class="text-danger pull-right">${o.price} ZŁ</h3>	
					<h3>${orderId }</h3>				
				</div>
	    	</section>
	    	
	    	<span class="clearfix borda"></span>
	    	<div class="hr-line-dashed"></div>
	    </c:forEach>
	    
	    <section class="col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
				<h3 class="text-uppercase pull-right">
				WARTOŚĆ ZAMÓWIENIA : <font size="12" class="text-danger">${sumTotal}</font>
				</h3>
		</section>
	  
	  <div class="col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
			<h2 class="text-danger text-center text-uppercase order-summary-adress">Dane do wysyłki</h2>
		
		<table class="table table-boardered table-striped">
			<tbody>
				<tr>
					<td>Imie</td>
					<td><c:choose>
							<c:when test="${profile.name == null}">
								brak
							</c:when>
							<c:otherwise>
								<c:out value="${profile.name}"></c:out>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td>Nazwisko</td>
					<td><c:choose>
							<c:when test="${profile.surname == null}">
								brak
							</c:when>
							<c:otherwise>
								<c:out value="${profile.surname}"></c:out>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td>Ulica</td>
					<td><c:choose>
							<c:when test="${profile.street == null}">
								brak
							</c:when>
							<c:otherwise>
								<c:out value="${profile.street}"></c:out>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</tbody>
		</table>
		<div class="text-center">
			<a href="${editProfileLink}" class="btn btn-danger" role="button">Edytuj
				dane</a>
		</div>
	</div>
	<section class="col-xs-12 col-sm-12 col-md-12">
		<a href="${payLink}" class="btn btn-lg btn-danger text-center btn-block order-summary-confirm">ZATWIERDZ I ZAPŁAĆ</a>
	</section>
</div>