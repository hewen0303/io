package com.hervey.socket.server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @program: io
 * @description: 服务端处理逻辑
 * @author: hewen
 * @create: 2019-08-27 10:25
 **/
public class BioServerHandler implements Runnable {

    private Socket socket;

    public BioServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 输入流
        BufferedReader in = null;
        // 输出流
        PrintWriter out = null;
        try {
            // 获取输入流
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 获取输出流， autoFlush自动刷新
            out = new PrintWriter(socket.getOutputStream(), true);
            String request = null;
            if ((request = in.readLine()) != null && request.length() != 0) {
                System.out.println("server get request info:" + request);
                out.println("thread:" + Thread.currentThread().getName() + ": i am server, i accept");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != out) {
                out.close();
            }
        }
    }
}
