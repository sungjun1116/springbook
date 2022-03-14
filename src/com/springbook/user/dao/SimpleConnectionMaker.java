package com.springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker {
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://database-1.c4avkpheoxjj.ap-northeast-2.rds.amazonaws.com:3306/springbook", "admin", "hsj143400**");
    }
}
