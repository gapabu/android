package com.sanus.sanus.domain.new_chat.data;

public class Messages {

    private String mensaje;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String  id;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    private String autor;

    public Messages(String mensaje, String autor, String  id) {
        this.mensaje = mensaje;
        this.autor = autor;
        this.id = id;
    }

    public String getMensaje() {return mensaje;}

    public void setMensaje(String mensaje) {this.mensaje = mensaje;}
}
