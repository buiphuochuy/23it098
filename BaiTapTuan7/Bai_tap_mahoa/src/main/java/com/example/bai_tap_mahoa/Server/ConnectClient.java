package com.example.bai_tap_mahoa.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectClient {
    int port = 12312;
    public ConnectClient() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Open port " + port );
        while (true){

            Socket socket = serverSocket.accept();
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    ServerController.checkRequest(socket);
                }
            });
            th.start();
        }

    }


}
