package com.sanus.sanus.data.repository.firebase.entity.user;

public class DoctorEntity {
    public String cv;
    public String cedula;
    public String especialidad;
    public String hospital;
    public String comentario;
    public String calificacion;

    public DoctorEntity(){}

    public DoctorEntity(String cv, String cedula, String especialidad, String hospital, String comentario, String calificacion) {
        this.cv = cv;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.hospital = hospital;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }







}
