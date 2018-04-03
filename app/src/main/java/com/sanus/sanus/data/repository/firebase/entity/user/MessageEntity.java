package com.sanus.sanus.data.repository.firebase.entity.user;

public class MessageEntity {
    public String autor;
    public String doctor;
    public String fecha;
    public String hora;
    public String mensaje;
    public String usuario;

    public MessageEntity(){

    }

    public MessageEntity(String autor, String doctor, String fecha, String hora, String mensaje, String usuario) {
        this.autor = autor;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

}
