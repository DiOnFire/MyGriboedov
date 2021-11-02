package me.dion.mygriboedov.core.server.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server implements Serializable {
    private LinkedList<ServerAdapter> connections = new LinkedList<>();
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private BufferedReader reader;
    private boolean canConnect = true;
    private BufferedWriter writer;
    public String address;
    public static final int port = 4040;

    public Server(String address) {
        this.address = address;
    }

    public void startServer() {
        Thread thread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                while (canConnect) {
                    clientSocket = serverSocket.accept();
                    try {
                        connections.add(new ServerAdapter(clientSocket));
                    } catch (IOException e) {
                        clientSocket.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void closeServer() throws IOException {
        clientSocket.close();
        reader.close();
        writer.close();
        serverSocket.close();
    }

    public int getConnectionsSize() {
        return connections.size();
    }

    public boolean isInterruptedConnections() {
        return !canConnect;
    }

    public void interruptConnections() {
        canConnect = false;
    }
}
