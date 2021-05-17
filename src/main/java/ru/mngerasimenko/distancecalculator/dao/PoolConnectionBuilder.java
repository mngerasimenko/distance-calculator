package ru.mngerasimenko.distancecalculator.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionBuilder implements ConnectionBuilder{

    protected DataSource dataSource;

    public  PoolConnectionBuilder() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:jboss/datasources/distanceCalculator");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        if (dataSource == null) throw new RuntimeException("Connection failed");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
