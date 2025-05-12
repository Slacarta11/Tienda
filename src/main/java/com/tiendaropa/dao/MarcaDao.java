package com.tiendaropa.dao;

import com.tiendaropa.model.Marca;
import com.tiendaropa.util.DatabaseConnector;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class MarcaDao implements Dao<Marca> {

    private final Jdbi jdbi;

    public MarcaDao() {
        this.jdbi = DatabaseConnector.getJdbi();
    }

    @Override
    public Marca findById(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM marcas WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Marca.class)
                        .findOne()
                        .orElse(null)
        );
    }

    @Override
    public List<Marca> findAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM marcas")
                        .mapToBean(Marca.class)
                        .list()
        );
    }

    @Override
    public int save(Marca marca) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO marcas (nombre, pais_origen) VALUES (:nombre, :paisOrigen)")
                        .bindBean(marca)
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    @Override
    public boolean update(Marca marca) {
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE marcas SET nombre = :nombre, pais_origen = :paisOrigen WHERE id = :id")
                        .bindBean(marca)
                        .execute()
        );
        return rowsAffected > 0;
    }

    @Override
    public boolean delete(int id) {
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM marcas WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
        return rowsAffected > 0;
    }

    // Método para buscar por nombre
    public List<Marca> findByNombre(String nombre) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM marcas WHERE nombre LIKE :nombre")
                        .bind("nombre", "%" + nombre + "%")
                        .mapToBean(Marca.class)
                        .list()
        );
    }

    // Método para buscar por país
    public List<Marca> findByPais(String pais) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM marcas WHERE pais_origen LIKE :pais")
                        .bind("pais", "%" + pais + "%")
                        .mapToBean(Marca.class)
                        .list()
        );
    }
}