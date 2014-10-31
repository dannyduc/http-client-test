package connmgmt;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ConnectionManagement {
    public static void main(String[] args) throws Exception {

        // simple connection manager
//        HttpClientContext context = HttpClientContext.create();
//        HttpClientConnectionManager connMgr = new BasicHttpClientConnectionManager();
//        HttpRoute route = new HttpRoute(new HttpHost("localhost", 80));
//        ConnectionRequest connRequest = connMgr.requestConnection(route, null);
//        HttpClientConnection conn = connRequest.get(10, TimeUnit.SECONDS);
//        try {
//            if (!conn.isOpen()) {
//                connMgr.connect(conn, route, 1000, context);
//                connMgr.routeComplete(conn, route, context);
//            }
//        } finally {
//            connMgr.releaseConnection(conn, null, 1, TimeUnit.MINUTES);
//        }

        // pooling connection manager
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//        cm.setMaxTotal(200);
//        cm.setDefaultMaxPerRoute(20);
//        HttpHost localhost = new HttpHost("localhost", 80);
//        cm.setMaxPerRoute(new HttpRoute(localhost), 50);
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setConnectionManager(cm)
//                .build();

        // multithreaded request execution
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setConnectionManager(cm)
//                .build();
//
//        // URIs to perform GETs on
//        String[] urisToGet = {
//                "http://www.domain1.com/",
//                "http://www.domain2.com/",
//                "http://www.domain3.com/",
//                "http://www.domain4.com/"
//        };
//
//        GetThread[] threads = new GetThread[urisToGet.length];
//        for (int i = 0; i < threads.length; i++) {
//            HttpGet httpget = new HttpGet(urisToGet[i]);
//            threads[i] = new GetThread(httpClient, httpget);
//        }
//
//        for (int i = 0; i < threads.length; i++) {
//            threads[i].start();
//        }
//
//        for (int i = 0; i < threads.length; i++) {
//            threads[i].join();
//        }
    }

    // eviction policy
    public static class IdleConnectionMonitorThread extends Thread {
        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    wait(5000);
                    connMgr.closeExpiredConnections();
                    connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                }
            } catch (InterruptedException ex) {
                // terminate
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }

//
//    static class GetThread extends Thread {
//        private final CloseableHttpClient httpClient;
//        private final HttpContext context;
//        private final HttpGet httpGet;
//
//        GetThread(CloseableHttpClient httpClient, HttpGet httpGet) {
//            this.httpClient = httpClient;
//            this.context = HttpClientContext.create();
//            this.httpGet = httpGet;
//        }
//
//        @Override
//        public void run() {
//            try {
//                CloseableHttpResponse response = httpClient.execute(httpGet, context);
//                try {
//                    HttpEntity entity = response.getEntity();
//                } finally {
//                    response.close();
//                }
//            } catch (ClientProtocolException ex) {
//
//            } catch (IOException ex) {
//
//            }
//        }
//    }
}
