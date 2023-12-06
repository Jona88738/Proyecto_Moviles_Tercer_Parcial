package com.example.proyecto.Clases;

public class CostruCuenta {

    private static int aumento;

    public CostruCuenta() {
    }
    public CostruCuenta(int aumento) {
        this.aumento = aumento;
    }

    public int getAumento() {
        return aumento;
    }

    public void setAumento(int aumento) {
        this.aumento = aumento;
    }
}
