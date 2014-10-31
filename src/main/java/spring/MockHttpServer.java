package spring;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class MockHttpServer {

    public static void main(String[] args) throws Exception {
//        System.setProperty("sun.net.httpserver.maxIdleConnections", "200")
        System.setProperty("sun.net.httpserver.maxReqTime", "10");
        System.setProperty("sun.net.httpserver.maxRspTime", "10");

        int port = 8000;
        int stopPort = 8888;
        String context = "/";

        HttpServer server = HttpServer.create(new InetSocketAddress(port), stopPort);
        server.createContext(context, new HttpHandler() {
            @Override
            public void handle(HttpExchange t) throws IOException {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                String response = "aok";
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        server.start();
//        server.stop(stopPort);
    }
}
