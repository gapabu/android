package com.sanus.sanus.data.repository.firebase.entity.user;

public class AppointmentEntity {
    public String hospital;
    public String doctor;
    public String fecha;
    public String hora;
    public String usuario;

    public AppointmentEntity(){}

    public AppointmentEntity(String hospital, String doctor, String fecha, String hora, String usuario) {
        this.hospital = hospital;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.usuario = usuario;
    }


}
