package com.springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        // D사의 독자적인 방법으로 Connection을 생성하는 코드
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://database-1.c4avkpheoxjj.ap-northeast-2.rds.amazonaws.com:3306/springbook", "admin", "hsj143400**");
    }
}
