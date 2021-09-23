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

    private int edad;
    private String idEmpresarial;
    private String cedula;
    private String nombre;
    private String segundoNombre;
    private String apellido;
    private String segundoApellido;
    private String cargo;
    private String area;

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

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the idEmpresarial
     */
    public String getIdEmpresarial() {
        return idEmpresarial;
    }

    /**
     * @param idEmpresarial the idEmpresarial to set
     */
    public void setIdEmpresarial(String idEmpresarial) {
        this.idEmpresarial = idEmpresarial;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the segundoNombre
     */
    public String getSegundoNombre() {
        return segundoNombre;
    }

    /**
     * @param segundoNombre the segundoNombre to set
     */
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String Apellido) {
        this.apellido = Apellido;
    }

    /**
     * @return the segundoApellido
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * @param segundoApellido the segundoApellido to set
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String Cargo) {
        this.cargo = Cargo;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param segundoNombre the segundoNombre to set
     */
    public void setArea(String Area) {
        this.area = Area;
    }
}
