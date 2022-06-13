package ru.gb.mikenord.module02.homework06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    private Socket socket;
    private DataInputStream inStream;
    private DataOutputStream outStream;

    public static void main(String[] args) {
        new EchoClient().start();
    }

    private void start() {
        try {
            openConnection();
            Thread scannerThread = new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();
                    sendMessage(message);
                    if ("/end".equalsIgnoreCase(message)) {
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

    private void sendMessage(String message) {
        try {
            outStream.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openConnection() throws IOException {
        socket = new Socket("127.0.0.1", 8189);
        inStream = new DataInputStream(socket.getInputStream());
        outStream = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String message = inStream.readUTF();
                    if ("/end".equalsIgnoreCase(message)) {
                        System.out.println("Завершение работы...");
                        break;
                    }
                    System.out.println("Сервер: " + message);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                closeConnection();
            }
        }).start();
    }

    private void closeConnection() {
        if (inStream != null) {
            try {
                inStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (outStream != null) {
            try {
                outStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
