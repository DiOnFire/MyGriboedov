package me.dion.mygriboedov.core.server.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class Server {
    private ArrayList<ServerAdapter> connections = new ArrayList<>();
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private BufferedReader reader;
    private BufferedWriter writer;
    public String address;
    public static final int port = 4040;

    public Server(String address) {
        this.address = address;
    }

    public void startServer() throws IOException {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                Socket socket = server.accept();
                try {
                    connections.add(new ServerAdapter(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        }
    }
}
