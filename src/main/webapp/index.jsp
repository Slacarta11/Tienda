<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tienda de Ropa</title>
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
            height: auto;
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
                <li><a href="login">Iniciar Sesión</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <h2>Bienvenido a nuestra tienda de ropa</h2>
        <p>Explora nuestra colección de moda y encuentra las últimas tendencias.</p>

        <div class="product-grid">
            <!-- Aquí mostraremos los productos destacados -->
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Producto 1">
                <h3>Camisa Casual</h3>
                <p>29.99 €</p>
                <button>Ver detalles</button>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Producto 2">
                <h3>Pantalón Vaquero</h3>
                <p>39.99 €</p>
                <button>Ver detalles</button>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Producto 3">
                <h3>Vestido Elegante</h3>
                <p>49.99 €</p>
                <button>Ver detalles</button>
            </div>
            <div class="product-card">
                <img src="https://via.placeholder.com/150" alt="Producto 4">
                <h3>Chaqueta de Invierno</h3>
                <p>59.99 €</p>
                <button>Ver detalles</button>
            </div>
        </div>
    </div>
</body>
</html>