/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meavenprueba;

import java.util.List;

/**
 *
 * @author User
 */

public interface ProfesorDAO  {
    int insert(Profesor profesor);
    int update(Profesor profesor);
    Profesor getProfesor(String id);
    List<Profesor> getTodos() throws DAOException;
     
}
