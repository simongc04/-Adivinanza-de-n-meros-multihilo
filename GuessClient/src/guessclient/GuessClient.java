/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.io.*;
import java.net.*;

import javax.swing.*;
import java.awt.*;


public class GuessClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private JTextField guessField;
    private JTextArea chatArea;

    public GuessClient() {
        JFrame frame = new JFrame("Adivina el Número");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        guessField = new JTextField();
        guessField.addActionListener(e -> sendGuess());
        frame.add(guessField, BorderLayout.SOUTH);

        frame.setVisible(true);

        connectToServer();
    }

    private void connectToServer() {
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            chatArea.append(input.readLine() + "\n");
        } catch (IOException e) {
            chatArea.append("No se pudo conectar al servidor.\n");
        }
    }

    private void sendGuess() {
        String guess = guessField.getText();
        output.println(guess);

        try {
            String response = input.readLine();
            chatArea.append("Tú: " + guess + "\n");
            chatArea.append("Servidor: " + response + "\n");

            if (response.contains("¡Correcto!")) {
                guessField.setEnabled(false);
            }
        } catch (IOException e) {
            chatArea.append("Error de conexión.\n");
        }

        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GuessClient::new);
    }
}
