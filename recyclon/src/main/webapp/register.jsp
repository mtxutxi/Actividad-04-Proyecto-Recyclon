<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>Nuevo Usuario</title>
</head>
<body>
	<div class="container mt-5">
		<div class="row text-center">
			<h1>Introduzca sus datos</h1>
		</div>
		<c:if test="${! empty mensajeBean.texto }">
			<div class="row">
				<div class="col-3"></div>
				<div class="col-6">
					<div class="alert ${mensajeBean.cssClass }">${mensajeBean.texto }</div>
				</div>
			</div>
		</c:if>
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<form action="users" method="post" enctype="multipart/form-data">
					<div class="mb-3">
						<label for="nombre" class="form-label">Nombre</label> 
						<input type="text" class="form-control"
							id="nombre" aria-describedby="emailHelp" name="nombre"
							value="${param.nombre }">
					</div>
					<div class="mb-3">
						<label for="apellidos" class="form-label">Apellidos</label> 
						<input type="text" class="form-control"
							id="apellidos" aria-describedby="emailHelp" name="apellidos"
							value="${param.apellidos}">
					</div>
					<div class="mb-3">
						<label for="exampleInputEmail1" class="form-label">Email
							address</label> <input type="email" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp" name="email"
							value="${param.email }">
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">Password</label>
						<input type="password" class="form-control"
							id="exampleInputPassword1" name="password">
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword2" class="form-label">Repita su contraseña</label>
						<input type="password" class="form-control"
							id="exampleInputPassword2" name="password2">
					</div>
					<button type="submit" class="btn btn-primary" name="accion" value="saveUser">Guardar</button>
					<button type="submit" class="btn btn-info" name="accion" value="cancelUser">Volver</button>
				</form>
			</div>
		</div>
	</div>
	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<!-- Option 2: Separate Popper and Bootstrap JS -->
	<!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    -->
</body>
</html>