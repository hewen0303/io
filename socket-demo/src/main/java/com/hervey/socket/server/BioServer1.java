package com.hervey.socket.server;

import com.hervey.socket.server.handler.BioServerHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: io
 * @description: 服务端
 * @author: hewen
 * @create: 2019-08-27 11:52
 **/
public class BioServer1 {

    private static final int port = 9999;

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            ExecutorService service = Executors.newFixedThreadPool(10);

            server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();
                service.submit(new BioServerHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
