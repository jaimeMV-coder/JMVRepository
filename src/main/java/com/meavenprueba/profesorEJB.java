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
}