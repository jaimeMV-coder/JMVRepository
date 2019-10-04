/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meavenprueba;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author User
 */
public class ProfesorDAOImpl extends DAOFactory<Profesor> implements ProfesorDAO{
      private static final String SQL_INSERT
            = " INSERT INTO PROFESORES ( profesor_id,username,alumno_id ) "
            + " VALUES (?,?,?)";
    private static final String SQL_UPDATE
            = " UPDATE PROFESORES SET "
            + "    USERNAME = ?, "
            + "    ALUMNO_ID    = ? "
            + " WHERE PROFESOR_ID       = ? ";
    private static final String SQL_DELETE
            = "";
    private static final String SQL_ALL
            = " SELECT PROFESOR_ID,USERNAME,ALUMNO_ID  FROM PROFESORES ";
    private static final String SQL_FIND
            = " SELECT PROFESOR_ID,USERNAME,ALUMNO_ID "
            + " FROM PROFESORES "
            + " WHERE PROFESOR_ID = ?";
    
    public ProfesorDAOImpl() {
        super(SQL_ALL, SQL_INSERT, SQL_UPDATE, SQL_DELETE);
    }
      @Override
    public List<Profesor> getTodos() {
        return findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Profesor getProfesor(String id) {
        return find(SQL_FIND, id);
    }

    @Override
    public int update(Profesor profesor) {
        return super.update(profesor);
    }

    @Override
    public int insert(Profesor profesor) {
        String username=profesor.getUsername();
        Integer id_p=profesor.getProfesor_id();
        Integer alumno_id= profesor.getAlumno_id();
        
          try {
              return super.executeUpdate(SQL_INSERT,id_p,username,alumno_id);
          } catch (NamingException | SQLException ex) {
              throw new DAOException(ex);
          }
    }

    
    @Override
    Map<String, Object> convertObjToParam(Profesor profes) {
        Map<String, Object> params = new HashMap<>();
        params.put("1", profes.getProfesor_id());
        params.put("2", profes.getUsername());
        params.put("3", profes.getAlumno_id());
        return params;
    }

    @Override
    Profesor convertDbToOjb(ResultSet resultSet) throws SQLException {
        Profesor profes = new Profesor();
        profes.setProfesor_id(resultSet.getInt("PROFESOR_ID"));
        profes.setUsername(resultSet.getString("USERNAME"));
        profes.setAlumno_id(resultSet.getInt("ALUMNO_ID"));

        return profes;
    }
}
