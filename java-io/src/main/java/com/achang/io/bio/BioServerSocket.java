package com.achang.io.bio;

import org.apache.log4j.Logger;

import java.net.ServerSocket;
import java.net.Socket;

public class BioServerSocket {

    private static final Logger logger = Logger.getLogger(BioServerSocket.class);

    //默认监听端口
    private static final int DEFAULT_PROT = 2345;

    //单例
    private static ServerSocket server;


    public static void start() throws Exception {
        //start default port
        start(DEFAULT_PROT);
    }

    public synchronized static void start(int port) throws Exception {

        if (server != null) {
            return;//service has started..
        }

        try {
            //通过构造函数构造ServerSocket
            server = new ServerSocket(port);
            logger.info("server is started ;listen port is " + port);

            while (true) {
                Socket socket = server.accept();
                new Thread(new SocketHandler(socket)).start();
            }

        } catch (Exception e) {
            logger.error(e, e);
        } finally {
            if (server != null) {
                logger.info("close server...");
                server.close();
                server = null;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        start();

    }


}
