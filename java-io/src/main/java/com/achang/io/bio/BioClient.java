package com.achang.io.bio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by changqi
 * data : 11/27/17.
 */

public class BioClient {

    private static int DEFAULT_SERVER_PORT = 2345;

    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void  send(String msg){
        send(DEFAULT_SERVER_PORT,msg);
    }

    public static void send(int port,String message){
        System.out.println("send message : " + message);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket(DEFAULT_SERVER_IP,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            out.println(message);
            System.out.println("response is :: " + in.readLine());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in !=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out !=null){
                out.close();
            }
            if(socket !=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        send("xxxx");
    }



}
