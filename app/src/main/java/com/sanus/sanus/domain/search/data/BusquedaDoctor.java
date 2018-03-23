package com.sanus.sanus.domain.search.data;

public class BusquedaDoctor{
    private String nombre;
    private String especialidad;
    private String avatar;
    private String id;
    private String estado;

    public BusquedaDoctor (String nombre, String especialidad, String avatar, String id, String estado){
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.avatar = avatar;
        this.id = id;
        this.estado = estado;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getAvatar() {return avatar;}
    public void setAvatar(String avatar) {this.avatar = avatar;}
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
    public String getEstado() {return estado;}

}
