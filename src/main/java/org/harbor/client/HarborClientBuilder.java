package org.harbor.client;

import cn.hutool.core.codec.Base64;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.harbor.client.flag.ResponseConfigure;
import org.harbor.client.op.impl.DefaultHarborClientV1;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
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

    private boolean ignoreSsl;

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

    public HarborClientBuilder setIgnoreSsl(boolean ignoreSsl) {
        this.ignoreSsl = ignoreSsl;
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
            .setConnectionManager(new PoolingHttpClientConnectionManager())
            .setSSLSocketFactory(buildSsl())
            .build();
    }

    private String accessToken() {
        return Base64.encode(username + ":" + password);
    }

    private LayeredConnectionSocketFactory buildSsl() {
        if (ignoreSsl) {
            SSLContext sslContext = null;
            try {
                sslContext = SSLContexts.custom().build();
                sslContext.init(null, new TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                e.printStackTrace();
            }
            if (sslContext == null) {
                sslContext = SSLContexts.createDefault();
            }
            return new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        }
        return null;
    }



    public static class HttpsTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) { }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) { }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }

    }
}
