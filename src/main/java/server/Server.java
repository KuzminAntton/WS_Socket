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
//            this.s = s;
//            this.is = s.getInputStream();
            this.socket = socket;

        }

        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                rq = new Request();
                rq.parseRequest(br);

//                System.out.println(rq.toString());

                rp = new Response(socket.getOutputStream());

//                if(rq.getMethod().contains(CommonConstants.GET)) {
//                    HelloHandler helloHandler = new HelloHandler();
//                    helloHandler.handle(rq, rp);
//                    System.out.println(rq.getMethod() + " " + rq.getBody());
//                } else {
//                    System.out.println("I'm in");
//                    AddBook addBook = new AddBook();
//                    System.out.println(rq.getMethod() + " " + rq.getBody());
//                    addBook.handle(rq, rp);
//                }

                HandlerFactory handlerFactory = new HandlerFactory();
                handlerFactory.getHndlerDirection().direct(rq,rp);




                //System.out.println(rp.toString());

//                writeResponse("hello");
            } catch (Throwable t) {
                /*do nothing*/
                t.printStackTrace();
            }

            finally {
                try {
                    socket.close();
                } catch (Throwable t) {
                    /*do nothing*/
                    t.printStackTrace();
                }
            }

        }
    }
}
