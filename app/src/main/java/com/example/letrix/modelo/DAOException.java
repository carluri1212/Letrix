package com.example.letrix.modelo;

    //Creamos una exepción en el caso de que falle la entrada/salida de datos.
    public class DAOException extends Exception {

        public DAOException(String mensaje) {
            super(mensaje);
        }

        public DAOException(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    }

