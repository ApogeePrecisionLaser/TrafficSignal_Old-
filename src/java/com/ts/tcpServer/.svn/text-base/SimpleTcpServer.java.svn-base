/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.tcpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Tarun
 */
public class SimpleTcpServer {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket clientSocket;
        BufferedReader reader;
        PrintWriter out ;
        int serverPort = 8060;
        try {
            serverSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("Waiting for Client to connect...");
                clientSocket = serverSocket.accept();
                System.out.println("Client connected. IP: " + clientSocket.getInetAddress());
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String nextLine = br.readLine();
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                while (!nextLine.equalsIgnoreCase("exit")) {
                    out.println(nextLine);
                    nextLine = br.readLine();
                }


                byte[] bytes = new byte[50];
                InputStream inputStream = clientSocket.getInputStream();
                int read = inputStream.read(bytes);
                for (int i = 0; i < read; i++) {
                System.out.println("Client:" + bytes[i]+ "Int Value is :"+ (int)bytes[i]);

//                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                String nextLineFromClient = reader.readLine();
//                System.out.println(nextLineFromClient);
//                while (nextLineFromClient != null) {
//                    System.out.println("Client: " + nextLineFromClient);
//                    nextLineFromClient = reader.readLine();
                }
            }
        } catch (IOException ioEx) {
            System.out.println("TCPServer main() Error: " + ioEx);
        }
    }
}