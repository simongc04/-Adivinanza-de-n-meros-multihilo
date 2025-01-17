package guessserver;


import java.io.*;
import java.net.*;
import java.util.Random;

public class GuessServer {
    private static final int PORT = 12345;
    private static final int SECRET_NUMBER = new Random().nextInt(100) + 1;

    public static void main(String[] args) {
        System.out.println("Servidor iniciado. Número secreto generado: " + SECRET_NUMBER);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado.");
                new GuessThread(clientSocket, SECRET_NUMBER).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
