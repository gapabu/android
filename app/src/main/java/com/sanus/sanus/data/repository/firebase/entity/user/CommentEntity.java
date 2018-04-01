package com.sanus.sanus.data.repository.firebase.entity.user;

public class CommentEntity {

    public String comentario;
    public String hora;
    public String fecha;
    public String calificacion;
    public String usuario;
    public String doctor;

    public CommentEntity(){}

    public CommentEntity(String comentario, String hora, String fecha, String calificacion, String usuario, String doctor) {
        this.comentario = comentario;
        this.hora = hora;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.doctor = doctor;
        this.usuario = usuario;
    }


}
