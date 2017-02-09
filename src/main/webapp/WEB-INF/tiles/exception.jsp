<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1">
	<div class="jumbotron text-center">
		<div class="message">
			<h1>
				<c:out value="${message}" />
			</h1>
		</div>
	</div>
	
	<!--  
		Exception : <c:out value="${exception}" />
		Failed URL : <c:out value="${url}" />
		Exception message : <c:out value="${exception.message}" />
		<c:forEach var="line" items="${exception.stackTrace}">
			<c:out value="${line}" />
		</c:forEach>
	-->
</div> 