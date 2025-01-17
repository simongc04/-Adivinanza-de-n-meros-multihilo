/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guessserver;

/**
 *
 * @author simon
 */
import java.io.*;
import java.net.*;

public class GuessThread extends Thread {
    private Socket socket;
    private int secretNumber;

    public GuessThread(Socket socket, int secretNumber) {
        this.socket = socket;
        this.secretNumber = secretNumber;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            output.println("¡Bienvenido! Adivina un número entre 1 y 100.");

            String guess;
            while ((guess = input.readLine()) != null) {
                try {
                    int number = Integer.parseInt(guess);

                    if (number == secretNumber) {
                        output.println("¡Correcto! Has adivinado el número.");
                        break;
                    } else if (number < secretNumber) {
                        output.println("El número es mayor. Intenta de nuevo.");
                    } else {
                        output.println("El número es menor. Intenta de nuevo.");
                    }
                } catch (NumberFormatException e) {
                    output.println("Entrada no válida. Ingresa un número.");
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
