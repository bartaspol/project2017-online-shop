<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="container home">
	<div class="login-form">
	<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="page-header">
			<h1 class="text-danger text-center text-uppercase">Edytuj OMNIE:</h1>
		</div>

		<!-- 		FORM -->
		<form:form class="form-signin" modelAttribute="profile">
			<!-- 			EDIT ABOUT PROFILE -->
			<div class="form-group">
				<label for="productDesc">O Tobie:</label>
				<div class="errors">
					<form:errors path="about" />
				</div>

				<form:textarea class="form-control" path="about" placeholder="Napisz coś o sobie..." maxlength="501"
					rows="5" />
			</div>
			
			<br>
				<button class="btn btn-lg btn-danger btn-block" type="submit">Zachowaj zmiany</button>
		</form:form>

		</div>
	</div>
</div>