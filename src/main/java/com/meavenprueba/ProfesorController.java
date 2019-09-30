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
@ManagedBean(name="profesor_C")
@RequestScoped
public class ProfesorController {
    ProfesorDAO daou;
    Profesor profe;
   
    public void registrar(){
        this.daou.insert(this.profe);
    }
    public void actualizar(){
        this.daou.update(this.profe);
    }
    public void mostrar_Todos(){
      this.daou.getTodos();
    }
}
