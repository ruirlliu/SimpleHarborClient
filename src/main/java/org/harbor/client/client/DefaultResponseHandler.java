package org.harbor.client.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.harbor.client.client.exception.HarborClientException;
import org.harbor.client.client.flag.ResponseConfigure;

import java.io.IOException;

/**
 * @author lr
 * @date 2021/2/5
 */
public class DefaultResponseHandler implements ResponseHandler<HarborResponse> {

    private final int flag;

    public DefaultResponseHandler(int flag) {
        this.flag = flag;
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
            if (ResponseConfigure.FAILED_THROW.enabled(flag)) {
                throw new HarborClientException(statusCode, body == null ? "response content empty" : body);
            }
        }
        harborResponse.setBody(body);
        return harborResponse;
//      T body = mapper.readValue(entity.getContent(), new TypeReference<T>() {});
    }
}
