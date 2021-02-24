package org.harbor.client.client.v2;

import org.apache.http.impl.client.CloseableHttpClient;
import org.harbor.client.client.v2.operation.ProjectOp;

/**
 * @author liurui
 * @date 2021/2/4
 */
public interface HarborClient {

    String API_BASE = "/api/v2.0";

    ProjectOp projects();

    CloseableHttpClient getHttpClient();

    String getUrl();

    String getAuthorization();


}
