package com.tiendaropa.dao;

import com.tiendaropa.model.Pedido;
import com.tiendaropa.model.Usuario;
import com.tiendaropa.model.Producto;
import com.tiendaropa.util.DatabaseConnector;
import org.jdbi.v3.core.Jdbi;

import java.time.LocalDate;
import java.util.List;

public class PedidoDao implements Dao<Pedido> {

    private final Jdbi jdbi;

    public PedidoDao() {
        this.jdbi = DatabaseConnector.getJdbi();
    }

    @Override
    public Pedido findById(int id) {
        return jdbi.withHandle(handle -> {
            Pedido pedido = handle.createQuery("SELECT * FROM pedidos WHERE id = :id")
                    .bind("id", id)
                    .mapToBean(Pedido.class)
                    .findOne()
                    .orElse(null);

            if (pedido != null) {
                // Cargar el usuario y producto relacionados
                Usuario usuario = handle.createQuery("SELECT * FROM usuarios WHERE id = :id")
                        .bind("id", pedido.getUsuarioId())
                        .mapToBean(Usuario.class)
                        .findOne()
                        .orElse(null);
                pedido.setUsuario(usuario);

                Producto producto = handle.createQuery("SELECT * FROM productos WHERE id = :id")
                        .bind("id", pedido.getProductoId())
                        .mapToBean(Producto.class)
                        .findOne()
                        .orElse(null);
                pedido.setProducto(producto);
            }

            return pedido;
        });
    }

    @Override
    public List<Pedido> findAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM pedidos")
                        .mapToBean(Pedido.class)
                        .list()
        );
    }

    @Override
    public int save(Pedido pedido) {
        return jdbi.withHandle(handle ->
                handle.createUpdate(
                                "INSERT INTO pedidos (usuario_id, producto_id, fecha_pedido, enviado) " +
                                        "VALUES (:usuarioId, :productoId, :fechaPedido, :enviado)")
                        .bindBean(pedido)
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    @Override
    public boolean update(Pedido pedido) {
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate(
                                "UPDATE pedidos SET usuario_id = :usuarioId, producto_id = :productoId, " +
                                        "fecha_pedido = :fechaPedido, enviado = :enviado WHERE id = :id")
                        .bindBean(pedido)
                        .execute()
        );
        return rowsAffected > 0;
    }

    @Override
    public boolean delete(int id) {
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM pedidos WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
        return rowsAffected > 0;
    }

    // Métodos de búsqueda adicionales
    public List<Pedido> findByUsuario(int usuarioId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM pedidos WHERE usuario_id = :usuarioId")
                        .bind("usuarioId", usuarioId)
                        .mapToBean(Pedido.class)
                        .list()
        );
    }

    public List<Pedido> findByFecha(LocalDate fecha) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM pedidos WHERE fecha_pedido = :fecha")
                        .bind("fecha", fecha)
                        .mapToBean(Pedido.class)
                        .list()
        );
    }

    public List<Pedido> findByFechaRango(LocalDate fechaInicio, LocalDate fechaFin) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                                "SELECT * FROM pedidos WHERE fecha_pedido BETWEEN :fechaInicio AND :fechaFin")
                        .bind("fechaInicio", fechaInicio)
                        .bind("fechaFin", fechaFin)
                        .mapToBean(Pedido.class)
                        .list()
        );
    }

    public List<Pedido> findByEstadoEnvio(boolean enviado) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM pedidos WHERE enviado = :enviado")
                        .bind("enviado", enviado)
                        .mapToBean(Pedido.class)
                        .list()
        );
    }

    // Método de búsqueda con múltiples criterios
    public List<Pedido> search(Integer usuarioId, Integer productoId,
                               LocalDate fechaInicio, LocalDate fechaFin, Boolean enviado) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM pedidos WHERE 1=1");

        if (usuarioId != null) {
            queryBuilder.append(" AND usuario_id = :usuarioId");
        }

        if (productoId != null) {
            queryBuilder.append(" AND producto_id = :productoId");
        }

        if (fechaInicio != null) {
            queryBuilder.append(" AND fecha_pedido >= :fechaInicio");
        }

        if (fechaFin != null) {
            queryBuilder.append(" AND fecha_pedido <= :fechaFin");
        }

        if (enviado != null) {
            queryBuilder.append(" AND enviado = :enviado");
        }

        return jdbi.withHandle(handle -> {
            org.jdbi.v3.core.statement.Query query = handle.createQuery(queryBuilder.toString());

            if (usuarioId != null) {
                query.bind("usuarioId", usuarioId);
            }

            if (productoId != null) {
                query.bind("productoId", productoId);
            }

            if (fechaInicio != null) {
                query.bind("fechaInicio", fechaInicio);
            }

            if (fechaFin != null) {
                query.bind("fechaFin", fechaFin);
            }

            if (enviado != null) {
                query.bind("enviado", enviado);
            }

            return query.mapToBean(Pedido.class).list();
        });
    }
}