package org.harbor.client.client.v2;

import com.harbor.client.v2.operation.ProjectOp;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author liurui
 * @date 2021/2/4
 */
public class DefaultHarborClient implements HarborClient {

    private CloseableHttpClient httpClient;
    private String url;
    private String authorization;

    public DefaultHarborClient(CloseableHttpClient httpClient, String url, String authorization) {
        this.httpClient = httpClient;
        this.url = url;
        this.authorization = authorization;
    }


    @Override
    public ProjectOp projects() {
        return new ProjectOp();
    }

    @Override
    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getAuthorization() {
        return authorization;
    }
}
