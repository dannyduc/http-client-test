package fundamentals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class RequestExecution {

    public static void main(String[] args) throws Exception {

        // Request execution
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = httpClient.execute(httpget);
//        try {
//
//        } finally {
//            response.close();
//        }


        // HTTP request
//        URI uri = new URIBuilder()
//                .setScheme("http")
//                .setHost("www.google.com")
//                .setPath("/search")
//                .setParameter("q", "httpclient")
//                .setParameter("btnG", "Google Search")
//                .setParameter("aq", "f")
//                .setParameter("op", "")
//                .build();

//        HttpGet httpget = new HttpGet("http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=");
//        HttpGet httpget = new HttpGet(uri);
//        System.out.println(httpget.getURI());

        // HTTP response
//        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
//        System.out.println(response.getProtocolVersion());
//        System.out.println(response.getStatusLine().getStatusCode());
//        System.out.println(response.getStatusLine().getReasonPhrase());
//        System.out.println(response.getStatusLine().toString());

        // message headers
//        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
//        response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
//        response.addHeader("Set-Cookie", "c2=b; path=\"/\"; domain=\"localhost\"");
//        Header h1 = response.getFirstHeader("Set-Cookie");
//        System.out.println(h1);
//        Header h2 = response.getLastHeader("Set-Cookie");
//        System.out.println(h2);
//        Header[] hs = response.getHeaders("Set-Cookie");
//        System.out.println(hs.length);

//        HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
//        while (it.hasNext()) {
//            HeaderElement elem = it.nextElement();
//            System.out.println(elem.getName() + " = " + elem.getValue());
//            NameValuePair[] params = elem.getParameters();
//            for (int i = 0; i < params.length; i++) {
//                System.out.println(" " + params[i]);
//            }
//        }

        // http entity
//        StringEntity myEntity = new StringEntity("important message", ContentType.create("text/plain", "UTF-8"));
//        System.out.println(myEntity.getContentType());
//        System.out.println(myEntity.getContentLength());
//        System.out.println(EntityUtils.toString(myEntity));
//        System.out.println(EntityUtils.toByteArray(myEntity).length);

        // release resources
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpget = new HttpGet("http://localhost/");
//        CloseableHttpResponse response = httpClient.execute(httpget);
//        try {
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                InputStream instream = entity.getContent();
//                try {
//
//                } finally {
//                    instream.close();
//                }
//            }
//        } finally {
//            response.close();
//        }

        // consuming entity content
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("http://localhost/");
//        CloseableHttpResponse response = httpClient.execute(httpGet);
//        try {
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                entity = new BufferedHttpEntity(entity);
//                long len = entity.getContentLength();
//                if (len != -1 && len < 2048) {
//                    System.out.println(EntityUtils.toString(entity));
//                } else {
//                    // Stream content out
//                }
//            }
//        } finally {
//            response.close();
//        }

        // producing entity content
//        File file = new File("somefile.txt");
//        FileEntity entity = new FileEntity(file, ContentType.create("text/plain", "UTF-8"));
//
//        HttpPost httpPost = new HttpPost("http://localhost/action.do");
//        httpPost.setEntity(entity);

        // html forms
//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        formparams.add(new BasicNameValuePair("param1", "value1"));
//        formparams.add(new BasicNameValuePair("param2", "value2"));
//        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        // response handlers
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("http://localhost/json");
//
//        ResponseHandler<JsonObject> rh = new ResponseHandler<JsonObject>() {
//            @Override
//            public JsonObject handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
//                StatusLine statusLine = response.getStatusLine();
//                HttpEntity entity = response.getEntity();
//                if (statusLine.getStatusCode() >= 300) {
//                    throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
//                }
//                if (entity == null) {
//                    throw new ClientProtocolException("Response contains no content");
//                }
//                Gson gson = new GsonBuilder().create();
//                ContentType contentType = ContentType.getOrDefault(entity);
//                Charset charset = contentType.getCharset();
//                Reader reader = new InputStreamReader(entity.getContent(), charset);
//                return gson.fromJson(reader, JsonObject.class);
//            }
//        };

        // HttpClient interface
//        ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {
//            @Override
//            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
//                long keepAlive = super.getKeepAliveDuration(response, context);
//                if (keepAlive == -1) {
//                    keepAlive = 5000;
//                }
//                return keepAlive;
//            }
//        };
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setKeepAliveStrategy(keepAliveStrat)
//                .build();


    }
}
