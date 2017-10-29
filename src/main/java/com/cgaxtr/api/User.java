package com.cgaxtr.api;

/**
 * Created by Antonio on 01/07/2017.
 */
public class User {

    private int id;
    private String nombre;
    private String apellido;

    public void setId(int id){
        this.id = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public int getId(){
        return this.id;
    }

    public String getNonmbre(){
        return this.nombre;
    }

    public String getApellido(){
        return this.apellido;
    }
}
