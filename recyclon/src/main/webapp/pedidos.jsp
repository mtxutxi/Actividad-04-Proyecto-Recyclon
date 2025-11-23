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
    <%@ include file="navbar.jsp" %>

    
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
                    			<td colspan="5" class="p-4">
                    		</c:when>
                    		<c:otherwise>
                    			<td colspan="4" class="p-4">
                    		</c:otherwise>
                    	</c:choose>
                                <div class="container-fluid">
                                    <h6 class="mb-3">Productos del Pedido: #${pedido.idPedido}</h6>
                                    <c:if test="${not empty pedido.lineasPedido}">
                                    	<table class="table table-sm table-bordered">
            								<thead class="table-light">
            									<tr>
            										<th>ID</th>
            										<th>Producto</th>
            										<th>Precio</th>
            										<th>Cantidad</th>
            									</tr>
            								</thead>
            								<tbody>
                                            <c:forEach var="linea" items="${pedido.lineasPedido}">
                                                <tr>
            										<td>${linea.producto.idProducto}</td>
            										<td>${linea.producto.nombre}</td>
            										<td><fmt:formatNumber value="${linea.producto.precio}" type="currency" currencySymbol="€"/></td>
            										<td>${linea.cantidad}</td>
            									</tr>
                                            </c:forEach>
                                          </tbody>
                                        </table>
                                    </c:if>
                                    <c:if test="${empty pedido.lineasPedido}">
                                        <p class="text-muted">Sin productos</p>
                                    </c:if>
                                    
                                    <c:if test="${sessionScope.usuario.isAdmin}">
                                        <hr>
                                        <form method="post" action="pedidos" class="row g-3 align-items-center mt-2">
                                            <input type="hidden" name="accion" value="cambiarEstado">
                                            <input type="hidden" name="idPedido" value="${pedido.idPedido}">
                                            
                                            <div class="col-auto">
                                                <label for="estado${pedido.idPedido}" class="col-form-label fw-bold">
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
                                                <button type="submit" class="btn btn-sm btn-success">
                                                   Actualizar
                                                </button>
                                            </div>
                                        </form>
                                    </c:if>
                                </div>
                            </td>
                        </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>