package me.dion.mygriboedov.core.client.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Serializable {
    private Socket socket;
    private final String nickname;
    private final InetAddress server;
    private WriteThread writeThread;
    private ReadThread readThread;

    public Client(InetAddress server, String nickname) {
        this.server = server;
        this.nickname = nickname;
    }

    public WriteThread getWriteThread() {
        return writeThread;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean connect() {
        try {
            socket = new Socket(server, 4040); // Connect to server
            readThread = new ReadThread(socket, this);
            writeThread = new WriteThread(socket, this);
            readThread.start();
            writeThread.start();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void setQuestion(String q) {

    }

    public static class ReadThread extends Thread {
        private BufferedReader reader;

        public ReadThread(Socket socket, Client client) {
            try {
                InputStream input = socket.getInputStream();
                reader = new BufferedReader(new InputStreamReader(input));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void run() {
            while (true) {
                try {
                    String response = reader.readLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
        }
    }

    public static class WriteThread extends Thread {
        private PrintWriter writer;

        public WriteThread(Socket socket, Client client) {
            try {
                OutputStream output = socket.getOutputStream();
                writer = new PrintWriter(output, true);
            } catch (IOException ex) {
                System.out.println("Error getting output stream: " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        public void sendMessage(String message_) {
            writer.println(message_);
        }
    }
}
