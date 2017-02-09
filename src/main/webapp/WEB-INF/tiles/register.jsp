<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="register-form">
		<div class="container">
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			
				<h2 class="form-signin-heading text-center">Zarejestruj się</h2>
<!-- 				SHOW ERRORS -->
				<div class="errors">
					<form:errors path="user.*"/>
				</div>
<!-- 			REGISTRATION FORM -->
				<form:form class="form-signin" method="post" modelAttribute="user">
					<form:input path="email" type="email" class="form-control"
						placeholder="Email"/> 
					 
					<form:input path="plainPassword" type="password" class="form-control"
						placeholder="Hasło"/>
						
 					<form:input path="repeatPassword" type="password" class="form-control"
 						placeholder="Powtorz hasło"/>
						
					<button class="btn btn-lg btn-danger btn-block" type="submit">Zarejestruj się</button>
				</form:form>
			</div>
		</div>
	</div>
</div>