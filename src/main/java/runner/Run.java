package runner;

import server.Server;

public class Run {
    public static void main(String[] args) throws Throwable {
        Server socketServer = new Server(8080,123);
        socketServer.start();
    }
}
