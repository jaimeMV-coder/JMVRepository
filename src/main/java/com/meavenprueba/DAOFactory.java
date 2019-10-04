package com.meavenprueba;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * 
 * @author UDS
 * @param <T>
 */
public abstract class DAOFactory<T> {

    private static final String DATASOURCE_CONTEXT = "java:/PostgresDS";
    private final String SQL_ALL;
    private final String SQL_INSERT;
    private final String SQL_UPDATE;
    private final String SQL_DELETE;

    /**
     * Inicializa las consultas ALL, INSERT, UPDATE, DELETE conforme a el DAO que 
     * lo requirió.
     * @param sql_all String
     * @param sql_insert String
     * @param sql_update String
     * @param sql_delete String
     */
    public DAOFactory(String sql_all, String sql_insert, String sql_update, String sql_delete) {
        this.SQL_ALL = sql_all;
        this.SQL_INSERT = sql_insert;
        this.SQL_UPDATE = sql_update;
        this.SQL_DELETE = sql_delete;
    }

    /**
     * Inserta un objeto en la base de datos.
     * @param object T
     * @return
     * @throws DAOException
     */
    public int insert(T object) throws DAOException {
        try {
            return executeUpdate(SQL_INSERT, convertObjToParam(object));
        } catch (SQLException | NamingException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Actualiza un objeto en la base de datos.
     * @param object T
     * @return 
     * @throws DAOException
     */
    public int update(T object) throws DAOException {
        try {
            return executeUpdate(SQL_UPDATE, convertObjToParam(object));
        } catch (SQLException | NamingException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Elimina un objeto en la base de datos.
     * @param object T
     * @return
     * @throws DAOException
     */
    public int delete(T object) throws DAOException {
        try {
            return executeUpdate(SQL_DELETE, convertObjToParam(object));
        } catch (SQLException | NamingException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Busca un objeto en la base de datos.
     * @param sql String
     * @param values Object
     * @return
     * @throws DAOException
     */
    public T find(String sql, Object... values) throws DAOException {
        T object = null;
        try {
            object = executeQuery(sql, values);
        } catch (SQLException | NamingException e) {
            throw new DAOException(e);
        }
        return object;
    }

    /**
     * Regresa todos los registros de un objeto en especifico.
     * @return list T
     * @throws DAOException
     */
    public List<T> findAll() throws DAOException {
        return list(SQL_ALL);
    }

    /**
     * Lista los objetos en la base de datos.
     * @param sql String
     * @param values Object
     * @return
     * @throws DAOException
     */
    public List<T> list(String sql, Object... values) throws DAOException {
        List<T> list = null;
        try {
            list = executeQueryList(sql, values);
        } catch (SQLException | NamingException e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * 
     * @param sql String
     * @param values Object
     * @return
     * @throws DAOException
     */
    public Object singleResult(String sql, Object... values) throws DAOException {
        Object object = null;
        try {
            object = executeQuerySingleResult(sql, values);
        } catch (NamingException | SQLException e) {
            throw new DAOException(e);
        }
        return object;
    }

    /**
     * Ejecuta la conexion a la base de datos.
     * @param sql String 
     * @param params Map
     * @return
     * @throws SQLException
     * @throws NamingException
     */
    public int executeUpdate(String sql, Map<String, Object> params) throws SQLException, NamingException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            //connection.setAutoCommit(true);
            statement = connection.prepareCall(sql);
            setParams(statement, params, sql);
            return statement.executeUpdate();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Actualiza un objeto en la base de datos.
     * @param sql String
     * @param params Object
     * @return
     * @throws SQLException
     * @throws NamingException
     */
    public int executeUpdate(String sql, Object... params) throws SQLException, NamingException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            //connection.setAutoCommit(true);
            statement = connection.prepareCall(sql);
            //setParams(statement, params, sql);
            setValues(statement, params);
            return statement.executeUpdate();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private T executeQuery(String sql, Object... values) throws SQLException, NamingException {
        List<T> list = executeQueryList(sql, values);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<T> executeQueryList(String sql, Object... values) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> list = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setValues(statement, values);
            resultSet = statement.executeQuery();

            list = new ArrayList<>();
            while (resultSet.next()) {
                T object = convertDbToOjb(resultSet);
                list.add(object);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    /**
     * Regresa una lista de Objetos.
     * @param sql String
     * @param values Object
     * @return
     * @throws DAOException
     */
    public List<Object> singleList(String sql, Object... values) throws DAOException {
        List<Object> list = null;
        try {
            list = executeQuerySingleList(sql, values);
        } catch (SQLException | NamingException e) {
            throw new DAOException(e);
        }
        return list;
    }

    private List<Object> executeQuerySingleList(String sql, Object... values) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Object> list = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setValues(statement, values);
            resultSet = statement.executeQuery();

            list = new ArrayList<>();
            while (resultSet.next()) {
                Object object = (Object) resultSet.getObject(1);
                list.add(object);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    private Object executeQuerySingleResult(String sql, Object... values) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Object object = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setValues(statement, values);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                object = resultSet.getObject(1);
            }

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return object;
    }

    private Connection getConnection() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
        return datasource.getConnection();
    }

    private void setValues(PreparedStatement statement, Object... values) throws SQLException {
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
        }
    }

    private void setParams(CallableStatement statement, Map<String, Object> values, String sql) throws SQLException {
        String[] split = sql.replaceAll(",", " ").split(" ");
        List<String> nameParams = new ArrayList<>();

        for (String string : split) {
            if (string.startsWith(":")) {
                nameParams.add(
                        string.
                        replaceAll(",", "").
                        replaceAll(":", "").
                        replaceAll("\\)", "").
                        replaceAll(";", ""));
            }
        }

        for (String label : nameParams) {
            Object object = values.get(label);
            statement.setObject(label, object);
        }
    }

    /**
     * Convierte la fecha al formato
     * @param dateUtil Date
     * @return
     */
    public java.sql.Date convertDate(java.util.Date dateUtil) {
        return new java.sql.Date(dateUtil.getTime());
    }

    abstract Map<String, Object> convertObjToParam(T object);

    abstract T convertDbToOjb(ResultSet resultSet) throws SQLException;

}
