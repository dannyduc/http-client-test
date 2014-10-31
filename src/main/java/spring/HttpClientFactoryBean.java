package spring;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HttpClientFactoryBean implements FactoryBean<CloseableHttpClient>, DisposableBean {

    CloseableHttpClient httpClient;

    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    IdleConnectionMonitorThread monitorThread = new IdleConnectionMonitorThread(cm);

    @Value("${proxy.host}")
    String proxyHost;

    @Value("${proxy.port}")
    String proxyPort;

    Logger logger = Logger.getLogger(getClass());

    @PostConstruct
    public void init() {

//        cm.setMaxTotal(200);
//        cm.setDefaultMaxPerRoute(20);

//        HttpHost localhost = new HttpHost("variants.ingenuity.com", 80);
//        cm.setMaxPerRoute(new HttpRoute(localhost), 50);

        HttpClientBuilder builder = HttpClients.custom()
                .setConnectionManager(cm);

        if (StringUtils.isNotEmpty(proxyHost) && StringUtils.isNotEmpty(proxyPort)) {
            HttpHost proxy = new HttpHost(proxyHost, Integer.parseInt(proxyPort));
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
            builder.setRoutePlanner(routePlanner);
        }

        httpClient = builder.build();

//        monitorThread.start();
    }

    @Override
    public CloseableHttpClient getObject() throws Exception {
        return httpClient;
    }

    @Override
    public Class<?> getObjectType() {
        return CloseableHttpClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
//        logger.info("stopping idle connection monitor thread");
//        monitorThread.shutdown();

        logger.info("closing http client");
        httpClient.close();
    }
}
