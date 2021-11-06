package me.dion.mygriboedov.core.server.core;

import android.content.Intent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import me.dion.mygriboedov.CreateGameActivity;

public class ServerAdapter extends Thread {
    private final Socket socket;
    private final BufferedReader reader;
    private final Server server;
    private String answer;
    private final BufferedWriter writer;

    public ServerAdapter(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public void run() {
        String message;
        try {
            while (true) {
                message = reader.readLine();
                writer.write(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
