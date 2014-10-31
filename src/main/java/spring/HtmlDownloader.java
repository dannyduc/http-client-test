package spring;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HtmlDownloader {

    @Autowired
    CloseableHttpClient httpClient;

    public String download(String url) throws Exception {
        String content = null;
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = null;
        try {
            entity = response.getEntity();
            if (entity != null) {
                content = EntityUtils.toString(entity);
            }
        } finally {
            EntityUtils.consumeQuietly(entity);
        }
        return content;
    }
}
