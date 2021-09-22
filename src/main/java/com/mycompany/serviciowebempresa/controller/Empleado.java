/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serviciowebempresa.controller;

import java.io.Serializable;

/**
 *
 * @author asantibo
 */
public class Empleado implements Serializable {

    int edad;
    String idEmpresarial;
    String cedula;
    String nombre;
    String segundoNombre;
    String apellido;
    String segundoApellido;
    String cargo;
    String area;

    public Empleado() {
    }
    

    public Empleado(int edad, String idEmpresarial, String cedula, String nombre, String segundoNombre, String apellido, String segundoApellido, String cargo, String area) {
        this.edad = edad;
        this.idEmpresarial = idEmpresarial;
        this.cedula = cedula;
        this.nombre = nombre;
        this.segundoNombre = segundoNombre;
        this.apellido = apellido;
        this.segundoApellido = segundoApellido;
        this.cargo = cargo;
        this.area = area;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getIdEmpresarial() {
        return idEmpresarial;
    }

    public void setIdEmpresarial(String idEmpresarial) {
        this.idEmpresarial = idEmpresarial;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String Apellido) {
        this.apellido = Apellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String Cargo) {
        this.cargo = Cargo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String Area) {
        this.area = Area;
    }
}
