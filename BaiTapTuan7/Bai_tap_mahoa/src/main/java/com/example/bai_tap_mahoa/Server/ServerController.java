package com.example.bai_tap_mahoa.Server;

import com.example.bai_tap_mahoa.Cl_toSV_dangki;
import com.example.bai_tap_mahoa.Request;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLException;

public class ServerController{
    static Gson gson = new Gson();

    public static String readJsonRequest(String json){
        Gson gson = new Gson();
        Request request = gson.fromJson(json, Request.class);
        return request.getRequest();
    }
    public static void sendStatus(Socket socket, String mess){
        DataOutputStream toSever = null;
        try {
            toSever = new DataOutputStream(socket.getOutputStream());
            toSever.writeBytes(mess + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // xử lý yêu cầu từ client
    public static void checkRequest(Socket socket){
        BufferedReader fromServe = null;
        try {
            fromServe = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String dataJson = fromServe.readLine();

            String request = readJsonRequest(dataJson);
            switch (request){
                case "/getSignUp":
                    signUpUser(socket);
                    break;
                case "/getSignIn":
                    LoginUser(socket);
                    break;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static void signUpUser(Socket socket){
        // gửi xác nhận cho client
        sendStatus(socket, "oke");

        //lấy data lần 2
        BufferedReader fromServe = null;
        try {
            fromServe = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String dataJson = fromServe.readLine();

            Cl_toSV_dangki user = gson.fromJson(dataJson, Cl_toSV_dangki.class);
            try {
                UserDAO.SignUpDB(user.getTendn(), user.getMkmahoa());
                sendStatus(socket, "T");
            } catch (SQLException e) {
                System.out.println("Lỗi truy vấn cơ sở dữ liệu");
                sendStatus(socket, "F");
                throw new RuntimeException(e);
            }
        socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void LoginUser(Socket socket){
        // gửi xác nhận cho client
        sendStatus(socket, "oke");
        //lấy data lần 2
        BufferedReader fromServe = null;
        try {
            fromServe = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String dataJson = fromServe.readLine();

            Cl_toSV_dangki user = gson.fromJson(dataJson, Cl_toSV_dangki.class);
            try {
                boolean check = UserDAO.LoginDB(user.getTendn(), user.getMkmahoa());
                if(check){
                    sendStatus(socket, "T");
                }else {
                    sendStatus(socket, "F");
                }
            } catch (SQLException e) {
                sendStatus(socket, "F");
                throw new RuntimeException(e);
            }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
