<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <script src="https://kit.fontawesome.com/f21170cf61.js" crossorigin="anonymous"></script>
    <title>Registro</title>
    <style>
        :root {
			--color-fondo: #EDE8DC;
			--verde-oscuro:#93C572 ;
			--verde-claro:rgba(147, 197, 114, 0.9);;
		}

		body{
		    background-color: var(--color-fondo);
		    margin:0 5px;
		    padding: 0;
		}
        
        .navbar{background-color: var(--verde-oscuro);}
    </style>
</head>
<body>
    <!-- BARRA DE NAVEGACION -->
    <nav class="navbar navbar-expand-lg fixed-top "> 
        <div class="container-fluid">
            <a class="navbar-brand text-light  ms-3" href="#"> 
                <i class="bi bi-leaf"></i> <span>Recyclon </span>
            </a> 
            <button class="navbar-toggler bg-light bg-opacity-75" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav ms-auto mb-2 mb-lg-0"> <!--ms-auto para desplazar-->
                  <li class="nav-item">
                      <a class="nav-link active text-light fw-bold" aria-current="page"  href="index.jsp">Registro <i class="bi bi-house-door-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link text-light" href="usuarios">Usuario <i class="bi bi-person-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link text-light" href="productos">Catálogo <i class="bi bi-book-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link text-light" href="pedidos">Pedidos <i class="bi bi-box-seam-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active text-light" href="cesta">Cesta <i class="bi bi-bag-fill"></i></a>
                  </li>
              </ul>  
              
          </div>
        </div>
    </nav>
	<div class="container mt-5 py-5">
		<div class="row text-center">
			<h1>Registro</h1>
		</div>
		<c:if test="${! empty mensajeCDI.message }">
			<div class="row">
				<div class="col-3"></div>
				<div class="col-6">
					<div class="alert ${mensajeCDI.role }">${mensajeCDI.message }</div>
				</div>
			</div>
		</c:if>
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<form action="usuarios" method="post">
					<div class="mb-3">
						<label for="nombre" class="form-label fw-bold">Nombre</label> 
						<input type="text" class="form-control"
							id="nombre" aria-describedby="emailHelp" name="nombre"
							value="${param.nombre }">
					</div>
					<div class="mb-3">
						<label for="apellidos" class="form-label fw-bold">Apellidos</label> 
						<input type="text" class="form-control"
							id="apellidos" aria-describedby="emailHelp" name="apellidos"
							value="${param.apellidos}">
					</div>
					<div class="mb-3">
						<label for="apellidos" class="form-label fw-bold">Teléfono</label> 
						<input type="text" class="form-control"
							id="tlfno" aria-describedby="emailHelp" name="tlfno"
							value="${param.tlfno}">
					</div>
					<div class="mb-3">
						<label for="apellidos" class="form-label fw-bold">Fecha de nacimiento</label> 
						<input type="text" class="form-control"
							id="fNacimiento" aria-describedby="emailHelp" name="fNacimiento"
							value="${param.fNacimiento}">
					</div>
					<div class="mb-3">
						<label for="apellidos" class="form-label fw-bold">DNI</label> 
						<input type="text" class="form-control"
							id="dni" aria-describedby="emailHelp" name="dni"
							value="${param.dni}">
					</div>
					<div class="mb-3">
						<label for="exampleInputEmail1" class="form-label fw-bold">E-mail</label> 
						<input type="email" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp" name="email"
							value="${param.email }">
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label fw-bold">Contraseña</label>
						<input type="password" class="form-control"
							id="exampleInputPassword1" name="contrasena1">
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword2" class="form-label fw-bold">Repita su contraseña</label>
						<input type="password" class="form-control"
							id="exampleInputPassword2" name="contrasena2">
					</div>
					<button type="submit" class="btn btn-success" name="accion" value="guardar">Guardar</button>
					<button type="submit" class="btn btn-outline-success" name="accion" value="cancelUser">Volver</button>
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