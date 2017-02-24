<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagin" tagdir="/WEB-INF/tags"%>

<!-- CONTEXT ROOT VARIABLE -->
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<c:set var="searchUrl" value="${contextRoot}/search" />

<div class="container home">
	<div class="row">
		<!-- SIDE PANEL -->
		<div class="col-md-2">
			<div class="list-group">
				<label class="text-center list-group-item">Cena</label>
				<form:form action="${searchUrl}" modelAttribute="" method="get">
					<input type="number" class="form-control list-group-item" name="f" value="${priceFrom}"
						placeholder="cena od..."> <input type="number"
						class="form-control list-group-item" name="t" value="${priceTo}" placeholder="cena do..."> <span
						class="input-group-btn"> <input type="hidden" name="s"
						value="${search}">
						<button class="btn btn btn-danger btn-block" type="submit">Szukaj</button>
					</span>
				</form:form>
			</div>

		</div>

		<!-- MAIN SITE -->
		<div class="col-md-10 col-xs-12">
			<!-- 		PAGE HEADER -->
			<div class="page-header">
				<h1 class="text-danger text-center text-uppercase">
					<c:if test="${resultSize > 0}">
			Znaleziono <strong>${resultSize}</strong>
					</c:if>
					<c:choose>
						<c:when test="${resultSize == 1}">
				Wynik
			</c:when>
						<c:otherwise>
				Wyniki
			</c:otherwise>
					</c:choose>
					wyszukiwania <strong>"${search}"</strong>
				</h1>
				<h2 class="text-danger text-center text-uppercase">
					<c:if test="${not empty priceFrom}">
				Cena od ${priceFrom}
			</c:if>
					<c:if test="${not empty priceTo}">
			cena do ${priceTo}
			</c:if>
				</h2>
			</div>


			<c:if test="${empty page.content}">
				<div class="jumbotron jumbotron-home text-center">
					<h1 class="text-uppercase">Nic nie znaleziono</h1>

				</div>
			</c:if>

			<div class="pag">
				<pagin:customSearchPagin size="5" priceFrom="${priceFrom}" priceTo="${priceTo}" url="${searchUrl}" page="${page}" />
			</div>
	
<!-- 			<div class="pag"> -->
<%-- 				<pagin:pagination size="5" url="${searchUrl}" page="${page}" /> --%>
<!-- 			</div> -->

			<c:forEach var="result" items="${page.content}">

				<!-- 		PRODUCT  -->
				<!-- 			<div class="col-sm-4 col-sm-offset-0 text-center"> -->

				<%-- 				<a href="/product/${result.productId}" class="thumbnail"> <img src="/productphoto?id=${result.productId}" --%>
				<!-- 					alt="some name"> -->
				<!-- 				</a> -->
				<%-- 				<h3 class="text-uppercase">${result.name}</h3> --%>
				<!-- 				<h2 class="text-danger">TEST ZŁ</h2> -->
				<!-- 				<p>TEST</p> -->
				<!-- 			</div> -->

				<section class="col-xs-12 col-sm-6 col-md-12">
					<article class="search-result row">
						<div class="col-xs-12 col-sm-12 col-md-3">
							<a href="${contextRoot}/product/${result.productId}" title="Lorem ipsum"
								class="thumbnail"><img
								src="${contextRoot}/productphoto?id=${result.productId}" alt="Lorem ipsum" /></a>
						</div>
						<!-- 			<div class="col-xs-12 col-sm-12 col-md-2"> -->
						<!-- 				<ul class="meta-search"> -->
						<!-- 					<li><i class="glyphicon glyphicon-calendar"></i> <span>02/15/2014</span></li> -->
						<!-- 					<li><i class="glyphicon glyphicon-time"></i> <span>4:28 pm</span></li> -->
						<!-- 					<li><i class="glyphicon glyphicon-tags"></i> <span>People</span></li> -->
						<!-- 				</ul> -->
						<!-- 			</div> -->
						<div class="col-xs-12 col-sm-12 col-md-7 excerpet">
							<h2 class="text-uppercase">
								<a href="${contextRoot}/product/${result.productId}" title="">${result.name}</a>
							</h2>
							<p>${result.description}</p>
							<h2 class="text-danger">${result.price}ZŁ</h2>
						</div>
						<span class="clearfix borda"></span>
						<div class="hr-line-dashed"></div>
					</article>
				</section>

			</c:forEach>

			<div class="pag">
				<pagin:customSearchPagin size="5" priceFrom="${priceFrom}" priceTo="${priceTo}" url="${searchUrl}" page="${page}" />
			</div>
		</div>
	</div>




</div>