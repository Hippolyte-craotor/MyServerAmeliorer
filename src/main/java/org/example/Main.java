package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        // Définition du port sur lequel le serveur écoutera les connexions
        int SERVER_PORT = 5000;

        try {
            // Création d'un serveur sur le port spécifié
            ServerSocket server = new ServerSocket(SERVER_PORT);
            System.out.println("Le serveur a démarré sur le port : " + SERVER_PORT);

            // Attente d'une connexion client
            Socket socket = server.accept();
            System.out.println("Un client s'est connecté depuis : " + socket.getInetAddress());

            // Récupération du flux d'entrée
            InputStream is = socket.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(is));

            String message;
            System.out.println("En attente des messages du client...");

            // Boucle pour recevoir les messages
            while ((message = input.readLine()) != null) {
                System.out.println("Message reçu : " + message);

                if (message.equalsIgnoreCase("bye")) {
                    System.out.println("Le client s'est déconnecté.");
                    break;
                }
            }

            // Fermeture des ressources
            input.close();
            socket.close();
            server.close();
        } catch (IOException exception) {
            System.err.println("Une erreur est survenue : " + exception.getMessage());
        }
    }
}
