<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <script src="https://kit.fontawesome.com/f21170cf61.js" crossorigin="anonymous"></script>
    <title>Usuario</title>
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
                      <a class="nav-link active text-light" href="index.jsp">Inicio <i class="bi bi-house-door-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active text-light fw-bold" aria-current="page" href="controladorrecyclon">Usuario <i class="bi bi-person-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active text-light" href="productos">Catálogo <i class="bi bi-book-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active text-light" href="pedidos">Pedidos <i class="bi bi-box-seam-fill"></i></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active text-light" href="cesta">Cesta <i class="bi bi-bag-fill"></i></a>
                  </li>
              </ul>  
              
          </div>
         </div>
    </nav>   

    <!-- ACORDEON -->
     
    <div class="container-fluid  justify-content-center align-items-center mt-5">
       <div class="col-8 offset-2 p-5">
       			<h1 class="text-center">Hola, ${usuario.nombre} ${usuario.apellidos}</h1>
                <div class="accordion mt-4" id="accordionUser">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="cabeceraAcordeon">
                            <button class="accordion-button fw-bold" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapse1" aria-expanded="true" aria-controls="collapseOne">
                                Tus datos
                            </button>
                        </h2>
                        <div id="collapse1" class="accordion-collapse collapse show" aria-labelledby="heading1"
                            data-bs-parent="#accordionUser">
                            <div class="accordion-body">
                            	<form class="row">
				                    <div class="col-3 mt-2">
				                        <label for="Nombre" class="form-label">Nombre</label>
				                        <input type="text" class="form-control bg-white" id="Nombre" value="${usuario.nombre}" disabled>
				                    </div>
				                    <div class="col-4 mt-2">
				                        <label for="Apellido" class="form-label">Apellidos</label>
				                        <input type="text" class="form-control  bg-white" id="Apellido" value="${usuario.apellidos}" disabled>
				                    </div>
				                    <div class="col-3 mt-2">
				                        <label for="fnacimiento" class="form-label">Documento identidad</label>
				                        <input type="text" class="form-control  bg-white" id="fnacimiento" value="${usuario.documentoIdentidad}" disabled>
				                    </div>
				                    <div class="col-2 mt-2">
				                        <label for="dni" class="form-label">Fecha Nacimiento</label>
				                        <input type="text" class="form-control  bg-white" id="dni" value="${usuario.fechaNacimiento}" disabled>
				                    </div>
				                    <div class="col-4 mt-4">
				                        <label for="email" class="form-label">E-mail</label>
				                        <input type="text" class="form-control  bg-white" id="email" value="${usuario.email}" disabled>
				                    </div>
				                    <div class="col-4 mt-4">
				                        <label for="envio" class="form-label">Dirección de Envio</label>
				                        <input type="text" class="form-control  bg-white" id="envio" value="${usuario.direccionEnvio}" disabled>
				                    </div>
				                    <div class="col-4 mt-4">
				                        <label for="facturacion" class="form-label">Dirección de Facturación</label>
				                        <input type="text" class="form-control  bg-white" id="facturacion" value="${usuario.direccionFacturacion}" disabled>
				                    </div>
				                    <div class="col-12 mt-4">
				                        <a href="pedidos?accion=verPedidosUsuario" class="btn btn-outline-success rounded" ROLE="button">Ver Pedidos</a>
				                    </div>
				                </form>
            				</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

</body>
</html>