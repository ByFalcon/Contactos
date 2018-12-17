package com.example.daniel.contactos;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacto implements Parcelable {

    long id;
    private String nombre;
    private String telefono;

    public Contacto(long id, String titulo, String telefono) {
        this.id = id;
        this.nombre = titulo;
        this.telefono = this.telefono;
    }

    public Contacto(){
        this(0, "", "");
    }

    protected Contacto(Parcel in) {
        id = in.readLong();
        nombre = in.readString();
        telefono = in.readString();
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nombre);
        dest.writeString(telefono);
    }
}
