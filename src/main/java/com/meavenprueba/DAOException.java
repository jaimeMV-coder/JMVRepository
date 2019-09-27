/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meavenprueba;
/**
 *
 * @author UDS
 */
public class DAOException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Se especifica el mensaje para la excepción.
     *
     * @param message String
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Se especifica la causa.
     *
     * @param cause Throwable.
     */
    public DAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Se especifica el mensaje y la causa de la excepción.
     *
     * @param message String
     * @param cause Throwable.
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
