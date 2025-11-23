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
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-leaf" viewBox="0 0 16 16">
  					<path d="M1.4 1.7c.216.289.65.84 1.725 1.274 1.093.44 2.884.774 5.834.528l.37-.023c1.823-.06 3.117.598 3.956 1.579C14.16 6.082 14.5 7.41 14.5 8.5c0 .58-.032 1.285-.229 1.997q.198.248.382.54c.756 1.2 1.19 2.563 1.348 3.966a1 1 0 0 1-1.98.198c-.13-.97-.397-1.913-.868-2.77C12.173 13.386 10.565 14 8 14c-1.854 0-3.32-.544-4.45-1.435-1.125-.887-1.89-2.095-2.391-3.383C.16 6.62.16 3.646.509 1.902L.73.806zm-.05 1.39c-.146 1.609-.008 3.809.74 5.728.457 1.17 1.13 2.213 2.079 2.961.942.744 2.185 1.22 3.83 1.221 2.588 0 3.91-.66 4.609-1.445-1.789-2.46-4.121-1.213-6.342-2.68-.74-.488-1.735-1.323-1.844-2.308-.023-.214.237-.274.38-.112 1.4 1.6 3.573 1.757 5.59 2.045 1.227.215 2.21.526 3.033 1.158.058-.39.075-.782.075-1.158 0-.91-.288-1.988-.975-2.792-.626-.732-1.622-1.281-3.167-1.229l-.316.02c-3.05.253-5.01-.08-6.291-.598a5.3 5.3 0 0 1-1.4-.811"/>
				</svg> 
                <span>Recyclon </span>
            </a> 
            <button class="navbar-toggler bg-light bg-opacity-75" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav ms-auto mb-2 mb-lg-0"> <!--ms-auto para desplazar-->
                  <li class="nav-item">
                      <a class="nav-link active text-light fw-bold" aria-current="page"  href="index.jsp">Registro</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link text-light" href="usuarios">Usuario</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link text-light" href="productos">Productos</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link text-light" href="pedidos">Pedidos</a>
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