package com.example.bai_tap_mahoa.Server;

import com.mysql.cj.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.Statement;

public class Connection {
    private  static String username = "root";
    private static String password = "";
    public Connection() {
    }

    public static Statement getStatementConnect(){
        Statement stmt = null;
        try {
            String DB_URL = "jdbc:mysql://localhost:3306/csdlmahoa";
            java.sql.Connection conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
        }catch (Exception e){
            System.out.println(e);
        }
        return stmt;
    }
    public void closeConnection(java.sql.Connection connection) {
        try{

            if(connection != null) {
                connection.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
