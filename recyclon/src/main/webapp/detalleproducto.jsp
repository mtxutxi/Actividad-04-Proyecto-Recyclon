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
    <title>Recyclon - ${producto.nombre}</title>
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
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-leaf" viewBox="0 0 16 16">
                    <path d="M1.4 1.7c.216.289.65.84 1.725 1.274 1.093.44 2.884.774 5.834.528l.37-.023c1.823-.06 3.117.598 3.956 1.579C14.16 6.082 14.5 7.41 14.5 8.5c0 .58-.032 1.285-.229 1.997q.198.248.382.54c.756 1.2 1.19 2.563 1.348 3.966a1 1 0 0 1-1.98.198c-.13-.97-.397-1.913-.868-2.77C12.173 13.386 10.565 14 8 14c-1.854 0-3.32-.544-4.45-1.435-1.125-.887-1.89-2.095-2.391-3.383C.16 6.62.16 3.646.509 1.902L.73.806zm-.05 1.39c-.146 1.609-.008 3.809.74 5.728.457 1.17 1.13 2.213 2.079 2.961.942.744 2.185 1.22 3.83 1.221 2.588 0 3.91-.66 4.609-1.445-1.789-2.46-4.121-1.213-6.342-2.68-.74-.488-1.735-1.323-1.844-2.308-.023-.214.237-.274.38-.112 1.4 1.6 3.573 1.757 5.59 2.045 1.227.215 2.21.526 3.033 1.158.058-.39.075-.782.075-1.158 0-.91-.288-1.988-.975-2.792-.626-.732-1.622-1.281-3.167-1.229l-.316.02c-3.05.253-5.01-.08-6.291-.598a5.3 5.3 0 0 1-1.4-.811"/>
                </svg>
                <span>Recyclon</span>
            </a>
            <button class="navbar-toggler bg-light bg-opacity-75" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                  <li class="nav-item"><a class="nav-link text-light" href="index.jsp">Inicio</a></li>
                  <li class="nav-item"><a class="nav-link text-light" href="usuarios">Usuario</a></li>
                  <li class="nav-item"><a class="nav-link active text-light" href="productos">Productos</a></li>
                  <li class="nav-item"><a class="nav-link text-light" href="pedidos">Pedidos</a></li>
              </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <!-- Breadcrumb -->
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="productos">Productos</a></li>
                <li class="breadcrumb-item"><a href="productos?accion=getcategoriaproducto&id=${producto.categoria.idCategoria}">${producto.categoria.tipo}</a></li>
                <li class="breadcrumb-item active">${producto.nombre}</li>
            </ol>
        </nav>

        <div class="row">
            <!-- Imagen del Producto -->
            <div class="col-md-6 mb-4">
                <div class="card shadow">
                    <c:choose>
                        <c:when test="${not empty producto.imagen}">
                            <img src="imagenes/${producto.imagen}" class="card-img producto-imagen" alt="${producto.nombre}">
                        </c:when>
                        <c:otherwise>
                            <div class="bg-secondary d-flex align-items-center justify-content-center" style="height: 500px;">
                                <i class="bi bi-image text-white" style="font-size: 5rem;"></i>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <!-- Información del Producto -->
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-body">
                        <span class="badge bg-success mb-2">${producto.categoria.nombre}</span>
                        <h2 class="card-title">${producto.nombre}</h2>
                        
                        <h3 class="text-success my-4">
                            <fmt:formatNumber value="${producto.precio}" type="currency" currencySymbol="€"/>
                        </h3>

                        <div class="mb-4">
                            <h5><i class="bi bi-text-paragraph"></i> Descripción</h5>
                            <p class="text-muted">${producto.descripcion}</p>
                        </div>

                        <div class="mb-4">
                            <h5><i class="bi bi-box-seam"></i> Disponibilidad</h5>
                            <c:choose>
                                <c:when test="${producto.stock > 0}">
                                    <span class="badge bg-success fs-6">
                                        <i class="bi bi-check-circle"></i> En stock (${producto.stock} unidades)
                                    </span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-danger fs-6">
                                        <i class="bi bi-x-circle"></i> Agotado
                                    </span>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <hr>

                        <!-- Botones -->
                        <div class="d-grid gap-2">
                            <c:if test="${producto.stock > 0}">
                                <button class="btn btn-recyclon btn-lg">
                                    <i class="bi bi-cart-plus"></i> Añadir al Carrito
                                </button>
                            </c:if>

                            <c:if test="${sessionScope.usuario != null && sessionScope.usuario.isAdmin}">
                                <a href="productos?accion=modificarproducto&id=${producto.idProducto}" class="btn btn-warning">
                                    <i class="bi bi-pencil"></i> Modificar Producto
                                </a>
                                <a href="productos?accion=eliminarproducto&id=${producto.idProducto}" 
                                   class="btn btn-danger"
                                   onclick="return confirm('¿Eliminar este producto?')">
                                    <i class="bi bi-trash"></i> Eliminar Producto
                                </a>
                            </c:if>

                            <a href="productos" class="btn btn-outline-secondary">
                                <i class="bi bi-arrow-left"></i> Volver al Catálogo
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>