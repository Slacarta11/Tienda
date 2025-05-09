package com.tiendaropa.model;

import java.time.LocalDate;

public class Pedido {
    private int id;
    private int usuarioId;
    private int productoId;
    private LocalDate fechaPedido;
    private boolean enviado;

    // Referencias a objetos relacionados (opcionales)
    private Usuario usuario;
    private Producto producto;

    // Constructor vacío
    public Pedido() {
    }

    // Constructor con todos los campos
    public Pedido(int id, int usuarioId, int productoId, LocalDate fechaPedido, boolean enviado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.productoId = productoId;
        this.fechaPedido = fechaPedido;
        this.enviado = enviado;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", productoId=" + productoId +
                ", fechaPedido=" + fechaPedido +
                ", enviado=" + enviado +
                '}';
    }
}