package me.dion.mygriboedov.core.client.core;

public class Manager {
    static Client client;

    public Manager(Client client) {
        Manager.client = client;
    }

    public static Client getClient() {
        return client;
    }
}
