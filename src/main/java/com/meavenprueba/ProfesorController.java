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
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped;  
import javax.faces.model.DataModel;  
import javax.faces.component.UIComponent;
@ManagedBean(name="profesor_C")
@RequestScoped
public class ProfesorController {
    ProfesorDAO daou;
    Profesor profe;
    String id_p;
    String usern;
    String alum_id;

    public String getId_p() {
        return id_p;
    }

    public void setId_p(String id_p) {
        this.id_p = id_p;
    }

    public String getUsern() {
        return usern;
    }

    public void setUsern(String usern) {
        this.usern = usern;
    }

    public String getAlum_id() {
        return alum_id;
    }

    public void setAlum_id(String alum_id) {
        this.alum_id = alum_id;
    }
    
    public void registrar(){
        this.profe= new Profesor();
        this.daou= new ProfesorDAO();
        this.profe.setProfesor_id(1);
        this.profe.setUsername(getUsern());
        this.profe.setAlumno_id(1);
        this.daou.insert(this.profe);
    }
    public void actualizar(){
        this.daou.update(this.profe);
    }
    public void mostrar_Todos(){
      this.daou.getTodos();
    }
}
