package com.example.proyecto.Clases;

public class CostruEnvio {

    private static String pedido;
    private static String calle;
    private static String colonia;
    private static String ciudad;
    private static int codigopos;

    public CostruEnvio() {
    }

    public CostruEnvio(String pedido, String calle, String colonia, String ciudad, int codigopos) {
        this.pedido = pedido;
        this.calle = calle;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.codigopos = codigopos;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getCodigopos() {
        return codigopos;
    }

    public void setCodigopos(int codigopos) {
        this.codigopos = codigopos;
    }
}
