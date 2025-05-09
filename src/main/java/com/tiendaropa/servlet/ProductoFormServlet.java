package com.tiendaropa.servlet;

import com.tiendaropa.dao.CategoríaDAO;
import com.tiendaropa.dao.MarcaDao;
import com.tiendaropa.dao.ProductoDao;
import com.tiendaropa.model.Categoria;
import com.tiendaropa.model.Marca;
import com.tiendaropa.model.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@WebServlet("/producto-form")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10,  // 10MB
        maxRequestSize = 1024 * 1024 * 30 // 30MB
)
public class ProductoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        Producto producto = new Producto();

        // Si hay ID, cargar el producto existente
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                ProductoDao productoDao = new ProductoDao();
                producto = productoDao.findById(id);

                if (producto == null) {
                    response.sendRedirect(request.getContextPath() + "/productos");
                    return;
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/productos");
                return;
            }
        }

        // Cargar categorías y marcas para el formulario
        CategoríaDAO categoriaDao = new CategoríaDAO();
        MarcaDao marcaDao = new MarcaDao();

        List<Categoria> categorias = categoriaDao.findAll();
        List<Marca> marcas = marcaDao.findAll();

        request.setAttribute("producto", producto);
        request.setAttribute("categorias", categorias);
        request.setAttribute("marcas", marcas);
        request.getRequestDispatcher("/producto-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener parámetros del formulario
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String precioStr = request.getParameter("precio");
        String stockStr = request.getParameter("stock");
        String categoriaIdStr = request.getParameter("categoriaId");
        String marcaIdStr = request.getParameter("marcaId");

        // Validación básica
        if (nombre == null || nombre.trim().isEmpty() ||
                precioStr == null || precioStr.trim().isEmpty() ||
                stockStr == null || stockStr.trim().isEmpty()) {

            request.setAttribute("error", "Todos los campos marcados con * son obligatorios");
            doGet(request, response);
            return;
        }

        try {
            Producto producto = new Producto();

            // Si es una edición, establecer el ID
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                producto.setId(id);

                // Obtener el producto existente para la imagen
                ProductoDao productoDao = new ProductoDao();
                Producto productoExistente = productoDao.findById(id);
                if (productoExistente != null) {
                    producto.setImagen(productoExistente.getImagen());
                }
            }

            producto.setNombre(nombre);
            producto.setPrecio(new BigDecimal(precioStr));
            producto.setStock(Integer.parseInt(stockStr));

            // Establecer fecha de alta solo para nuevos productos
            if (idStr == null || idStr.isEmpty()) {
                producto.setFechaAlta(LocalDate.now());
            } else {
                // Mantener la fecha original para productos existentes
                ProductoDao productoDao = new ProductoDao();
                Producto existente = productoDao.findById(Integer.parseInt(idStr));
                if (existente != null) {
                    producto.setFechaAlta(existente.getFechaAlta());
                } else {
                    producto.setFechaAlta(LocalDate.now());
                }
            }

            // Categoría y marca (opcionales)
            if (categoriaIdStr != null && !categoriaIdStr.isEmpty()) {
                producto.setCategoriaId(Integer.parseInt(categoriaIdStr));
            }

            if (marcaIdStr != null && !marcaIdStr.isEmpty()) {
                producto.setMarcaId(Integer.parseInt(marcaIdStr));
            }

            // Manejo de imagen
            Part filePart = request.getPart("imagen");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String extension = fileName.substring(fileName.lastIndexOf('.'));
                String newFileName = UUID.randomUUID().toString() + extension;

                // Guardar imagen en el directorio de imágenes
                String uploadDir = getServletContext().getRealPath("/img/productos");
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }

                String filePath = uploadDir + File.separator + newFileName;
                filePart.write(filePath);

                // Guardar ruta relativa en la base de datos
                producto.setImagen("img/productos/" + newFileName);
            }

            // Guardar o actualizar producto
            ProductoDao productoDao = new ProductoDao();
            if (producto.getId() > 0) {
                productoDao.update(producto);
            } else {
                productoDao.save(producto);
            }

            response.sendRedirect(request.getContextPath() + "/productos");

        } catch (Exception e) {
            request.setAttribute("error", "Error al procesar el formulario: " + e.getMessage());
            doGet(request, response);
        }
    }
}