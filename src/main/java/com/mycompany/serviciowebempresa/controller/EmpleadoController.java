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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * El controlador del empleado permite administrar los empleados de una empresa
 * Se permite agregar empleados, mostrarlos, editarlos y eliminar la informaciòn
 * de un empleado Esta informacion se guarda y obtiene de un fichero o archivo
 * de texto plano en la ruta especificada
 *
 * @author Andres Santiago Bernal Ovalle
 */
@Stateless //no conversacional
@Path("/empleados")
public class EmpleadoController {

    private static ArrayList<Empleado> empleado = new ArrayList<>();
    String direccion = "C:\\Users\\asant\\OneDrive\\Escritorio\\resultados.txt";

    /**
     * Permite obtener la informacion de un empleado en especifico recibiendo su
     * id empresarial
     *
     * @param id
     * @return
     * @throws IOException
     */
    @GET
    @Path("/obtenerPorId/{Id}")
    @Produces(MediaType.APPLICATION_JSON) //tipo de respuesta
    public Empleado Obtener(@NotNull @Size(min = 1, max = 4) @Pattern(regexp = "[0-9]*") @PathParam("Id") String id) throws IOException {
        empleado = guardarArchivoEnArray();
        String contenido = "";
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
                return datos;
            } else {
                contenido = "No Registros";
            }
        }
        empleado.clear();
        return datos;
    }

    /**
     * Permite obtener el registro completo de empleados almacenados en el
     * archivo de texto plano
     *
     * @return ArrayList tipo Empleado
     */
    @GET
    @Path("/obtenerGeneral")
    @Produces(MediaType.APPLICATION_JSON)   //tipo de respuesta
    public ArrayList<Empleado> ObtenerGeneral() {

        ArrayList<Empleado> empleadoLista = new ArrayList<>();

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

        empleadoLista.clear();

        do {
            empleadoLista.add(new Empleado(Integer.parseInt(vector[contador + 1]), vector[contador + 3], vector[contador + 5], vector[contador + 7], vector[contador + 9], vector[contador + 11], vector[contador + 13], vector[contador + 15], vector[contador + 17]));
            contador = contador + 18;
        } while (contador < (vector.length) - 1);

        return empleadoLista;
    }

    /**
     * Permite insertar un empleado en el fichero
     *
     * @param datosEmpleado
     * @throws IOException
     */
    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)   //tipo de consumo(cuerpo JSON)
    public Response InsertarEmpleado(Empleado datosEmpleado) throws IOException {

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
            return Response.status(Response.Status.OK).entity("Agregado con exito").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
    }

    /**
     * Permite editar un empleado almacenado en el fichero
     *
     * @param datosEmpleado
     * @return String
     * @throws IOException
     */
    @PUT
    @Path("/editarEmpleado") //POR CEDULA
    @Consumes(MediaType.APPLICATION_JSON)   //tipo de consumo(cuerpo JSON)
    public String EditarEmpleado(Empleado datosEmpleado) throws IOException {
        empleado = guardarArchivoEnArray();
        String contenido = "Editado con exito";
        for (Empleado le : empleado) {

            System.out.println(le.getCedula());

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

                return contenido;
            } else {
                contenido = "No Registros";
            }
        }
        empleado.clear();
        return contenido;

    }

    /**
     * Permite eliminar un empleado almacenado en el fichero
     *
     * @param id
     * @return
     * @throws IOException
     */
    @DELETE
    @Path("/eliminarPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)   //tipo de respuesta
    public Response eliminarEmpleado(@NotNull @Size(min = 1, max = 4) @Pattern(regexp = "[0-9]*") @PathParam("id") String id) throws IOException {
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

    /**
     * permite guardar la informacion del fichero en un arreglo para poderla
     * utilizar dinamicamente
     *
     * @return ArrayList tipo Empleado
     */
    public ArrayList<Empleado> guardarArchivoEnArray() {
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

    /**
     * permite crear un nuevo fichero con los datos almacenados en el arrayList
     * general cada vez que se requiera
     *
     * @param datosEmpleado
     * @throws IOException
     */
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
