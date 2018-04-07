package com.sanus.sanus.domain.citas.data;

public class Citas {
    private String doctor;
    private String fecha;
    private String hospital;
    private String hora;
    private String avatar;

    public Citas(String doctor, String fecha, String hospital, String hora, String avatar) {
        this.doctor = doctor;
        this.fecha = fecha;
        this.hospital = hospital;
        this.hora = hora;
        this.avatar = avatar;
    }
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
