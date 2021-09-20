/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serviciowebempresa.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.scene.media.Media;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author asantibo
 */



//C:\Users\asant\OneDrive\Escritorio




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
    public int[] Obtener(@PathParam("Id") int id) throws IOException {
        f = new File("C:\\Users\\asant\\OneDrive\\Escritorio\\resultados.txt");
        w = new FileWriter(f);
        bw = new BufferedWriter(w);
        pw = new PrintWriter(bw);
        
        pw.write("Esta es la id: "+id);
        
        pw.close();
        bw.close();
        
        int[] vector = {1, 2, 3, id};
        
        return vector;
    }

    @GET
    @Path("/obtenerGeneral")
    @Produces(MediaType.APPLICATION_JSON)   //tipo de respuesta
    public int[] ObtenerGeneral() {
        int[] vector = {1, 2, 3};
        return vector;
    }

    @POST
    @Path("/insertarEmpleado")
    @Consumes(MediaType.APPLICATION_JSON)   //tipo de consumo(cuerpo JSON)
    //@Produces(MediaType.APPLICATION_JSON)   //tipo de respuesta
    public void InsertarEmpleado(String nombre) {
        System.out.println(nombre + " con edad de: " + 22);
        System.out.println("Insertado correctamente");
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
    @Path("/eliminarPorId/{Id}")
    @Produces(MediaType.APPLICATION_JSON)   //tipo de respuesta
    public void eliminarEmpleado(@PathParam("Id") int id) {
        System.out.println("Eliminado correctamente: "+id);
    }
}
