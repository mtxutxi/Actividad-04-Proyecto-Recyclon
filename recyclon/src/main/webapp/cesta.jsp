<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <script src="https://kit.fontawesome.com/f21170cf61.js" crossorigin="anonymous"></script>
    <title>Mi Cesta</title>
    <style>
        :root {
            --color-fondo: #EDE8DC;
            --verde-oscuro:#93C572;
            --verde-claro:rgba(147, 197, 114, 0.9);
        }

        body {
            background-color: var(--color-fondo);
            margin: 0;
            padding: 0;
            padding-top: 70px;
        }

        .navbar {
            background-color: var(--verde-oscuro);
        }

        .btn-recyclon {
            background-color: var(--verde-oscuro);
            border-color: var(--verde-oscuro);
            color: white;
        }

        .btn-recyclon:hover {
            background-color: #7fb05e;
            border-color: #7fb05e;
            color: white;
        }

        .producto-imagen {
            max-height: 500px;
            object-fit: contain;
            width: 100%;
        }
    </style>
</head>
<body>
    <!-- BARRA DE NAVEGACION  -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand text-light ms-3" href="index.jsp">
                <i class="bi bi-leaf"></i> <span>Recyclon </span>
            </a>
            <button class="navbar-toggler bg-light bg-opacity-75" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
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
                  	  <a class="nav-link active text-light fw-bold" aria-current="page" href="productos">Catálogo <i class="bi bi-book-fill"></i></a>
                  </li>
                  <li class="nav-item">
                  	  <a class="nav-link text-light" href="pedidos">Pedidos <i class="bi bi-box-seam-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active text-light" href="productos?accion=vercarrito">Cesta <i class="bi bi-bag-fill"></i></a>
                  </li>
              </ul>
            </div>
        </div>
    </nav>
    
    <div class="container mt-5">
        <h1 class="mb-4"><i class="bi bi-cart3"></i> Mi Cesta</h1>
        
        <!-- Mensajes -->
        <c:if test="${not empty mensajeCDI.message}">
            <div class="alert ${mensajeCDI.role} alert-dismissible fade show" role="alert">
                ${mensajeCDI.message}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>
        
        <!-- si el carrito está vacío -->
        <c:if test="${cesta.estaVacia()}">
            <div class="alert alert-info">
                <i class="bi bi-exclamation-circle"></i> Tu carrito está vacío
            </div>
            <a href="productos" class="btn btn-success">
             Volver
            </a>
        </c:if>
        
        <!-- si hay productos en el carrito -->
        <c:if test="${not cesta.estaVacia()}">
            <div class="row">
                <div class="col-lg-8">
                    <div class="card">
                        <div class="card-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Producto</th>
                                        <th>Precio</th>
                                        <th>Cantidad</th>
                                        <th>Subtotal</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="total" value="0" />
                                    <c:forEach items="${productosCarrito}" var="item">
                                        <c:set var="subtotal" value="${item.producto.precio * item.cantidad}" />
                                        <c:set var="total" value="${total + subtotal}" />
                                        <tr>
                                            <td>
                                                <div class="d-flex align-items-center">
                                                    <img src="imagenes/${item.producto.imagen}" 
                                                         alt="${item.producto.nombre}" 
                                                         class="img-thumbnail me-3" 
                                                         style="width: 80px; height: 80px; object-fit: cover;">
                                                    <div>
                                                        <h6 class="mb-0">${item.producto.nombre}</h6>
                                                        <small class="text-muted">${item.producto.categoria.tipo}</small>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="align-middle">
                                                ${item.producto.precio}€
                                            </td>
                                            <td class="align-middle">
                                                ${item.cantidad}
                                            </td>
                                            <td class="align-middle">
                                                <strong>${subtotal}€</strong>
                                            </td>
                                            <td class="align-middle">
                                                <a href="productos?accion=eliminardelcarrito&id=${item.producto.idProducto}" 
                                                   class="btn btn-sm btn-danger"
                                                   onclick="return confirm('¿Eliminar este producto del carrito?')">
                                                    <i class="bi bi-trash"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Resumen del pedido</h5>
                            <hr>
                            <div class="d-flex justify-content-between mb-2">
                                <span>Productos (${cesta.totalProductos}):</span>
                                <span>${total}€</span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span>Envío:</span>
                                <span>Gratis</span>
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-3">
                                <strong>Total:</strong>
                                <strong>${total}€</strong>
                            </div>
                            <!-- ESTE METODO ES POST NO LLAMAR AL CREAR PEDIDO -->
							<form method="post" action="pedidos">
							    <input type="hidden" name="accion" value="finalizarpedido">
							    <button type="submit" class="btn btn-success w-100 mb-2">
							        <i class="bi bi-check-circle"></i> Finalizar Pedido
							    </button>
							</form>
                            <a href="productos" class="btn btn-outline-success w-100">
                                < Seguir Comprando
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>