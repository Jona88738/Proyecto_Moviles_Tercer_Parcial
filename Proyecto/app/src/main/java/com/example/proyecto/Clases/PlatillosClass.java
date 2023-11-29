package com.example.proyecto.Clases;

public class PlatillosClass {
    private int id, imagen;
    private String Nombre, Descripcion;
    private Double costo;
    private Boolean Disponible;

    public PlatillosClass() {
    }

    public PlatillosClass(int id) {
        this.id = id;
    }

    public PlatillosClass(int id, int imagen, String nombre, String descripcion, Double costo, Boolean disponible) {
        this.id = id;
        this.imagen = imagen;
        this.Nombre = nombre;
        this.Descripcion = descripcion;
        this.costo = costo;
        this.Disponible = disponible;
    }

    public PlatillosClass(int imagen, String nombre, Double costo) {
        this.imagen = imagen;
        this.Nombre = nombre;
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Boolean getDisponible() {
        return Disponible;
    }

    public void setDisponible(Boolean disponible) {
        Disponible = disponible;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
