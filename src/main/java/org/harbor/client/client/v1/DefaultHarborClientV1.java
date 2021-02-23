package org.harbor.client.client.v1;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.harbor.client.v1.exception.HarborClientException;
import com.harbor.client.v1.op.Projects;
import org.apache.http.HttpStatus;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author liurui
 * @date 2021/2/5
 */
public class DefaultHarborClientV1 implements HarborClientV1 {

    private final ObjectMapper objectMapper = new ObjectMapper();


    private final String url;

    private final String accessToken;

    private final CloseableHttpClient httpClient;

    private final ResponseHandler<HarborResponse<String>> handler;// = new DefaultResponseHandler(configure);

    public DefaultHarborClientV1(String url, String accessToken, CloseableHttpClient httpClient, int configure) {
        this.url = url;
        this.accessToken = accessToken;
        this.httpClient = httpClient;
        // = ResponseConfigure.DEFAULT_CONFIGURE;
        handler = new DefaultResponseHandler(configure);
    }

    @Override
    public Projects projects() {
        return new Projects(this, API_BASE);
    }


    public <T> List<T> list(String path, Class<T> object) throws HarborClientException {
        HttpGet httpGet = new HttpGet(url + path);
        addAccessTokenHeader(httpGet);
        try {
            HarborResponse<String> response = httpClient.execute(httpGet, handler);
            if (!response.success()) {
                return Collections.emptyList();
            }
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, object);
            List<T> list = objectMapper.readValue(response.getBody(), collectionType);
            return list == null ? Collections.emptyList() : list;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public <T> T get(String path, Class<T> object) throws IOException {
        HttpGet httpGet = new HttpGet(url + path);
        addAccessTokenHeader(httpGet);
        HarborResponse<String> response = httpClient.execute(httpGet, handler);
        if (response.success()) {
            return objectMapper.readValue(response.getBody(), object);
        }
        return null;
    }

    public HarborResponse<String> put(String path, Object o) throws IOException {
        HttpPut httpPut = new HttpPut(url + path);
        addAccessTokenHeader(httpPut);
        try {
            httpPut.setEntity(new StringEntity(objectMapper.writeValueAsString(o), ContentType.APPLICATION_JSON));
            HarborResponse<String> execute = httpClient.execute(httpPut, handler);
            return execute;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public HarborResponse<String> post(String path, Object o) throws HarborClientException {
        HttpPost httpPost = new HttpPost(url + path);
        addAccessTokenHeader(httpPost);
        try {
            httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(o), ContentType.APPLICATION_JSON));
            HarborResponse<String> execute = httpClient.execute(httpPost, handler);
            return execute;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public HarborResponse<String> head(String path) throws HarborClientException {
        HttpHead httpHead = new HttpHead(url + path);
        addAccessTokenHeader(httpHead);
        try {
            HarborResponse<String> execute = httpClient.execute(httpHead, handler);
            return execute;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public HarborResponse<String> delete(String path, Object o) throws HarborClientException {
        HttpDelete httpDelete = new HttpDelete(url + path);
        addAccessTokenHeader(httpDelete);
        try {
            HarborResponse<String> execute = httpClient.execute(httpDelete, handler);
            return execute;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }



//    @Override
//    public ResponseFailedHandler getFailedHandler() {
//        return failedHandler;
//    }
//
//    @Override
//    public void setFailedHandler(ResponseFailedHandler handler) {
//        Objects.requireNonNull(handler, "ResponseFailedHandler can not be null");
//        this.failedHandler = handler;
//    }

//    private void handlerResponse(HttpResponse response) throws IOException {
//        ResponseFailedHandler failedHandler = getFailedHandler();
//        if (failedHandler.isFailed(response)) {
//            failedHandler.handleFailed(response);
//        }
//    }

    private HttpRequestBase addAccessTokenHeader(HttpRequestBase httpRequestBase) {
        if (StrUtil.isNotEmpty(accessToken)) {
            httpRequestBase.addHeader("Authorization", "Basic " + accessToken);
        }
        return httpRequestBase;
    }

}
