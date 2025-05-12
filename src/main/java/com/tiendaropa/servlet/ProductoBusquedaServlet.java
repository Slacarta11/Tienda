package com.tiendaropa.servlet;

import com.tiendaropa.dao.CategoríaDAO;
import com.tiendaropa.dao.MarcaDao;
import com.tiendaropa.dao.ProductoDao;
import com.tiendaropa.model.Categoria;
import com.tiendaropa.model.Marca;
import com.tiendaropa.model.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/buscar-productos")
public class ProductoBusquedaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Cargar categorías y marcas para el formulario de búsqueda
        CategoríaDAO categoriaDao = new CategoríaDAO();
        MarcaDao marcaDao = new MarcaDao();

        List<Categoria> categorias = categoriaDao.findAll();
        List<Marca> marcas = marcaDao.findAll();

        request.setAttribute("categorias", categorias);
        request.setAttribute("marcas", marcas);

        // Si hay parámetros de búsqueda, realizar la búsqueda
        if (request.getParameterMap().size() > 0 && request.getParameter("buscar") != null) {
            String nombre = request.getParameter("nombre");
            String categoriaIdStr = request.getParameter("categoriaId");
            String marcaIdStr = request.getParameter("marcaId");
            String precioMinStr = request.getParameter("precioMin");
            String precioMaxStr = request.getParameter("precioMax");

            // Convertir parámetros
            Integer categoriaId = null;
            if (categoriaIdStr != null && !categoriaIdStr.isEmpty()) {
                try {
                    categoriaId = Integer.parseInt(categoriaIdStr);
                } catch (NumberFormatException e) {
                    // Ignorar error de conversión
                }
            }

            Integer marcaId = null;
            if (marcaIdStr != null && !marcaIdStr.isEmpty()) {
                try {
                    marcaId = Integer.parseInt(marcaIdStr);
                } catch (NumberFormatException e) {
                    // Ignorar error de conversión
                }
            }

            BigDecimal precioMin = null;
            if (precioMinStr != null && !precioMinStr.isEmpty()) {
                try {
                    precioMin = new BigDecimal(precioMinStr);
                } catch (NumberFormatException e) {
                    // Ignorar error de conversión
                }
            }

            BigDecimal precioMax = null;
            if (precioMaxStr != null && !precioMaxStr.isEmpty()) {
                try {
                    precioMax = new BigDecimal(precioMaxStr);
                } catch (NumberFormatException e) {
                    // Ignorar error de conversión
                }
            }

            // Realizar búsqueda
            ProductoDao productoDao = new ProductoDao();
            List<Producto> resultados = productoDao.search(nombre, categoriaId, marcaId, precioMin, precioMax);

            request.setAttribute("resultados", resultados);
            request.setAttribute("nombre", nombre);
            request.setAttribute("categoriaId", categoriaId);
            request.setAttribute("marcaId", marcaId);
            request.setAttribute("precioMin", precioMin);
            request.setAttribute("precioMax", precioMax);
        }

        request.getRequestDispatcher("/buscar-productos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}