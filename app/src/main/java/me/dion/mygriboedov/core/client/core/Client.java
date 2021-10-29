package me.dion.mygriboedov.core.client.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import me.dion.mygriboedov.core.client.exception.ServerNotFoundException;
import me.dion.mygriboedov.core.client.quiz.Question;

public class Client {
    private int score;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private final String nickname, server;

    public Client(String server, String nickname) {
        this.score = 0;
        this.server = server;
        this.nickname = nickname;
    }

    public String getServer() {
        return server;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(Question question) {
        this.score += question.getMaxScore();
    }

    private void connect() throws ServerNotFoundException {
        try {
            socket = new Socket(server, 4040);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            throw new ServerNotFoundException("Игра с таким кодом не найдена!");
        }
    }
}
