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
    <%@ include file="navbar.jsp" %>
  

    <!-- ACORDEON -->
     
    <div class="container-fluid  justify-content-center align-items-center mt-5">
       <div class="col-8 offset-2 p-5">
                <div class="accordion mt-5" id="accordionUser">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="cabeceraAcordeon">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapse1" aria-expanded="true" aria-controls="collapseOne">
                                Datos Usuario ${usuario.idUsuario}
                            </button>
                        </h2>
                        <div id="collapse1" class="accordion-collapse collapse show" aria-labelledby="heading1"
                            data-bs-parent="#accordionUser">
                            <div class="accordion-body">
                                <ul  class="list-unstyled">
                                    <li>Nombre: ${usuario.nombre}</li>
                                    <li>Apellidos: ${usuario.apellidos}</li>
                                    <li>Telefono: ${usuario.telefono}</li>
                                    <li>Fecha de Nacimiento: ${usuario.fechaNacimiento}</li>
                                    <li>Documento identidad: ${usuario.documentoIdentidad}</li>
                                    <li>Correo electronico: ${usuario.email}</li>
                                    <li>Dirección de envio: ${usuario.direccionEnvio}</li>
                                    <li>Dirección de Facturación: ${usuario.direccionFacturacion}</li>
                                 </ul>
            				</div>
                        </div>
                    </div>
                    <div class="container-fluid  justify-content-center align-items-center mt-5">
                    	<a class="btn btn-outline-success rounded" href="pedidos?accion=verPedidosUsuario">Ver Pedidos</a>
                    </div>
                </div>
            </div>
        </div>    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

</body>
</html>