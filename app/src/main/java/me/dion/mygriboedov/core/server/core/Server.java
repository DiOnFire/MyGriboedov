package me.dion.mygriboedov.core.server.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private BufferedReader reader;
    private BufferedWriter writer;
    public static final int port = 4040;

}
