package com.sanus.sanus.domain.hospital.data;


public class Hospital {

    private String nombre;
    private String direccion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    public Hospital(String nombre, String direccion, String id) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.id = id;
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

}
