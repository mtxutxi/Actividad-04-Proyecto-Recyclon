<!-- BARRA DE NAVEGACION -->
<nav class="navbar navbar-expand-lg fixed-top" style="background-color: #93C572;">
    <div class="container-fluid">
        <a class="navbar-brand text-light ms-3" href="index.jsp">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-leaf" viewBox="0 0 16 16">
                <path d="M1.4 1.7c.216.289.65.84 1.725 1.274 1.093.44 2.884.774 5.834.528l.37-.023c1.823-.06 3.117.598 3.956 1.579C14.16 6.082 14.5 7.41 14.5 8.5c0 .58-.032 1.285-.229 1.997q.198.248.382.54c.756 1.2 1.19 2.563 1.348 3.966a1 1 0 0 1-1.98.198c-.13-.97-.397-1.913-.868-2.77C12.173 13.386 10.565 14 8 14c-1.854 0-3.32-.544-4.45-1.435-1.125-.887-1.89-2.095-2.391-3.383C.16 6.62.16 3.646.509 1.902L.73.806zm-.05 1.39c-.146 1.609-.008 3.809.74 5.728.457 1.17 1.13 2.213 2.079 2.961.942.744 2.185 1.22 3.83 1.221 2.588 0 3.91-.66 4.609-1.445-1.789-2.46-4.121-1.213-6.342-2.68-.74-.488-1.735-1.323-1.844-2.308-.023-.214.237-.274.38-.112 1.4 1.6 3.573 1.757 5.59 2.045 1.227.215 2.21.526 3.033 1.158.058-.39.075-.782.075-1.158 0-.91-.288-1.988-.975-2.792-.626-.732-1.622-1.281-3.167-1.229l-.316.02c-3.05.253-5.01-.08-6.291-.598a5.3 5.3 0 0 1-1.4-.811"/>
            </svg>
            <span>Recyclon</span>
        </a>

        <!-- CARRITO EN MÓVIL - Visible al lado de la hamburguesa -->
        <a href="productos?accion=vercarrito" class="text-light ms-auto me-3 d-lg-none position-relative" style="font-size: 1.5rem; text-decoration: none;">
            <i class="bi bi-cart3"></i>
            <c:if test="${cesta.totalProductos > 0}">
                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger" style="font-size: 0.7rem;">
                    ${cesta.totalProductos}
                </span>
            </c:if>
        </a>

        <button class="navbar-toggler bg-light bg-opacity-75" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
              <li class="nav-item">
                  <a class="nav-link text-light" href="index.jsp">Inicio</a>
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
              <!-- CARRITO EN PANTALLAS GRANDES - Solo visible en desktop -->
              <li class="nav-item d-none d-lg-block">
                  <a class="nav-link text-light position-relative" href="productos?accion=vercarrito">
                      <i class="bi bi-cart3"></i> Carrito
                      <c:if test="${cesta.totalProductos > 0}">
                          <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                              ${cesta.totalProductos}
                          </span>
                      </c:if>
                  </a>
              </li>
          </ul>
      </div>
     </div>
</nav>
<div style="margin-top: 70px;"></div>