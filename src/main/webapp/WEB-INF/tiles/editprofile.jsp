<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="container">
	<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="text-center">
			<div class="page-header">
				<h1 class="text-danger text-center text-uppercase">Edytuj dane do wysyłki</h1>
			</div>
			<h2>
				<c:choose>
					<c:when test="${profile.name == null || fn:length(profile.name) < 1}">
						${userEmail}
					</c:when>
					<c:otherwise>
						<c:out value="${profile.name}"></c:out>
					</c:otherwise>
				</c:choose>
			</h2>
			<h3>${profile.about}</h3>
		</div>
		<div class="errors">
			<form:errors path="profile.*" />
		</div>

		<form:form class="form-signin" modelAttribute="profile">
			
			<label>Imię:</label>
			<form:input path="name" class="form-control" placeholder="Imię" />
			<br>
			
			<label>Nazwisko:</label>
			<form:input path="surname" class="form-control" placeholder="Nazwisko" />
			<br>

			<label>Ulica:</label>
			<form:input path="street" class="form-control" placeholder="Nazwa ulicy" />
			<br>
			<button class="btn btn-lg btn-danger btn-block" type="submit">Zachowaj
				zmiany</button>
		</form:form>
	</div>
</div>