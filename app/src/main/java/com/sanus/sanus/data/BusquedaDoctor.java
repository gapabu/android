package com.sanus.sanus.data;

import com.sanus.sanus.fragments.UserId;

public class BusquedaDoctor extends UserId{
    private String nombre;
    private String especialidad;

    public BusquedaDoctor (String nombre, String especialidad){
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}
