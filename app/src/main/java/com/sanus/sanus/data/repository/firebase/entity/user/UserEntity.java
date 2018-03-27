package com.sanus.sanus.data.repository.firebase.entity.user;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserEntity {

    public String tipo;
    public String nombre;
    public String apellido;
    public String edad;
    public String sexo;
    public String avatar;
    public String estado;

    public UserEntity() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserEntity(String tipo, String nombre, String apellido, String edad, String sexo, String avatar, String estado) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.avatar = avatar;
        this.estado = estado;
    }
}