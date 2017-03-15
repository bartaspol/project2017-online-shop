<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!-- CONTEXT ROOT VARIABLE -->
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<c:url var="orderSummaryLink" value="ordersummary"/>
    
    <div class="container home">
    
    	<c:choose>
    		<c:when test="${fn:length(order) > 0}">
    			<div class="page-header">
					<h1 class="text-danger text-center text-uppercase">TWOJE ZAMÓWIENIE</h1>
				</div>
			</c:when>
    		
    		<c:otherwise>
    			<div class="jumbotron text-center">
					<h1>BRAK PRODUKTÓW W KOSZYKU</h1>
					<h3 class="text-danger"><a href="${contextRoot}/"> wróć do strony głównej</a></h3>
				</div>
    		</c:otherwise>
    	</c:choose>
	    
		
	    <c:forEach var="o" items="${order}">
	    
	    	<section class="col-xs-12 col-sm-12 col-md-12">
	    		<div class="col-xs-12 col-sm-3 col-md-2">
					<a href="${contextRoot}/product/${o.id}" title="Lorem ipsum" class="image-order">
						<img src="${contextRoot}/productphoto?id=${o.id}" alt="Lorem ipsum" />
					</a>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-7 excerpet">
					<h2 class="text-uppercase"><a href="${contextRoot}/product/${o.id}" title=""><strong>${o.name}</strong></a></h2>
					<h3 class="text-danger">${o.price} ZŁ</h3>	
<%-- 					<h3>${orderId }</h3>				 --%>
				</div>
				<div class="text-center">
						<a onclick="return confirm('Usunąć produkt z listy zamówienia?')" 
							href="deleteFromOrder?pid=${o.id}" class="btn btn-danger">Usuń z zamówienia</a> <br> <br>
				</div>
	    	</section>
	    	
	    	<span class="clearfix borda"></span>
	    	<div class="hr-line-dashed"></div>
	    </c:forEach>
	    
	    <section class="col-xs-12 col-sm-12 col-md-12">
		    <h3 class="text-uppercase pull-right">
					WARTOŚĆ ZAMÓWIENIA : <font size="12" class="text-danger">${sumTotal}</font>
			</h3>
		</section>
	    
	    
	    <section class="col-xs-12 col-sm-12 col-md-12">
		    <c:if test="${fn:length(order) > 0}">
			    <div class="text-center">
					<a href="${orderSummaryLink}" class="btn btn-lg btn-danger btn-block">ZŁÓŻ ZAMÓWIENIE</a>
				</div>
		    </c:if>
	    </section>
	    
	</div>