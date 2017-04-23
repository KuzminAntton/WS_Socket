package com.epam.ws_socet.view;

import com.epam.ws_socet.server.Server;

public class Main {
    public static void main(String[] args) throws Throwable {
        Server socketServer = new Server(8080,123);
        socketServer.start();
    }
}
