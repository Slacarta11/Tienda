package com.tiendaropa.dao;

import com.tiendaropa.model.Categoria;
import com.tiendaropa.util.DatabaseConnector;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class CategoríaDAO implements Dao<Categoria> {

    private final Jdbi jdbi;

    public CategoríaDAO() {
        this.jdbi = DatabaseConnector.getJdbi();
    }

    @Override
    public Categoria findById(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM categorias WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Categoria.class)
                        .findOne()
                        .orElse(null)
        );
    }

    @Override
    public List<Categoria> findAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM categorias")
                        .mapToBean(Categoria.class)
                        .list()
        );
    }

    @Override
    public int save(Categoria categoria) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO categorias (nombre, descripcion) VALUES (:nombre, :descripcion)")
                        .bindBean(categoria)
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    @Override
    public boolean update(Categoria categoria) {
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE categorias SET nombre = :nombre, descripcion = :descripcion WHERE id = :id")
                        .bindBean(categoria)
                        .execute()
        );
        return rowsAffected > 0;
    }

    @Override
    public boolean delete(int id) {
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM categorias WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
        return rowsAffected > 0;
    }

    // Método para buscar por nombre
    public List<Categoria> findByNombre(String nombre) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM categorias WHERE nombre LIKE :nombre")
                        .bind("nombre", "%" + nombre + "%")
                        .mapToBean(Categoria.class)
                        .list()
        );
    }
}