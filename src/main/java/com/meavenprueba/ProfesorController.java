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
public class ProfesorController {
    ProfesorDAO daou;
    
    public void registrar(Profesor profe){
        daou.insert(profe);
    }
    public void actualizar(Profesor profe){
        daou.update(profe);
    }
    public void mostrar_Todos(){
      daou.getTodos();
    }
}
