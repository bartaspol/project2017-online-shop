<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page" %>
<%@ attribute name="url" required="true"%>

<!-- number of page numbers to display in results -->
<%@ attribute name="size" required="false"%>
<%@ attribute name="priceFrom" required="true"%>
<%@ attribute name="priceTo" required="true"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<%-- <c:set var="priceFrom" value="${empty param.f ? 0 : param.f}"></c:set> --%>
<%-- <c:set var="priceTo" value="${empty param.t ? 1000000 : param.t}"></c:set> --%>
<c:set var="search" value="${empty param.s ? 0 : param.s}"></c:set>
<c:set var="size" value="${empty size ? 10 : size}"></c:set>
<c:set var="block" value="${empty param.b ? 0 : param.b}"></c:set>
<c:set var="startPage" value="${block * size + 1}"></c:set>
<c:set var="endPage" value="${(block + 1) * size}"></c:set>
<c:set var="endPage" value="${endPage > page.totalPages ? page.totalPages : endPage}"></c:set>

<c:if test="${page.totalPages != 1}">
	<div class="pagination">
	

	
	
	<c:choose>
		<c:when test="${empty search}">
			<c:if test="${block != 0}">
				<a href="${url}?b=${block - 1}&p=${(block - 1) * size + 1}">&lt;&lt;</a>
			</c:if>
		</c:when>
		<c:otherwise>
			<c:if test="${block != 0}">
				<a href="${url}?s=${search}&b=${block - 1}&p=${(block - 1) * size + 1}">&lt;&lt;</a>
			</c:if>
		</c:otherwise>
	</c:choose>
	
	
		<c:forEach var="pageNumber" begin="${startPage}" end="${endPage}">
			<c:choose>
			
				<c:when test="${page.number != pageNumber - 1}">
					<a href="${url}?s=${search}&p=${pageNumber}&b=${block}&f=${priceFrom}&t=${priceTo}"> <c:out
							value="${pageNumber}" />
					</a>
				</c:when>
				
				<c:otherwise>
					<strong><c:out value="${pageNumber}" /></strong>
				</c:otherwise>
				
			</c:choose>
	
			<c:if test="${pageNumber != endPage}">
		 		|
		 		</c:if>
	
		</c:forEach>
		
		<c:if test="${endPage != page.totalPages}">
			<a href="${url}?s=${search}&b=${block + 1}&p=${(block + 1) * size + 1}&f=${priceFrom}&t=${priceTo}">&gt;&gt;</a>
		</c:if>
	</div>
</c:if>