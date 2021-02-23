package org.harbor.client.client.v2;

import com.harbor.client.v2.operation.ProjectOp;
import org.apache.http.impl.client.CloseableHttpClient;

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
