package com.tiendaropa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Producto {
    private int id;
    private String nombre;
    private BigDecimal precio;
    private int stock;
    private LocalDate fechaAlta;
    private String imagen;
    private int marcaId;
    private int categoriaId;

    // Referencias a objetos relacionados (opcionales)
    private Marca marca;
    private Categoria categoria;

    // Constructor vacío
    public Producto() {
    }

    // Constructor con todos los campos básicos
    public Producto(int id, String nombre, BigDecimal precio, int stock,
                    LocalDate fechaAlta, String imagen, int marcaId, int categoriaId) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.fechaAlta = fechaAlta;
        this.imagen = imagen;
        this.marcaId = marcaId;
        this.categoriaId = categoriaId;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", fechaAlta=" + fechaAlta +
                ", imagen='" + imagen + '\'' +
                ", marcaId=" + marcaId +
                ", categoriaId=" + categoriaId +
                '}';
    }
}