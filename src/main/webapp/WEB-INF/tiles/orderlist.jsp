<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- CONTEXT ROOT VARIABLE -->
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<c:url var="deleteFromOrderButtonLink" value=""/>
    
    <div class="container home">
	    <div class="page-header">
			<h1 class="text-danger text-center text-uppercase">TWOJE ZAMÓWIENIE</h1>
		</div>
	    <c:forEach var="o" items="${order}">
	    
	    	<section class="col-xs-12 col-sm-6 col-md-12">
	    		<div class="col-xs-12 col-sm-12 col-md-2">
					<a href="${contextRoot}/product/${o.id}" title="Lorem ipsum" class="image-order">
						<img src="${contextRoot}/productphoto?id=${o.id}" alt="Lorem ipsum" />
					</a>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-7 excerpet">
					<h2 class="text-uppercase"><a href="${contextRoot}/product/${o.id}" title=""><strong>${o.name}</strong></a></h2>
					<h3 class="text-danger">${o.price} ZŁ</h3>	
					<h3>${orderId }</h3>				
				</div>
				<div class="text-center">
						<a onclick="return confirm('Usunąć produkt z listy zamówienia?')" 
							href="deleteFromOrder?pid=${o.id}" class="btn btn-danger">Usuń z zamówienia</a> <br> <br>
					</div>
	    	</section>
	    	
	    	<span class="clearfix borda"></span>
	    	<div class="hr-line-dashed"></div>
	    </c:forEach>
	</div>