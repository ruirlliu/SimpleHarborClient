package org.harbor.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.harbor.client.HarborResponse;
import org.harbor.client.exception.HarborClientException;

import java.io.IOException;

/**
 * @author lr
 * @date 2021/2/5
 */
public class DefaultResponseHandler implements ResponseHandler<HarborResponse> {


    public DefaultResponseHandler() {
    }

    @Override
    public HarborResponse handleResponse(HttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        HarborResponse harborResponse = new HarborResponse(statusCode);
        HttpEntity entity = response.getEntity();
        String body = null;
        if (entity != null) {
            body = EntityUtils.toString(entity);
        }
        if (!harborResponse.success()) {
            throw new HarborClientException(statusCode, body == null ? "response content empty" : body);
        }
        harborResponse.setBody(body);
        return harborResponse;
    }
}
