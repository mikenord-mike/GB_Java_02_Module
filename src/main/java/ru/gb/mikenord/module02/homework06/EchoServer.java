package ru.gb.mikenord.module02.homework06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Ждём подключения клиента...");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String message = inStream.readUTF();
                        if ("/end".equalsIgnoreCase(message)) {
                            outStream.writeUTF(message);
                            System.out.println("Завершение работы...");
                            break;
                        }
                        outStream.writeUTF("Echo: " + message);
                        System.out.println("Киент: " + message);
                    }
                } catch (IOException e) {
                    System.out.println("Соединение разорвано");
                }
            }).start();

            Thread scannerThread = new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();
                    try {
                        outStream.writeUTF(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if ("/end".equalsIgnoreCase(message)) {
                        System.out.println("Завершение работы...");
                        break;
                    }
                }
            });
            scannerThread.setDaemon(true);
            scannerThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
