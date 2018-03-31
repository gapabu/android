package com.sanus.sanus.domain.new_chat.data;

public class Messages {

    private String mensaje;
    private String autor;
    private String  id;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    private  String hour;

    public Messages(String mensaje, String autor, String  id, String hour) {
        this.mensaje = mensaje;
        this.autor = autor;
        this.id = id;
        this.hour = hour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {this.autor = autor;}

    public String getMensaje() {return mensaje;}

    public void setMensaje(String mensaje) {this.mensaje = mensaje;}
}
