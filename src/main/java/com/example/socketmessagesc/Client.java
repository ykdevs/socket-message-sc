package com.example.socketmessagesc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void runClient(AppConfig appConfig) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Socket socket = null;
            PrintWriter out = null;
            String userInput;
            BufferedReader in = null;
            while ((userInput = prompt(reader, isClosed(socket))) != null) {
                switch (userInput) {
                    case "つなぎます":
                        if (!isClosed(socket)) {
                            System.out.println("Client: すでに接続済みです。");
                            break;
                        }
                        socket = new Socket(appConfig.getIp(), appConfig.getPort());
                        out = new PrintWriter(socket.getOutputStream(), true);
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        System.out.println("Client: サーバに接続します。");
                        String serverResponse = in.readLine();
                        System.out.println("Server: " + serverResponse);
                        break;
                    case "おわります":
                        if (!isClosed(socket)) {
                            System.out.println("Client: サーバにさようならをしてください。");
                            break;
                        }
                        assert out != null;
                        out.println(userInput);
                        System.out.println("Disconnected from server.");
                        return;
                    case "こんにちは":
                    case "こんばんは":
                    case "ありがとうございます":
                    case "さようなら":
                        if (isClosed(socket)) {
                            System.out.println("Client: サーバにつないでください。");
                            break;
                        }
                        out.println(userInput);
                        serverResponse = in.readLine();
                        System.out.println("Server: " + serverResponse);
                        if (userInput.equals("さようなら")) {
                            socket.close();
                        }
                        break;
                    default:
                        System.out.println("もう一度入力してください。");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isClosed(Socket socket) {
        return (socket == null || socket.isClosed());
    }

    private static String prompt(BufferedReader reader, boolean isClosed) throws IOException {
        if (isClosed) {
            System.out.print("Prompt(未接続): ");
        } else {
            System.out.print("Prompt(接続済): ");
        }
        return reader.readLine();
    }
}
