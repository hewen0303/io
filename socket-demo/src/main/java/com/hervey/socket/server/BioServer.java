package com.hervey.socket.server;

import com.hervey.socket.server.handler.BioServerHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: io
 * @description: 服务端
 * @author: hewen
 * @create: 2019-08-27 10:14
 **/
public class BioServer {

    private static final int port = 8888;

    public static void main(String[] args) {
        // socket服务
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("server is start");
            while (true) {
                // 持续监听, 获取客户端返回
                Socket socket = server.accept();
                new Thread(new BioServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
