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
import com.meavenprueba.util.JsfUtil;
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import javax.faces.model.DataModel;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
@ManagedBean(name="profesor_C")
@ViewScoped
public class ProfesorController {
    
    String id_p;
    String alum_id;
    String usern;
    private Profesor current;
    
    public String getId_p() {
        return id_p;
    }

    public void setId_p(String id_p) {
        this.id_p = id_p;
    }

    public String getAlum_id() {
        return alum_id;
    }

    public void setAlum_id(String alum_id) {
        this.alum_id = alum_id;
    }

    public String getUsern() {
        return usern;
    }

    public void setUsern(String usern) {
        this.usern = usern;
    }

    
    @EJB
    private profesorEJB ejb;
    
    

    public String registrar() {
        current = new Profesor();
        this.current.setProfesor_id(Integer.parseInt(this.id_p));
        this.current.setAlumno_id(Integer.parseInt(this.alum_id));
        this.current.setUsername(this.usern);
        String mensaje;
        mensaje=ejb.guardar(current);
        return "Creado";
    }
   
   
}

