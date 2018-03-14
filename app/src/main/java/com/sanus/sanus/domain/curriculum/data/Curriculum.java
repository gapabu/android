package com.sanus.sanus.domain.curriculum.data;

public class Curriculum {
    String especialidad;
    String cv;
    String cedula;

    public Curriculum(String especialidad, String cv, String cedula) {
        this.especialidad = especialidad;
        this.cv = cv;
        this.cedula = cedula;
    }

    public String getEspecialidad() {return especialidad;}

    public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}

    public String getCv() {return cv;}

    public void setCv(String cv) {this.cv = cv;}

    public String getCedula() {return cedula;}

    public void setCedula(String cedula) {this.cedula = cedula;}

}
