package org.harbor.client.client.op.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
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
import org.harbor.client.client.DefaultResponseHandler;
import org.harbor.client.client.HarborClientV1;
import org.harbor.client.client.HarborResponse;
import org.harbor.client.client.exception.HarborClientException;
import org.harbor.client.client.op.GeneralSystemInfos;
import org.harbor.client.client.op.Healths;
import org.harbor.client.client.op.ProjectHandler;
import org.harbor.client.client.op.Projects;
import org.harbor.client.client.op.Searches;
import org.harbor.client.client.op.Users;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author lr
 * @date 2021/2/5
 */
public class DefaultHarborClientV1 implements HarborClientV1 {

    private final String url;

    private final String accessToken;

    private final CloseableHttpClient httpClient;

    private final ResponseHandler<HarborResponse> handler;

    public DefaultHarborClientV1(String url, String accessToken, CloseableHttpClient httpClient, int configure) {
        this.url = url;
        this.accessToken = accessToken;
        this.httpClient = httpClient;
        handler = new DefaultResponseHandler(configure);
    }

    @Override
    public Projects projects() {
        return new ProjectsImpl(this, API_BASE);
    }

    @Override
    public ProjectHandler project(String projectName) {
        Objects.requireNonNull(projectName, "project name can not be null");
        return projects().withExactName(projectName);
    }

    @Override
    public Searches search() {
        return new SearchesImpl(this, API_BASE);
    }

    @Override
    public GeneralSystemInfos systemInfo() {
        return new GeneralSystemInfoImpl(this, API_BASE);
    }

    @Override
    public Users user() {
        return new UsersImpl(this, API_BASE);
    }


    @Override
    public Healths health() {
        return new HealthsImpl(this, API_BASE);
    }

    public <T> List<T> list(String path, Class<T> object) throws HarborClientException {
        HttpGet httpGet = new HttpGet(url + path);
        addAccessTokenHeader(httpGet);
        try {
            HarborResponse response = httpClient.execute(httpGet, handler);
            if (!response.success()) {
                return Collections.emptyList();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, object);
            List<T> list = objectMapper.readValue(response.getBody(), collectionType);
            return list == null ? Collections.emptyList() : list;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public <T> T get(String path, Class<T> object) throws HarborClientException {
        HttpGet httpGet = new HttpGet(url + path);
        addAccessTokenHeader(httpGet);
        try {
            HarborResponse response = httpClient.execute(httpGet, handler);
            if (response.success()) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.getBody(), object);
            }
            return null;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public HarborResponse put(String path, Object o) throws HarborClientException {
        HttpPut httpPut = new HttpPut(url + path);
        addAccessTokenHeader(httpPut);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            httpPut.setEntity(new StringEntity(objectMapper.writeValueAsString(o), ContentType.APPLICATION_JSON));
            HarborResponse execute = httpClient.execute(httpPut, handler);
            return execute;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public HarborResponse post(String path, Object o) throws HarborClientException {
        HttpPost httpPost = new HttpPost(url + path);
        addAccessTokenHeader(httpPost);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (o != null) {
                httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(o), ContentType.APPLICATION_JSON));
            }
            HarborResponse execute = httpClient.execute(httpPost, handler);
            return execute;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public HarborResponse head(String path) throws HarborClientException {
        HttpHead httpHead = new HttpHead(url + path);
        addAccessTokenHeader(httpHead);
        try {
            HarborResponse execute = httpClient.execute(httpHead, handler);
            return execute;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public HarborResponse delete(String path) throws HarborClientException {
        HttpDelete httpDelete = new HttpDelete(url + path);
        addAccessTokenHeader(httpDelete);
        try {
            HarborResponse execute = httpClient.execute(httpDelete, handler);
            return execute;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    private HttpRequestBase addAccessTokenHeader(HttpRequestBase httpRequestBase) {
        if (StrUtil.isNotEmpty(accessToken)) {
            httpRequestBase.addHeader("Authorization", "Basic " + accessToken);
        }
        return httpRequestBase;
    }

//    static  ObjectMapper configObjectMapper(ObjectMapper objectMapper) {
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(Type.class, new JsonDeserializer<Type>() {
//            @Override
//            public Type deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//                return null;
//            }
//        })
//    }

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
}
