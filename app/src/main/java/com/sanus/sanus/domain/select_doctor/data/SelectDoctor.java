package com.sanus.sanus.domain.select_doctor.data;

public class SelectDoctor {
    private String nombre;
    private String especialidad;
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public SelectDoctor(String nombre, String especialidad, String avatar, String id) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.avatar = avatar;
        this.id = id;
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getEspecialidad() {return especialidad;}
    public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}
    public String getAvatar() {return avatar;}
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
