package me.dion.mygriboedov.core.server.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerAdapter extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ServerAdapter(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {

            }
        } catch (IOException e) {
            // empty catch block
        }
    }
}
