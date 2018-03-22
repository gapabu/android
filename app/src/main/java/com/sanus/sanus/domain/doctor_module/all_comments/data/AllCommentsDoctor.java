package com.sanus.sanus.domain.doctor_module.all_comments.data;


public class AllCommentsDoctor {
    private String usuario;
    private String cometario;
    private String fecha;
    private String calificacion;
    private String avatar;

    public AllCommentsDoctor(String usuario, String cometario, String fecha, String calificacion, String avatar) {
        this.usuario = usuario;
        this.cometario = cometario;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
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

    public void setCometario(String cometario) {this.cometario = cometario;}

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
}