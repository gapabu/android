package com.sanus.sanus.domain.select_doctor.data;


public class SelectDoctor {
    private String nombre;
    private String especialidad;

    public SelectDoctor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getEspecialidad() {return especialidad;}

    public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}

}
