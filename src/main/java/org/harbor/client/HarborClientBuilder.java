package org.harbor.client;

import lombok.Getter;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.harbor.client.op.impl.DefaultHarborClientV2;

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
@Getter
public class HarborClientBuilder {

    public HarborClientBuilder() { }


    private String url;

    private String username;

    private String password;

    private int connectionTimeout;

    private boolean ignoreSsl;

    public HarborClientBuilder setUrl(String url) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        this.url = url;
        return this;
    }


    public HarborClientBuilder setUsername(String username) {
        this.username = username;
        return this;
    }


    public HarborClientBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public HarborClientBuilder setConnectionTimeout(Integer connectionTimeout, TimeUnit timeUnit) {
        this.connectionTimeout = (int) timeUnit.toMillis(connectionTimeout);
        return this;
    }

    public HarborClientBuilder setIgnoreSsl(boolean ignoreSsl) {
        this.ignoreSsl = ignoreSsl;
        return this;
    }



    public HarborClientV2 buildV2() {
        return new DefaultHarborClientV2(url, createClientBuilder().build());
    }

    private HttpClientBuilder createClientBuilder() {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.username, this.password));
        return HttpClientBuilder.create()
            .setDefaultRequestConfig(RequestConfig.custom()
                .setConnectTimeout(connectionTimeout)
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .build())
            .setConnectionManager(new PoolingHttpClientConnectionManager())
            .setSSLSocketFactory(buildSsl())
            .setDefaultCredentialsProvider(credsProvider);
    }

    private LayeredConnectionSocketFactory buildSsl() {
        if (ignoreSsl) {
            SSLContext sslContext;
            try {
                sslContext = SSLContexts.custom().build();
                sslContext.init(null, new TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                throw new RuntimeException(e);
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
