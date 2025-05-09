package com.tiendaropa.dao;

import com.tiendaropa.model.Usuario;
import com.tiendaropa.util.DatabaseConnector;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class UsuarioDao implements Dao<Usuario> {

    private final Jdbi jdbi;

    public UsuarioDao() {
        this.jdbi = DatabaseConnector.getJdbi();
    }

    @Override
    public Usuario findById(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM usuarios WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Usuario.class)
                        .findOne()
                        .orElse(null)
        );
    }

    @Override
    public List<Usuario> findAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM usuarios")
                        .mapToBean(Usuario.class)
                        .list()
        );
    }

    @Override
    public int save(Usuario usuario) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO usuarios (nombre, email, contraseña, rol, activo) VALUES (:nombre, :email, :contraseña, :rol, :activo)")
                        .bindBean(usuario)
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    @Override
    public boolean update(Usuario usuario) {
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE usuarios SET nombre = :nombre, email = :email, contraseña = :contraseña, rol = :rol, activo = :activo WHERE id = :id")
                        .bindBean(usuario)
                        .execute()
        );
        return rowsAffected > 0;
    }

    @Override
    public boolean delete(int id) {
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM usuarios WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
        return rowsAffected > 0;
    }

    // Método adicional para buscar por email (útil para login)
    public Usuario findByEmail(String email) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM usuarios WHERE email = :email")
                        .bind("email", email)
                        .mapToBean(Usuario.class)
                        .findOne()
                        .orElse(null)
        );
    }

    // Método adicional para validar credenciales
    public Usuario validarLogin(String email, String contraseña) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM usuarios WHERE email = :email AND contraseña = :contrasena AND activo = true")
                        .bind("email", email)
                        .bind("contrasena", contraseña)
                        .mapToBean(Usuario.class)
                        .findOne()
                        .orElse(null)
        );
    }
}