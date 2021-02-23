package org.harbor.client.client.v1;

import org.apache.http.HttpResponse;

import java.io.IOException;

/**
 * @author liurui
 * @date 2021/2/5
 */
public interface ResponseFailedHandler {

    boolean isFailed(HttpResponse response);

    void handleFailed(HttpResponse response) throws IOException;


}
