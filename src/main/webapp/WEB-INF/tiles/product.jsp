<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container">
	<div class="page-header">
		<h1 class="text-danger text-center text-uppercase">${product.name}</h1>
	</div>
	<div class="col-md-8 col-sm-8 ">
		
		<div class="text-center">
				<a class="thumbnail" href="/product/${product.id}"> <img
					 src="/productphoto?id=${product.id}" alt="some name">
				</a>
			<p>Opis produktu:</p>
			<p>${product.description}</p>


		</div>
	</div>
	
	
	<div class="col-md-4 col-sm-4 ">
		<div class="thumbnail text-center">
			<h2>${product.price} ZŁ</h2>
			<a href="#" class="btn btn-danger" role="button">KUP TERAZ</a>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Właściwości techniczne:</h3>
			</div>
			
			<table class="table table-boardered table-striped">
				<tbody>
					<tr>
						<td>Marka</td>
						<td>jakas tam</td>
					</tr>
					<tr>
						<td>Wlsciwosc</td>
						<td>opis</td>
					</tr>
					<tr>
						<td>Wlsciwosc</td>
						<td>opis</td>
					</tr>
					<tr>
						<td>Wlsciwosc</td>
						<td>opis</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	
	
</div>