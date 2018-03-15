package com.sanus.sanus.domain.select_doctor.data;

public class SelectDoctor {
    private String nombre;
    private String especialidad;

   /* public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String avatar;*/
    public SelectDoctor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        //this.avatar = avatar;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getEspecialidad() {return especialidad;}

    public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}

}
