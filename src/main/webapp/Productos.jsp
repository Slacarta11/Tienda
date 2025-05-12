<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Productos - Tienda de Ropa</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        header {
            background-color: #333;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background-color: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
            margin-top: 20px;
        }
        .product-card {
            border: 1px solid #ddd;
            padding: 15px;
            text-align: center;
        }
        .product-card img {
            max-width: 100%;
            height: 200px;
            object-fit: cover;
        }
        nav ul {
            list-style-type: none;
            padding: 0;
            display: flex;
            justify-content: center;
            background-color: #444;
        }
        nav ul li {
            padding: 10px 20px;
        }
        nav ul li a {
            color: white;
            text-decoration: none;
        }
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .pagination a {
            display: inline-block;
            padding: 8px 16px;
            margin: 0 4px;
            border: 1px solid #ddd;
            color: black;
            text-decoration: none;
        }
        .pagination a.active {
            background-color: #333;
            color: white;
        }
        .pagination a:hover:not(.active) {
            background-color: #ddd;
        }
        .buttons {
            margin-top: 10px;
        }
        .buttons a {
            display: inline-block;
            padding: 5px 10px;
            margin-right: 5px;
            text-decoration: none;
            color: white;
            border-radius: 3px;
        }
        .view-btn {
            background-color: #428bca;
        }
        .edit-btn {
            background-color: #5cb85c;
        }
        .delete-btn {
            background-color: #d9534f;
        }
        .add-btn {
            display: block;
            margin: 20px 0;
            padding: 10px 15px;
            background-color: #5cb85c;
            color: white;
            text-decoration: none;
            text-align: center;
            border-radius: 3px;
        }
        .message {
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 3px;
        }
        .success {
            background-color: #dff0d8;
            color: #3c763d;
            border: 1px solid #d6e9c6;
        }
        .error {
            background-color: #f2dede;
            color: #a94442;
            border: 1px solid #ebccd1;
        }
    </style>
</head>
<body>
    <header>
        <h1>Tienda de Ropa</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Inicio</a></li>
                <li><a href="productos">Productos</a></li>
                <li><a href="categorias">Categorías</a></li>
                <li><a href="marcas">Marcas</a></li>
                <li><a href="buscar-productos">Buscar</a></li>
                <c:choose>
                    <c:when test="${sessionScope.usuario != null}">
                        <li><a href="perfil">Mi Perfil</a></li>
                        <li><a href="logout">Cerrar Sesión</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="login">Iniciar Sesión</a></li>
                        <li><a href="registro">Registrarse</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </header>

    <div class="container">
        <h2>Productos</h2>

        <c:if test="${sessionScope.mensaje != null}">
            <div class="message success">
                ${sessionScope.mensaje}
                <% session.removeAttribute("mensaje"); %>
            </div>
        </c:if>

        <c:if test="${sessionScope.error != null}">
            <div class="message error">
                ${sessionScope.error}
                <% session.removeAttribute("error"); %>
            </div>
        </c:if>

        <c:if test="${sessionScope.usuarioRol == 'admin'}">
            <a href="producto-form" class="add-btn">Añadir Nuevo Producto</a>
        </c:if>

        <div class="product-grid">
            <c:forEach items="${productos}" var="producto">
                <div class="product-card">
                    <c:choose>
                        <c:when test="${not empty producto.imagen}">
                            <img src="${producto.imagen}" alt="${producto.nombre}">
                        </c:when>
                        <c:otherwise>
                            <img src="https://via.placeholder.com/150" alt="Sin imagen">
                        </c:otherwise>
                    </c:choose>
                    <h3>${producto.nombre}</h3>
                    <p>${producto.precio} €</p>
                    <div class="buttons">
                        <a href="producto-detalle?id=${producto.id}" class="view-btn">Ver</a>
                        <c:if test="${sessionScope.usuarioRol == 'admin'}">
                            <a href="producto-form?id=${producto.id}" class="edit-btn">Editar</a>
                            <a href="eliminar-producto?id=${producto.id}" class="delete-btn"
                               onclick="return confirm('¿Estás seguro de eliminar este producto?')">Eliminar</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="pagination">
            <c:if test="${paginaActual > 1}">
                <a href="productos?pagina=${paginaActual - 1}">Anterior</a>
            </c:if>

            <c:forEach begin="1" end="${totalPaginas}" var="i">
                <c:choose>
                    <c:when test="${paginaActual == i}">
                        <a class="active">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="productos?pagina=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${paginaActual < totalPaginas}">
                <a href="productos?pagina=${paginaActual + 1}">Siguiente</a>
            </c:if>
        </div>
    </div>
</body>
</html>