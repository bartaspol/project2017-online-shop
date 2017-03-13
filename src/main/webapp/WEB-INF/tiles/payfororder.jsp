<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1">
	<div class="jumbotron text-center">
		<h1>TU POWINNO NASTĄPIĆ PRZENIESIENIE DO JAKIEJS METODY PLATNOŚCI</h1>
	</div>
	<h1 class="text-danger text-center text-uppercase">
		Dziękujemy za zakupy
		<c:choose>
			<c:when test="${profile.name == null || fn:length(profile.name) < 1}">
							${userEmail}
						</c:when>
			<c:otherwise>
				<c:out value="${profile.name}"></c:out>
			</c:otherwise>
		</c:choose>
	</h1>
</div>
