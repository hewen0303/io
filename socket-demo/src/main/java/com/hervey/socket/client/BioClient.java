package com.hervey.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @program: io
 * @description: 客户端
 * @author: hewen
 * @create: 2019-08-27 14:04
 **/
public class BioClient {

    private static final String url = "127.0.0.1";

    private static final int port = 9999;

    public static void main(String[] args) {
        // 客户端连接
        Socket socket = null;
        // 输入流
        BufferedReader in = null;
        // 输出流
        PrintWriter out = null;
        try {
            for (int i=0; i< 15; i++) {
                socket = new Socket(url, port);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("hi i am client");
                String response = in.readLine();
                System.out.println(response);
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
