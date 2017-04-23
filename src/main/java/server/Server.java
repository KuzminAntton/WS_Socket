package server;

import handler.HandlerFactory;
import method.Request;
import method.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ExecutorService pool;
    private int port;
    private int poolSize;

    public Server() {
    }

    public Server(int port, int poolSize) {
        this.port = port;
        this.poolSize = poolSize;
    }

    public void start() throws Throwable {

        pool = Executors.newFixedThreadPool(poolSize);
        ServerSocket srSocket = new ServerSocket(port);
        while (true) {
            Socket sock = srSocket.accept();
            pool.submit(new SocketProcessor(sock));
        }
    }

    public void stop() {
        pool.shutdown();
    }

    private static class SocketProcessor implements Runnable {

        private Request rq;
        private Response rp;
        private Socket socket;

        private SocketProcessor(Socket socket) throws Throwable {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                rq = new Request(br);

                rp = new Response(socket.getOutputStream());

                HandlerFactory handlerFactory = new HandlerFactory();
                handlerFactory.getHandlerRouter().direct(rq,rp);

            } catch (Throwable t) {
                t.printStackTrace();
            }

            finally {
                try {
                    socket.close();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

        }
    }
}
