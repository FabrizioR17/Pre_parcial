package com.backend.repository.impl;

import com.backend.entity.Cosa;
import com.backend.repository.IDao;
import com.backend.repository.dbconnection.H2Connection;
import org.apache.log4j.Logger;

import java.sql.*;
public class CosaDaoH2 implements IDao<Cosa> {

    private final Logger LOGGER = Logger.getLogger(CosaDaoH2.class);

    @Override
    public Cosa registrar(Cosa cosa) {
        String insert = "INSERT INTO COSA(CODIGO, NOMBRE, LABORATORIO, CANTIDAD, PRECIO) VALUES(?, ?, ?, ?, ?)";

        Connection connection = null;
        Cosa cosaRegistrado = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setInt(1, cosa.getCodigo());
//            preparedStatement.setString(2, medicamento.getNombre());
//            preparedStatement.setString(3, medicamento.getLaboratorio());
//            preparedStatement.setInt(4, medicamento.getCantidad());
//            preparedStatement.setDouble(5, medicamento.getPrecio());
            preparedStatement.execute();

            //connection.commit();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
//                cosaRegistrado = new Cosa(resultSet.getLong("id"), medicamento.getCodigo(), medicamento.getNombre(), medicamento.getLaboratorio(), medicamento.getCantidad(), medicamento.getPrecio());
            }
            connection.commit();
            LOGGER.info("Cosa guardado: " + cosaRegistrado);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.error("Tuvimos un problema. " + e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        return cosaRegistrado;
    }

}
