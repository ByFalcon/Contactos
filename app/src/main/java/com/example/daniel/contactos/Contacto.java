package com.example.daniel.contactos;

public class Contacto {

    long id;
    private String nombre;
    private String telefono;

    public Contacto(long id, String titulo, String telefono) {
        this.id = id;
        this.nombre = titulo;
        this.telefono = this.telefono;
    }

    public Contacto(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre (String titulo) {
        this.nombre = titulo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
