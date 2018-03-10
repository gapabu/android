package com.sanus.sanus.domain.select_clinica.data;

/**
 * Created by Mireya on 10/03/2018.
 */

public class SelectClinica {
    String nombre;
    String direccion;

    public SelectClinica(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }






}
