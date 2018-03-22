package com.sanus.sanus.domain.chat.data;


public class ContactUser {
    private String nombre;
    private String avatar;
    private String id;

    public ContactUser (String nombre, String avatar, String id){
        this.nombre = nombre;
        this.avatar = avatar;
        this.id = id;
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
}
