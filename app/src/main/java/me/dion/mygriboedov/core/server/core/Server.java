package me.dion.mygriboedov.core.server.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Server implements Serializable {
    private final LinkedList<ServerAdapter> connections = new LinkedList<>();
    private final LinkedList<ServerAdapter> toDisconnect = new LinkedList<>();
    private final HashMap<String, Integer> results = new HashMap<>();
    private Socket clientSocket;
    private final ArrayList<String> msgs = new ArrayList<>();
    private ServerSocket serverSocket;
    private BufferedReader reader;
    private boolean canConnect = true;
    private BufferedWriter writer;
    public String address;
    public static final int port = 4040;

    public Server(String address) {
        this.address = address;
    }

    public void setTest(String s) {
        msgs.add(s);
    }

    public String getTest() {
        return msgs.size() + "";
    }

    public void startServer() {
        Thread thread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                while (canConnect) {
                    clientSocket = serverSocket.accept();
                    try {
                        connections.add(new ServerAdapter(clientSocket, this));
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
