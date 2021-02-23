package org.harbor.client.client.v1;

import com.harbor.client.v1.exception.HarborClientException;
import com.harbor.client.v1.flag.ResponseConfigure;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author liurui
 * @date 2021/2/5
 */
public class DefaultResponseHandler implements /*ResponseFailedHandler,*/ ResponseHandler<HarborResponse<String>> {

//    private static ObjectMapper mapper = new ObjectMapper();

    private final int flag;

    public DefaultResponseHandler(int flag) {
        this.flag = flag;
    }

    /*@Override
    public boolean isFailed(HttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        return statusCode != HttpStatus.SC_OK;
    }

    @Override
    public void handleFailed(HttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String msg = EntityUtils.toString(entity);
            throw new HarborClientException(statusCode, msg);
        }
        throw new HarborClientException(statusCode, "response content empty");
    }*/

    @Override
    public HarborResponse<String> handleResponse(HttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        HarborResponse<String> harborResponse = new HarborResponse<>(statusCode);
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
