package com.backend;

import com.backend.repository.dbconnection.H2Connection;

public class Application {

    public static void main(String[] args) {
        H2Connection.crearTablas();

    }

}
