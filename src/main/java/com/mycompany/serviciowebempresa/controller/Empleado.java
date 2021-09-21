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

    short edad;
    String idEmpresarial;
    String cedula;
    String nombre;
    String segundoNombre;
    String Apellido;
    String segundoApellido;
    String Cargo;
    String Area;

    public Empleado() {
    }
    

    public Empleado(short edad, String idEmpresarial, String cedula, String nombre, String segundoNombre, String Apellido, String segundoApellido, String Cargo, String Area) {
        this.edad = edad;
        this.idEmpresarial = idEmpresarial;
        this.cedula = cedula;
        this.nombre = nombre;
        this.segundoNombre = segundoNombre;
        this.Apellido = Apellido;
        this.segundoApellido = segundoApellido;
        this.Cargo = Cargo;
        this.Area = Area;
    }

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
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
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }
}
