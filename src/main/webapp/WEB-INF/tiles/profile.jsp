<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- VARIABLES -->
<c:url var="editProfileLink" value="${contextRoot}/editprofile" />
<c:url var="editAboutLink" value="${contextRoot}/editabout" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


<div class="container">
		<div class="jumbotron text-center">
			<div class="profile">
				<h1 class="text-uppercase">
					Witaj
					<c:choose>
						<c:when
							test="${profile.name == null || fn:length(profile.name) < 1}">
							${userEmail}
						</c:when>
						<c:otherwise>
							<c:out value="${profile.name}"></c:out>
						</c:otherwise>
					</c:choose>
				</h1>
				<div class="thumbnail text-center">
					<h2>O mnie:</h2>
					<div class="profile-about">
						<c:choose>
							<c:when test="${profile.about == null}">
						Dodaj jakies informacje
					</c:when>
							<c:otherwise>
								<c:out value="${profile.about}"></c:out>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="pull-right edit-about-button">
						<a href="${editAboutLink}">Edytuj opis</a>
					</div>
				</div>
			</div>
		</div>
	<!-- JUMBOTRON ENDS -->



	<div class="col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
		<div class="page-header text-center">
			<h1 class="text-danger text-center text-uppercase">Dane do
				wysyłki</h1>
		</div>
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
</div>
