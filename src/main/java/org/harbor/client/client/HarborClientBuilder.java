package org.harbor.client.client;

import cn.hutool.core.codec.Base64;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.harbor.client.client.v1.DefaultHarborClientV1;
import org.harbor.client.client.v1.HarborClientV1;
import org.harbor.client.client.v1.flag.ResponseConfigure;

import java.util.concurrent.TimeUnit;

/**
 * @author lr
 * @date 2021/2/4
 */
public class HarborClientBuilder {

    public HarborClientBuilder() { }


    private String url;

    private String username;

    private String password;

    private int connectionTimeout;

    private int configure = ResponseConfigure.DEFAULT_CONFIGURE;

    public String getUrl() {
        return url;
    }

    public HarborClientBuilder setUrl(String url) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public HarborClientBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public HarborClientBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public HarborClientBuilder setConnectionTimeout(Integer connectionTimeout, TimeUnit timeUnit) {
        this.connectionTimeout = (int) timeUnit.toMillis((long) connectionTimeout);
        return this;
    }

    public HarborClientBuilder enable(ResponseConfigure... cs) {
        for (ResponseConfigure c : cs) {
            configure |= c.getMask();
        }
        return this;
    }

    public HarborClientBuilder disable(ResponseConfigure... cs) {
        for (ResponseConfigure c : cs) {
            configure &= ~c.getMask();
        }
        return this;
    }

    public HarborClientV1 buildV1() {
        return new DefaultHarborClientV1(url, accessToken(), createClient(), configure);
    }

    private CloseableHttpClient createClient() {
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(connectionTimeout)
                        .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                        .build())
//                .setConnectionManager(new PoolingHttpClientConnectionManager())
                .build();
    }

    private String accessToken() {
        return Base64.encode(username + ":" + password);
    }
}
