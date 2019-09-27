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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ProfesorDAO extends DAOFactory<Profesor> {
       private static final String SQL_INSERT
            = " INSERT INTO PROFESORES ( profesor_id,username,alumno_id ) "
            + " VALUES ( :PROFESOR_ID,:USERNAME,:ALUMNO_ID )";
    private static final String SQL_UPDATE
            = " UPDATE PROFESORES SET "
            + "    USERNAME = :USERNAME, "
            + "    ALUMNO_ID    = :ALUMNO_ID "
            + " WHERE PROFESOR_ID       = :PROFESOR_ID ";
    private static final String SQL_DELETE
            = "";
    private static final String SQL_ALL
            = " SELECT PROFESOR_ID,USERNAME,ALUMNO_ID  FROM PROFESORES ";
    private static final String SQL_FIND
            = " SELECT PROFESOR_ID,USERNAME,ALUMNO_ID "
            + " FROM PROFESORES "
            + " WHERE PROFESOR_ID = :PROFESOR_ID";
    
    public ProfesorDAO() {
        super(SQL_ALL, SQL_INSERT, SQL_UPDATE, SQL_DELETE);
    }
    public List<Profesor> getTodos() {
        return findAll();
    }

    public Profesor getProfesor(String id) {
        return find(SQL_FIND, id);
    }

    @Override
    public int update(Profesor profesor) {
        return super.update(profesor);
    }

    @Override
    public int insert(Profesor profesor) {
        return super.insert(profesor);
    }

    
    @Override
    Map<String, Object> convertObjToParam(Profesor object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Profesor convertDbToOjb(ResultSet resultSet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
