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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    private static ArrayList<Empleado> empleado = new ArrayList<>();
    String direccion = "C:\\Users\\asant\\OneDrive\\Escritorio\\resultados.txt";

    @GET
    //@Valid
    @Path("/obtenerPorId/{Id}")
    @Produces(MediaType.APPLICATION_JSON) //tipo de respuesta
    public Response Obtener(/*@NotNull @Size(min = 1, max = 4) @Pattern(regexp = "[0-9]*")*/ @PathParam("Id") String id) throws IOException {

        /*try {*/
            empleado = guardarArchivoEnArray();
            Empleado datos = new Empleado();

            for (Empleado le : empleado) {

                if (le.getIdEmpresarial().equals(id)) {

                    datos.setEdad(le.getEdad());
                    datos.setIdEmpresarial(le.getIdEmpresarial());
                    datos.setCedula(le.getCedula());
                    datos.setNombre(le.getNombre());
                    datos.setSegundoNombre(le.getSegundoNombre());
                    datos.setApellido(le.getApellido());
                    datos.setSegundoApellido(le.getSegundoApellido());
                    datos.setCargo(le.getCargo());
                    datos.setArea(le.getArea());

                    empleado.clear();

                    return Response.status(Response.Status.OK).entity(datos).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).entity("Id empresarial no encontrado!").build();
                }
            }
            empleado.clear();
            return Response.status(Response.Status.OK).entity(datos).build();
        /*} catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }*/
    }

    @GET
    @Path("/obtenerGeneral")
    @Produces(MediaType.APPLICATION_JSON)   //tipo de respuesta
    public Response ObtenerGeneral() {

        ArrayList<Empleado> empleadoLista = new ArrayList<>();

        try {
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
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
            }
            try {
                String vector[] = contenido.split(";");

                empleadoLista.clear();

                do {
                    empleadoLista.add(new Empleado(Integer.parseInt(vector[contador + 1]), vector[contador + 3], vector[contador + 5], vector[contador + 7], vector[contador + 9], vector[contador + 11], vector[contador + 13], vector[contador + 15], vector[contador + 17]));
                    contador = contador + 18;
                } while (contador < (vector.length) - 1);
            } catch (Exception e) {
                return Response.status(Response.Status.NO_CONTENT).entity("No existen elementos. " + e).build();
            }
            return Response.status(Response.Status.OK).entity(empleadoLista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
    }

    @POST
    @Valid
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)   //tipo de consumo(cuerpo JSON)
    public Response InsertarEmpleado(@Valid Empleado datosEmpleado) throws IOException {

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
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("2" + e).build();
        }

        return Response.status(Response.Status.OK).entity("Agregado con exito").build();

    }

    @PUT
    @Valid
    @Path("/editarEmpleado")
    @Consumes(MediaType.APPLICATION_JSON)   //tipo de consumo(cuerpo JSON)
    public Response EditarEmpleado(@Valid Empleado datosEmpleado) throws IOException {

        try {
            empleado = guardarArchivoEnArray();
            String contenido = "Editado con exito";
            for (Empleado le : empleado) {

                if (le.getCedula().equals(datosEmpleado.getCedula())) {

                    le.setEdad(datosEmpleado.getEdad());
                    le.setIdEmpresarial(datosEmpleado.getIdEmpresarial());
                    le.setCedula(datosEmpleado.getCedula());
                    le.setNombre(datosEmpleado.getNombre());
                    le.setSegundoNombre(datosEmpleado.getSegundoNombre());
                    le.setApellido(datosEmpleado.getApellido());
                    le.setSegundoApellido(datosEmpleado.getSegundoApellido());
                    le.setCargo(datosEmpleado.getCargo());
                    le.setArea(datosEmpleado.getArea());

                    File archivo = new File(direccion);
                    archivo.delete();
                    NuevoArchivo(empleado);

                    return Response.status(Response.Status.CREATED).entity(contenido).build();
                } else {
                    contenido = "No Registros";
                }
            }
            empleado.clear();
            //201
            return Response.status(Response.Status.CREATED).entity(contenido).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
    }

    @DELETE
    @Valid
    @Path("/eliminarPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)   //tipo de respuesta
    public Response eliminarEmpleado(@NotNull @Size(min = 1, max = 4) @Pattern(regexp = "[0-9]*") @PathParam("id") String id) throws IOException {

        try {
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
                } else {
                    return (Response.status(Response.Status.NOT_FOUND).entity("Recurso no encontrado").build());
                }
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

    }

    public ArrayList guardarArchivoEnArray() {
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
