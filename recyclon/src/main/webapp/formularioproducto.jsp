<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <title>Recyclon - ${empty producto ? 'Nuevo' : 'Modificar'} Producto</title>
    <style>
        :root {
            --color-fondo: #EDE8DC;
            --verde-oscuro:#93C572;
            --verde-claro:rgba(147, 197, 114, 0.9);
        }

        body {
            background-color: var(--color-fondo);
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
            color: white;
        }

        .card-header-recyclon {
            background: linear-gradient(135deg, var(--verde-oscuro) 0%, var(--verde-claro) 100%);
            color: white;
        }
    </style>
</head>
<body>
    <!-- NAVBAR (IGUAL QUE LOS OTROS) -->
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
                  <li class="nav-item"><a class="nav-link text-light" href="controladorrecyclon">Usuario</a></li>
                  <li class="nav-item"><a class="nav-link active text-light fw-bold" aria-current="page" href="productos">Productos</a></li>
                  <li class="nav-item"><a class="nav-link text-light" href="pedidos">Pedidos</a></li>
              </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow">
                    <div class="card-header card-header-recyclon">
                        <h3 class="mb-0">
                            <i class="bi bi-${empty producto ? 'plus' : 'pencil'}-circle"></i>
                            ${empty producto ? 'Nuevo Producto' : 'Modificar Producto'}
                        </h3>
                    </div>
                    
                    <div class="card-body">
                        <c:if test="${not empty mensaje.message}">
                            <div class="alert ${mensaje.role} alert-dismissible fade show">
                                ${mensaje.message}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <form action="productos" method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="accion" value="${empty producto ? 'nuevoproducto' : 'modificarproducto'}">
                            <c:if test="${not empty producto}">
                                <input type="hidden" name="id" value="${producto.id}">
                            </c:if>

                            <div class="mb-3">
                                <label for="nombre" class="form-label"><i class="bi bi-tag"></i> Nombre *</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" 
                                       value="${producto.nombre}" placeholder="Ej: iPhone 13 Pro" required>
                            </div>

                            <div class="mb-3">
                                <label for="descripcion" class="form-label"><i class="bi bi-text-paragraph"></i> Descripción</label>
                                 <textarea class="form-control" id="descripcion" name="descripcion" 
                                          rows="4" placeholder="Describe el producto...">${producto.descripcion}</textarea>
                            </div>

                            <div class="mb-3">
                                <label for="categoria" class="form-label"><i class="bi bi-list-ul"></i> Categoría *</label>
                                <select class="form-select" id="categoria" name="categoria" required>
                                    <option value="">Selecciona una categoría</option>
                                    <c:forEach var="cat" items="${categorias}">
                                        <option value="${cat.id}" ${producto.categoria.id == cat.id ? 'selected' : ''}>
                                            ${cat.nombre}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="precio" class="form-label"><i class="bi bi-currency-euro"></i> Precio *</label>
                                        <div class="input-group">
                                            <input type="number" class="form-control" id="precio" name="precio" 
                                                   step="0.01" min="0.01" value="${producto.precio}" 
                                                   placeholder="0.00" required>
                                            <span class="input-group-text">€</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="stock" class="form-label"><i class="bi bi-box-seam"></i> Stock *</label>
                                        <input type="number" class="form-control" id="stock" name="stock" 
                                               min="0" value="${producto.stock}" placeholder="0" required>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="imagen" class="form-label"><i class="bi bi-image"></i> Imagen del Producto</label>
                                
                                <c:if test="${not empty producto.imagen}">
                                    <div class="mb-2">
                                        <img src="imagenes/${producto.imagen}" alt="Imagen actual" 
                                             class="img-thumbnail" style="max-height: 150px;">
                                        <p class="text-muted small mt-1">Imagen actual</p>
                                    </div>
                                </c:if>
                                
                                <input type="file" class="form-control" id="imagen" name="imagen" accept="image/*">
                                <div class="form-text">Formatos: JPG, PNG, GIF (máx. 5MB)</div>
                                <div id="preview-container" class="mt-2"></div>
                            </div>

                            <hr>

                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <a href="productos" class="btn btn-secondary">
                                    <i class="bi bi-x-circle"></i> Cancelar
                                </a>
                                <button type="submit" class="btn btn-recyclon">
                                    <i class="bi bi-save"></i> 
                                    ${empty producto ? 'Crear Producto' : 'Guardar Cambios'}
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
    
    <script>
        // Vista previa de imagen
        document.getElementById('imagen').addEventListener('change', function(e) {
            const container = document.getElementById('preview-container');
            container.innerHTML = '';
            
            if (e.target.files && e.target.files[0]) {
                const reader = new FileReader();
                reader.onload = function(event) {
                    const preview = document.createElement('img');
                    preview.src = event.target.result;
                    preview.className = 'img-thumbnail';
                    preview.style.maxHeight = '150px';
                    
                    const label = document.createElement('p');
                    label.className = 'text-muted small mt-1';
                    label.textContent = 'Nueva imagen';
                    
                    container.appendChild(preview);
                    container.appendChild(label);
                };
                reader.readAsDataURL(e.target.files[0]);
            }
        });
    </script>
</body>
</html>