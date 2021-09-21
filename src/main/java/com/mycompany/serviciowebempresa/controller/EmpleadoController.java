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

    @GET
    @Path("/obtenerPorId/{Id}")
    @Produces(MediaType.APPLICATION_JSON) //tipo de respuesta
    public void Obtener(@PathParam("Id") int id) throws IOException {
        String direccion = "C:\\Users\\asant\\OneDrive\\Escritorio\\resultados.txt";
        try {
            String bfReader;
            BufferedReader lector = new BufferedReader(new FileReader(direccion));
            while ((bfReader = lector.readLine()) != null) {
                String[] partes;
                partes = bfReader.split(",");
                System.out.println(bfReader);
            }
            lector.close();
        } catch (Exception e) {
            System.out.println("Fichero no encontrado");
        }

        /*f = new File("C:\\Users\\asant\\OneDrive\\Escritorio\\resultados.txt");
        w = new FileWriter(f);
        bw = new BufferedWriter(w);
        pw = new PrintWriter(bw);

        pw.write("Esta es la id: " + id);

        pw.close();
        bw.close();

        int[] vector = {1, 2, 3, id};

        return vector;*/
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
            while (c!=-1) {
                contenido+=(char)c;
                c=fr.read();
            }
            
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
            
            fw.write("\n[;idEmpresarial: " + datosEmpleado.getIdEmpresarial() + ";\n"
                + "edad: " + datosEmpleado.getEdad() + ";\n"
                + "cedula: " + datosEmpleado.getCedula() + ";\n"
                + "nombre: " + datosEmpleado.getNombre() + ";\n"
                + "segundo nombre: " + datosEmpleado.getSegundoNombre() + ";\n"
                + "Apellido: " + datosEmpleado.getApellido() + ";\n"
                + "segundo apellido: " + datosEmpleado.getSegundoApellido() + ";\n"
                + "cargo: " + datosEmpleado.getCargo() + ";\n"
                + "area: " + datosEmpleado.getArea() + ";]\n;");
            
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
    public void eliminarEmpleado(@PathParam("id") int id) {
        //1.guardar todo en una lista
        String direccion = "C:\\Users\\asant\\OneDrive\\Escritorio\\resultados.txt";
        String contenido = "";
        int c;
        try {
            FileReader fr = new FileReader(direccion);
            
            c = fr.read();
            while (c!=-1) {
                contenido+=(char)c;
                c=fr.read();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        String vector[] = contenido.split(";");
        
        System.out.println(vector[10]);
        //2.buscar el que se va a eliminar y eliminar del arreglo
        int i = 0;
        while(i<vector.length && vector[i]=="idEmpresarial: "+id){
            
            
            
            i++;
        }
        
        //2. eliminar fichero 
        
        //3. crear un nuevo fichero con el arreglo nuevo
    }
}
