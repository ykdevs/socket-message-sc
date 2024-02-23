package com.example.socketmessagesc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static void runServer(AppConfig appConfig) {
        try (ServerSocket serverSocket = new ServerSocket(appConfig.getPort())) {
            System.out.printf("Server is running on port %d...%n", appConfig.getPort());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            out.println("どうもこんにちは");
            System.out.println("Server: どうもこんにちは");
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client: " + inputLine);
                switch (inputLine.trim()) {
                    case "さようなら":
                        out.println("さようなら");
                        System.out.println("Server: さようなら");
                        clientSocket.close();
                        return;
                    case "こんにちは":
                        out.println("こんにちは");
                        System.out.println("Server: こんにちは");
                        break;
                    case "ありがとうございます":
                        out.println("どういたしまして");
                        System.out.println("Server: どういたしまして");
                        break;
                    default:
                        out.println("もう一度お願いします");
                        System.out.println("Server: もう一度お願いします");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
