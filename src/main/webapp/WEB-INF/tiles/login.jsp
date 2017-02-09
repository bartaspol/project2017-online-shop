<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<c:url var="loginUrl" value="/login"/>

<div class="container">
	<div class="login-form">
		<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		
			<form class="form-signin" method="post" action="${loginUrl}">
				
<!-- 			TOKEN -->
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
				<h2 class="form-signin-heading text-center">Zaloguj się</h2>
				<a href="/register">Nie masz konta?</a> 
				
<!-- 				IF PASSWORD OR USERNAME IS INCORRECT -->
				<c:if test="${param.error != null }">
					<div class="login-error errors">
						Nieprawidłowa nazwa użytkownika lub hasło
					</div>
				</c:if>
				<input type="text" name="username" class="form-control" placeholder="Nazwa użytkownika" required autofocus /> 
				<input type="password" name="password" class="form-control" placeholder="Hasło" required />
				<button class="btn btn-lg btn-danger btn-block" type="submit">Zaloguj się</button>
			</form>
		</div>
	</div>
</div>