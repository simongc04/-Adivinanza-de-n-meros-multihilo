/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guestclientcommandline;

/**
 *
 * @author simon
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GuessClientCommandLine {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(input.readLine());

            while (true) {
                System.out.print("Tu suposición: ");
                String guess = scanner.nextLine();
                output.println(guess);

                String response = input.readLine();
                System.out.println(response);

                if (response.contains("¡Correcto!")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
