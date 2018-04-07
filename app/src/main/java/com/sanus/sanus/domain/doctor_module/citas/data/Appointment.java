package com.sanus.sanus.domain.doctor_module.citas.data;

public class Appointment {

    private String usuario;
    private String fecha;
    private String hora;
    private String avatar;

    public Appointment(String usuario, String fecha, String hora, String avatar) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.hora = hora;
        this.avatar = avatar;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
