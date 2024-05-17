package com.example.bai_tap_mahoa.Server;

import com.example.bai_tap_mahoa.Request;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static void SignUpDB(String username, String password) throws SQLException {
        String sql = "INSERT INTO `account`(`user`, `pass`) VALUES ('"+username+"','"+password+"')";
        Connection.getStatementConnect().executeUpdate(sql);
    }

    public static boolean LoginDB(String username, String password) throws SQLException {
        String sql = "SELECT `user` FROM `account` WHERE `user` = '"+username+"' AND `pass` = '"+password+"' ";
        ResultSet data = null;
        data = Connection.getStatementConnect().executeQuery(sql);
        return data.next();
    }
}
