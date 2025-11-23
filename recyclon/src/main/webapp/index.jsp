<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <script src="https://kit.fontawesome.com/f21170cf61.js" crossorigin="anonymous"></script>
    <title>Inicio sesión</title>
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
    <nav class="navbar navbar-expand-lg fixed-top"> 
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
                      <a class="nav-link active text-light fw-bold" aria-current="page" href="index.jsp">Inicio sesión <i class="bi bi-house-door-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link text-light" href="controladorrecyclon">Usuario <i class="bi bi-person-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link text-light" href="productos">Catálogo <i class="bi bi-book-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link text-light" href="pedidos">Pedidos</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active text-light" href="cesta">Cesta <i class="bi bi-bag-fill"></i></a>
                  </li>
              </ul>  
              
          </div>
        </div>
    </nav>
    
    <div class="container mt-5 pt-5">
		<div class="row text-center">
			<h1>Inicio sesión</h1>
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
						<label for="exampleInputEmail1" class="form-label fw-bold">E-mail</label> <input type="text" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp" name="email"
							value="${param.email }">
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label fw-bold">Contraseña</label>
						<input type="password" class="form-control"
							id="exampleInputPassword1" name="password">
					</div>
					<button type="submit" class="btn btn-success" name="accion" value="login">Entrar</button>
					<button type="submit" class="btn btn-outline-success" name="accion" value="registro">Registrarse</button>
				</form>
			</div>
		</div>
	</div>
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

</body>
</html>