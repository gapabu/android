package com.sanus.sanus.domain.doctor_module.chat.data;

public class ContactDoctor {
    private String nombre;
    private String avatar;
    private String id;
    private String estado;

    public ContactDoctor (String nombre, String avatar, String id, String estado){
        this.nombre = nombre;
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
    public String getEstado() {return estado;}

}
