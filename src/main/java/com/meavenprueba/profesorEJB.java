/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meavenprueba;

/**
 *
 * @author User
 */
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class profesorEJB  {
     
    public String guardar(Profesor profesor) {
        String mensaje = "Fallo";
        if(new ProfesorDAOImpl().insert(profesor)!=0){
            
            mensaje="Creado";
        }
        
        return mensaje;
    }
    public List<Profesor> obtall(){
       List<Profesor>profesores;
        profesores=new ProfesorDAOImpl().getTodos();
         
        return profesores;
    }
    public String destruir(Profesor profe){
        String mensaje = "Fallo";
        if(new ProfesorDAOImpl().delete(profe)!=0){
        mensaje="Profesor eliminado";
        }
        return mensaje;
    }
    public String actualizar (Profesor profe){
        String mensaje="Fallo";
        if(new ProfesorDAOImpl().update(profe) !=0){
            mensaje="Profesor actualizado";
            
        }
        return mensaje;
    }
}