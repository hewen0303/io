# io
socket通信

// 服务端
1.首先服务端开启一个端口 8888
服务端bio通信都是通过serverSocket来进行通信；
第一步通过serversocket开启一个端口为8888的服务

ServerSocket server = new ServerSocket(8888); // 这样就开启了

2.接下来就是要让服务端持续运行进行监听等待接收命令；我们可以用accept()来让服务端进行阻塞，持续监听

server.accept(); // 为了持续监听 可以写个死循环 while (true) {} 来实现；

3. 然后就是服务端处理逻辑 handler
 我们需要通过开启一个线程的方式来处理服务端我们需要处理的逻辑； 逻辑和服务分开
 简单的线程可以直接使用 new Thread(new Runnable() {
    public void run() {
        // 逻辑
    }
 });
 在高并发的情况下,这样非常耗CPU; 推荐使用线程池来进行；

