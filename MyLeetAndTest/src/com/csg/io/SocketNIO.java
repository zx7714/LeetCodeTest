package com.csg.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class SocketNIO {
    public static void main(String[] args) throws IOException {


        LinkedList<SocketChannel> clients = new LinkedList<>();

        ServerSocketChannel ss = ServerSocketChannel.open();  //服务端开启监听：接受客户端
        ss.bind(new InetSocketAddress(9090));
        ss.configureBlocking(false);
        while (true) {
            SocketChannel client = ss.accept(); // 没有连接返回 NULL

            if (client == null) {

            } else {
                client.configureBlocking(false); //设置非阻塞
                int port = client.socket().getPort();
                System.out.println("===== client： " + port + "=====");
                clients.add(client);
            }
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);  //开辟缓冲区
            //遍历已经链接进来的客户端能不能读写数据
            for (SocketChannel c : clients) {
                int num = c.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    byte[] aaa = new byte[buffer.limit()];
                    buffer.get(aaa);

                    String b = new String(aaa);
                    System.out.println("=====" + c.socket().getPort() + " : " + b + " =======");
                    buffer.clear();
                }
            }
        }
    }
}
