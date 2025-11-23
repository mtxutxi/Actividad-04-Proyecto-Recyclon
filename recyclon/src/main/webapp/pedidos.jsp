<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <script src="https://kit.fontawesome.com/f21170cf61.js" crossorigin="anonymous"></script>
    <title>Pedidos</title>
    <style>
        :root {
			--color-fondo: #EDE8DC;
			--verde-oscuro:#93C572;
			--verde-claro:rgba(147, 197, 114, 0.9);
		}

		body{
		    background-color: var(--color-fondo);
		    margin:0 5px;
		    padding: 0;
		    padding-top: 70px; /* para que se me vea el titulo*/
		}
        
        .navbar{background-color: var(--verde-oscuro);}
    </style>
</head>
<body>
    <!-- BARRA DE NAVEGACION -->
    <nav class="navbar navbar-expand-lg fixed-top"> 
        <div class="container-fluid">
            <a class="navbar-brand text-light ms-3" href="index.jsp"> 
                <i class="bi bi-leaf"></i> <span>Recyclon </span>
            </a> 
            <button class="navbar-toggler bg-light bg-opacity-75" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                      <a class="nav-link text-light" href="index.jsp">Inicio <i class="bi bi-house-door-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link text-light" href="controladorrecyclon">Usuario <i class="bi bi-person-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active text-light" href="productos">Catálogo <i class="bi bi-book-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active text-light fw-bold" aria-current="page" href="pedidos">Pedidos <i class="bi bi-box-seam-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active text-light" href="cesta">Cesta <i class="bi bi-bag-fill"></i></a>
                  </li>
              </ul>  
              
              
          </div>
        </div>
    </nav>
    
<div class="container mt-4">
    <h2 class="mb-4 text-center">
    	<c:choose>
    		<c:when test="${sessionScope.usuario.isAdmin}">
    			Pedidos
    		</c:when>
    		<c:otherwise>
    			Mis Pedidos
    		</c:otherwise>
    	</c:choose>
    </h2>

    <!-- si NO hay pedidos -->
    <c:if test="${empty pedidos}">
        <div class="alert alert-info text-center">
            No hay ningún pedido
        </div>
    </c:if>

    <!-- si hay pedidos -->
    <c:if test="${not empty pedidos}"> 
        <table class="table table-striped table-bordered text-center">
            <thead class="table-success">
                <tr>
                    <th>ID</th>
                    <th>Fecha</th>
                    <c:if test="${sessionScope.usuario.isAdmin}">
                    	<th>Usuario</th>
                    </c:if>
                    <th>Estado</th>
                    <th>Detalles</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="pedido" items="${pedidos}">
                    <tr>
                        <th>#${pedido.idPedido}</th>
                        <td><fmt:formatDate value="${pedido.fecha}" pattern="dd/MM/yyyy"/></td>
                        <c:if test="${sessionScope.usuario.isAdmin}">
                    		<td>${pedido.usuario.nombre} ${pedido.usuario.apellidos}</td>
                    	</c:if>
                        <td>
                            <c:choose>
                                <c:when test="${pedido.estado == 'Pendiente'}">
                                    <p><span class="badge bg-danger">${pedido.estado}</span><p>
                                </c:when>
                                <c:when test="${pedido.estado == 'Enviado'}">
                                    <p><span class="badge bg-primary">${pedido.estado}</span></p>
                                </c:when>
                                <c:when test="${pedido.estado == 'Entregado'}">
                                    <p><span class="badge bg-success">${pedido.estado}</span><p>
                                </c:when>
                                <c:otherwise>
                                    <p><span class="badge bg-secondary">${pedido.estado}</span></p>
                                </c:otherwise>
                            </c:choose>
                        </td>                        
                        <td>
                            <button class="btn btn-outline-success btn-sm" type="button" 
                                    data-bs-toggle="collapse" data-bs-target="#detalle${pedido.idPedido}">
                                <i class="bi bi-eye"></i> Ver Detalles
                            </button>
                        </td>	
                    </tr>
        <!-- collapse -->
        <tr class="collapse" id="detalle${pedido.idPedido}">
        	<c:choose>
        		<c:when test="${sessionScope.usuario.isAdmin}">
        			<td colspan="5">
        		</c:when>
        		<c:otherwise>
        			<td colspan="4">
        		</c:otherwise>
        	</c:choose>
                    <h6>Productos del Pedido: #${pedido.idPedido}</h6>
                    <c:if test="${not empty pedido.lineasPedido}">
                    	<table class="table">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Producto</th>
									<th scope="col">Precio</th>
									<th scope="col">Cantidad</th>
								</tr>
							</thead>
							<tbody>
                            <c:forEach var="linea" items="${pedido.lineasPedido}">
                                <tr>
									<th scope="row">${linea.producto.idProducto}</th>
									<td>${linea.producto.nombre}</td>
									<td><fmt:formatNumber value="${linea.producto.precio}" type="currency" currencySymbol="€"/></td>
									<td>${linea.cantidad}</td>
								</tr>
                            </c:forEach>
                          </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty pedido.lineasPedido}">
                        <p>Sin productos</p>
                    </c:if>
                    
                    <!-- formulario cambiar estado (solo admin) -->
                    <c:if test="${sessionScope.usuario.isAdmin}">
                        <hr>
                        <form method="post" action="pedidos" class="row g-3 align-items-center">
                            <input type="hidden" name="accion" value="cambiarEstado">
                            <input type="hidden" name="idPedido" value="${pedido.idPedido}">
                            
                            <div class="col-auto">
                                <label for="estado${pedido.idPedido}" class="col-form-label">
                                 Cambiar estado:
                                </label>
                            </div>
                            <div class="col-auto">
	                                <select class="form-select form-select-sm" id="estado${pedido.idPedido}" name="estado">
	                                    <option value="Pendiente" ${pedido.estado == 'Pendiente' ? 'selected' : ''}>Pendiente</option>
	                                    <option value="Enviado" ${pedido.estado == 'Enviado' ? 'selected' : ''}>Enviado</option>
	                                    <option value="Entregado" ${pedido.estado == 'Entregado' ? 'selected' : ''}>Entregado</option>
	                                    <option value="Cancelado" ${pedido.estado == 'Cancelado' ? 'selected' : ''}>Cancelado</option>
	                                </select>
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-sm bg-success text-white">
                                 Actualizar
                                </button>
                               </div>
                               </form>
                               </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>