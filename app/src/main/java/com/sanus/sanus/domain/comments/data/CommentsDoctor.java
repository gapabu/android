package com.sanus.sanus.domain.comments.data;

public class CommentsDoctor {
    String usuario;
    String cometario;
    String fecha;
    String calificacion;

    public CommentsDoctor(String usuario, String cometario, String fecha, String calificacion) {
        this.usuario = usuario;
        this.cometario = cometario;
        this.fecha = fecha;
        this.calificacion = calificacion;
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

    public void setCometario(String cometario) {
        this.cometario = cometario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCalificacion() {return calificacion;}

    public void setCalificacion(String calificacion) {this.calificacion = calificacion;}
}
