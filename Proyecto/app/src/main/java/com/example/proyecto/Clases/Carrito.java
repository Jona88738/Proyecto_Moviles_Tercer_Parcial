package com.example.proyecto.Clases;

public class Carrito {
    private int id, img, id_platillo, cantidad_comida;
    private Double Precio_normal, Precio_IVA;

    public Carrito(int id, int id_platillo, int cantidad_comida, Double precio_normal, Double precio_IVA) {
        this.id = id;
        this.id_platillo = id_platillo;
        this.cantidad_comida = cantidad_comida;
        Precio_normal = precio_normal;
        Precio_IVA = precio_IVA;
    }

    public Carrito(int img, int id_platillo, Double precio_IVA) {
        this.img = img;
        this.id_platillo = id_platillo;
        Precio_IVA = precio_IVA;
    }

    public Carrito(int id) {
        this.id = id;
    }

    public Carrito() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_platillo() {
        return id_platillo;
    }

    public void setId_platillo(int id_platillo) {
        this.id_platillo = id_platillo;
    }

    public int getCantidad_comida() {
        return cantidad_comida;
    }

    public void setCantidad_comida(int cantidad_comida) {
        this.cantidad_comida = cantidad_comida;
    }

    public Double getPrecio_normal() {
        return Precio_normal;
    }

    public void setPrecio_normal(Double precio_normal) {
        Precio_normal = precio_normal;
    }

    public Double getPrecio_IVA() {
        return Precio_IVA;
    }

    public void setPrecio_IVA(Double precio_IVA) {
        Precio_IVA = precio_IVA;
    }
}
