package com.tiendaropa.dao;

import com.tiendaropa.model.Producto;
import com.tiendaropa.model.Categoria;
import com.tiendaropa.model.Marca;
import com.tiendaropa.util.DatabaseConnector;
import org.jdbi.v3.core.Jdbi;

import java.math.BigDecimal;
import java.util.List;

public class ProductoDao implements Dao<Producto> {

    private final Jdbi jdbi;

    public ProductoDao() {
        this.jdbi = DatabaseConnector.getJdbi();
    }

    @Override
    public Producto findById(int id) {
        return jdbi.withHandle(handle -> {
            Producto producto = handle.createQuery(
                            "SELECT * FROM productos WHERE id = :id")
                    .bind("id", id)
                    .mapToBean(Producto.class)
                    .findOne()
                    .orElse(null);

            if (producto != null) {
                // Cargar la marca y categoría si existen
                if (producto.getMarcaId() > 0) {
                    Marca marca = handle.createQuery("SELECT * FROM marcas WHERE id = :id")
                            .bind("id", producto.getMarcaId())
                            .mapToBean(Marca.class)
                            .findOne()
                            .orElse(null);
                    producto.setMarca(marca);
                }

                if (producto.getCategoriaId() > 0) {
                    Categoria categoria = handle.createQuery("SELECT * FROM categorias WHERE id = :id")
                            .bind("id", producto.getCategoriaId())
                            .mapToBean(Categoria.class)
                            .findOne()
                            .orElse(null);
                    producto.setCategoria(categoria);
                }
            }

            return producto;
        });
    }

    @Override
    public List<Producto> findAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                                "SELECT p.*, m.nombre AS marca_nombre, c.nombre AS categoria_nombre " +
                                        "FROM productos p " +
                                        "LEFT JOIN marcas m ON p.marca_id = m.id " +
                                        "LEFT JOIN categorias c ON p.categoria_id = c.id")
                        .mapToBean(Producto.class)
                        .list()
        );
    }

    @Override
    public int save(Producto producto) {
        return jdbi.withHandle(handle ->
                handle.createUpdate(
                                "INSERT INTO productos (nombre, precio, stock, fecha_alta, imagen, marca_id, categoria_id) " +
                                        "VALUES (:nombre, :precio, :stock, :fechaAlta, :imagen, :marcaId, :categoriaId)")
                        .bindBean(producto)
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    @Override
    public boolean update(Producto producto) {
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate(
                                "UPDATE productos SET nombre = :nombre, precio = :precio, stock = :stock, " +
                                        "fecha_alta = :fechaAlta, imagen = :imagen, marca_id = :marcaId, categoria_id = :categoriaId " +
                                        "WHERE id = :id")
                        .bindBean(producto)
                        .execute()
        );
        return rowsAffected > 0;
    }

    @Override
    public boolean delete(int id) {
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM productos WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
        return rowsAffected > 0;
    }

    // Métodos de búsqueda
    public List<Producto> findByNombre(String nombre) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                                "SELECT * FROM productos WHERE nombre LIKE :nombre")
                        .bind("nombre", "%" + nombre + "%")
                        .mapToBean(Producto.class)
                        .list()
        );
    }

    public List<Producto> findByPrecioRango(BigDecimal min, BigDecimal max) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                                "SELECT * FROM productos WHERE precio BETWEEN :min AND :max")
                        .bind("min", min)
                        .bind("max", max)
                        .mapToBean(Producto.class)
                        .list()
        );
    }

    public List<Producto> findByCategoria(int categoriaId) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                                "SELECT * FROM productos WHERE categoria_id = :categoriaId")
                        .bind("categoriaId", categoriaId)
                        .mapToBean(Producto.class)
                        .list()
        );
    }

    public List<Producto> findByMarca(int marcaId) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                                "SELECT * FROM productos WHERE marca_id = :marcaId")
                        .bind("marcaId", marcaId)
                        .mapToBean(Producto.class)
                        .list()
        );
    }

    // Método para búsqueda con múltiples criterios
    public List<Producto> search(String nombre, Integer categoriaId, Integer marcaId,
                                 BigDecimal precioMin, BigDecimal precioMax) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM productos WHERE 1=1");

        if (nombre != null && !nombre.isEmpty()) {
            queryBuilder.append(" AND nombre LIKE :nombre");
        }

        if (categoriaId != null && categoriaId > 0) {
            queryBuilder.append(" AND categoria_id = :categoriaId");
        }

        if (marcaId != null && marcaId > 0) {
            queryBuilder.append(" AND marca_id = :marcaId");
        }

        if (precioMin != null) {
            queryBuilder.append(" AND precio >= :precioMin");
        }

        if (precioMax != null) {
            queryBuilder.append(" AND precio <= :precioMax");
        }

        return jdbi.withHandle(handle -> {
            org.jdbi.v3.core.statement.Query query = handle.createQuery(queryBuilder.toString());

            if (nombre != null && !nombre.isEmpty()) {
                query.bind("nombre", "%" + nombre + "%");
            }

            if (categoriaId != null && categoriaId > 0) {
                query.bind("categoriaId", categoriaId);
            }

            if (marcaId != null && marcaId > 0) {
                query.bind("marcaId", marcaId);
            }

            if (precioMin != null) {
                query.bind("precioMin", precioMin);
            }

            if (precioMax != null) {
                query.bind("precioMax", precioMax);
            }

            return query.mapToBean(Producto.class).list();
        });
    }

    // Método para paginación
    public List<Producto> findAllPaginated(int limit, int offset) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM productos LIMIT :limit OFFSET :offset")
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(Producto.class)
                        .list()
        );
    }

    // Método para contar el total de productos (útil para paginación)
    public int countAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM productos")
                        .mapTo(Integer.class)
                        .one()
        );
    }
}