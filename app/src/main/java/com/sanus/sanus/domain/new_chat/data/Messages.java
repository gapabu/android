package com.sanus.sanus.domain.new_chat.data;

public class Messages {

    private String mensaje;
    private String autor;
    private String  id;

    public Messages(String mensaje, String autor, String  id) {
        this.mensaje = mensaje;
        this.autor = autor;
        this.id = id;
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
