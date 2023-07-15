package com.csg.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketBIO {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9090, 20);
        System.out.println("===== new ServerSocket(9090) =====");

        while (true) {
            Socket client = server.accept();  //阻塞
            System.out.println("==== client：" + client.getPort() + " =====");
            new Thread(() -> {
                InputStream in = null;
                try {
                    in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true) {
                        String dataline = reader.readLine(); //阻塞
                        if (null != dataline) {
                            System.out.println(dataline);
                        } else {
                            client.close();
                            break;
                        }
                    }
                    System.out.println("===== 客户端断开 =====");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
