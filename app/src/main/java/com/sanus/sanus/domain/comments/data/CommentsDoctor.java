package com.sanus.sanus.domain.comments.data;

public class CommentsDoctor {
    private String usuario;
    private String cometario;
    private String fecha;
    private String calificacion;
    private String avatar;

    public String getAvatar() {return avatar;}

    public void setAvatar(String avatar) {this.avatar = avatar;}



    public CommentsDoctor(String usuario, String cometario, String fecha, String calificacion, String avatar) {
        this.usuario = usuario;
        this.cometario = cometario;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.avatar = avatar;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCometario() {
        return cometario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCalificacion() {return calificacion;}

}
