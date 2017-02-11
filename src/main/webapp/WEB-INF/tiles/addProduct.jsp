<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="container">
	<div class="login-form">
		<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">


			<h2 class="form-signin-heading text-center">Dodaj produkt</h2>
			

			<br>

			<!-- 			CREATE FORM -->
			<form:form class="form-signin" modelAttribute="product" enctype="multipart/form-data">
			
			<!-- 		ADD IMAGE-->
			<input type="file" accept="image/*" name="file"/><br>

				<!-- 					add product name -->
				<div class="errors">
					<form:errors path="name" />
				</div>
				<label>Nazwa:</label>
				<form:input path="name" class="form-control"
					placeholder="Nazwa produktu" />

				<!-- 						 choose category -->
				<!-- 				<div class="form-group"> -->
				<!-- 					<label for="productCategory">Wybierz kategorię produktu:</label> <select -->
				<!-- 						class="form-control" name="productCategory" id="productCategory"> -->
				<!-- 						<option>krem</option> -->
				<!-- 						<option>perfum</option> -->
				<!-- 					</select> -->
				<!-- 				</div> -->

				<!-- 					add description -->
				<div class="form-group">
					<label for="productDesc">Opis:</label>
					<div class="errors">
						<form:errors path="description" />
					</div>
					<form:textarea class="form-control" path="description" rows="5"
						name="productDesc" maxlength="5000" placeholder="Opis produktu..."></form:textarea>
					<!-- 					<span id="chars">Maksymalnie 250 znakow</span> -->
				</div>

<!-- 									add price  -->
				<label>Podaj cenę:</label>
				<input type="range" name="productPriceSlider" min="0" max="2000" value="500" step="0.1" onchange="updateProuctPriceInput(this.value);" />
				<form:input class="form-control" path="price" type="number" step="0.1" name="productPrice" id="productPrice" value=""/>
				<br>


				<button class="btn btn-lg btn-danger btn-block" type="submit">Dodaj
					produkt</button>
			</form:form>
		</div>
	</div>
</div>

<script>
	function updateProuctPriceInput(newValue)
	{
		document.getElementById("productPrice").value=newValue;
	}
</script>