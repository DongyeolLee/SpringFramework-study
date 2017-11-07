package com.dy.board;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by DY on 2017. 11. 8..
 */
public class MySQLConnectionTest {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/book_ex";
    private static final String USER = "root";
    private static final String PW = "";

    @Test
    public void testConnection() throws Exception {

        Class.forName(DRIVER);

        try(Connection con = DriverManager.getConnection(URL, USER, PW)) {

            //check if connection is okay
            System.out.println(con);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
