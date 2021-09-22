/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serviciowebempresa.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.Media;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author asantibo
 */
@Stateless //no conversacional
@Path("/empleados")
public class EmpleadoController {

    File f;
    FileWriter w;
    BufferedWriter bw;
    PrintWriter pw;

    private static ArrayList<Empleado> empleado = new ArrayList<>();

    @GET
    @Path("/obtenerPorId/{Id}")
    @Produces(MediaType.APPLICATION_JSON) //tipo de respuesta
    public void Obtener(@PathParam("Id") int id) throws IOException {

    }

    @GET
    @Path("/obtenerGeneral")
    @Produces(MediaType.APPLICATION_JSON)   //tipo de respuesta
    public String ObtenerGeneral() {
        String direccion = "C:\\Users\\asant\\OneDrive\\Escritorio\\resultados.txt";
        String contenido = "";
        int c;
        try {
            FileReader fr = new FileReader(direccion);

            c = fr.read();
            while (c != -1) {
                contenido += (char) c;
                c = fr.read();
            }
            fr.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return contenido;
    }

    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)   //tipo de consumo(cuerpo JSON)
    public void InsertarEmpleado(Empleado datosEmpleado) throws IOException {
        String direccion = "C:\\Users\\asant\\OneDrive\\Escritorio\\resultados.txt";

        try {
            FileWriter fw = new FileWriter(direccion, true);

            fw.write("edad;" + datosEmpleado.getEdad() + ";\n"
                    + "idEmpresarial;" + datosEmpleado.getIdEmpresarial() + ";\n"
                    + "cedula;" + datosEmpleado.getCedula() + ";\n"
                    + "nombre;" + datosEmpleado.getNombre() + ";\n"
                    + "segundo nombre;" + datosEmpleado.getSegundoNombre() + ";\n"
                    + "apellido;" + datosEmpleado.getApellido() + ";\n"
                    + "segundo apellido;" + datosEmpleado.getSegundoApellido() + ";\n"
                    + "cargo;" + datosEmpleado.getCargo() + ";\n"
                    + "area;" + datosEmpleado.getArea() + ";\n");

            fw.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @PUT
    @Path("/editarEmpleado")
    @Consumes(MediaType.APPLICATION_JSON)   //tipo de consumo(cuerpo JSON)
    //@Produces(MediaType.APPLICATION_JSON)   //tipo de respuesta
    public void EditarEmpleado(String nombre) {
        System.out.println(nombre + " con edad de: " + 22);
        System.out.println("Editado correctamente");
        
    }

    @DELETE
    @Path("/eliminarPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)   //tipo de respuesta
    public Response eliminarEmpleado(@PathParam("id") String id) throws IOException {
        String direccion = "C:\\Users\\asant\\OneDrive\\Escritorio\\resultados.txt";

        //1.guardar todo en una lista
        empleado = guardarArchivoEnArray();

        //2.buscar si existe el que se va a eliminar y eliminar del arreglo
        for (Empleado le : empleado) {

            if (le.getIdEmpresarial().equals(id)) {
                //elimina
                //System.out.println("se elimina a: " + le.getIdEmpresarial());
                empleado.remove(le);
                File archivo = new File(direccion);
                archivo.delete();
                NuevoArchivo(empleado);
                return (Response.noContent().entity("Se elimino correctamente " + id).build());
            }
        }
        return (Response.status(Response.Status.NOT_FOUND).build());
    }

    public ArrayList guardarArchivoEnArray() {
        String direccion = "C:\\Users\\asant\\OneDrive\\Escritorio\\resultados.txt";
        String contenido = "";
        int contador = 0;
        int c;
        try {
            FileReader fr = new FileReader(direccion);

            c = fr.read();
            while (c != -1) {
                contenido += (char) c;
                c = fr.read();
            }

            fr.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        String vector[] = contenido.split(";");

        do {
            empleado.add(new Empleado(Integer.parseInt(vector[contador + 1]), vector[contador + 3], vector[contador + 5], vector[contador + 7], vector[contador + 9], vector[contador + 11], vector[contador + 13], vector[contador + 15], vector[contador + 17]));
            contador = contador + 18;
        } while (contador < (vector.length) - 1);

        return empleado;
    }

    public void NuevoArchivo(ArrayList<Empleado> datosEmpleado) throws IOException {
        String direccion = "C:\\Users\\asant\\OneDrive\\Escritorio\\resultados.txt";

        for (Empleado le : empleado) {

            try {
                FileWriter fw = new FileWriter(direccion, true);

                fw.write("edad;" + le.getEdad() + ";\n"
                        + "idEmpresarial;" + le.getIdEmpresarial() + ";\n"
                        + "cedula;" + le.getCedula() + ";\n"
                        + "nombre;" + le.getNombre() + ";\n"
                        + "segundo nombre;" + le.getSegundoNombre() + ";\n"
                        + "apellido;" + le.getApellido() + ";\n"
                        + "segundo apellido;" + le.getSegundoApellido() + ";\n"
                        + "cargo;" + le.getCargo() + ";\n"
                        + "area;" + le.getArea() + ";\n");

                fw.close();//PENDIENTE

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        empleado.clear();
    }

}
