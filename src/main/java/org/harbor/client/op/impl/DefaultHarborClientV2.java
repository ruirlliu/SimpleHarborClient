package org.harbor.client.op.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.http.HttpStatus;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.harbor.client.DefaultResponseHandler;
import org.harbor.client.HarborClientV2;
import org.harbor.client.HarborResponse;
import org.harbor.client.exception.HarborClientException;
import org.harbor.client.op.*;
import org.harbor.client.op.handler.ProjectHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author lr
 * @date 2021/2/5
 */
public class DefaultHarborClientV2 implements HarborClientV2 {

    private final String url;

    private final CloseableHttpClient httpClient;

    private final ResponseHandler<HarborResponse> handler;

    public DefaultHarborClientV2(String url, CloseableHttpClient httpClient) {
        this.url = url;
        this.httpClient = httpClient;
        this.handler = new DefaultResponseHandler();
    }

    @Override
    public Projects projects() {
        return new ProjectsImpl(this);
    }

    @Override
    public ProjectHandler project(String projectName) {
        Objects.requireNonNull(projectName, "project name can not be null");
        return projects().withExactName(projectName);
    }

    @Override
    public Searches search() {
        return new SearchesImpl(this);
    }

    @Override
    public GeneralSystemInfos systemInfo() {
        return new GeneralSystemInfoImpl(this, API_BASE);
    }

    @Override
    public Users user() {
        return new UsersImpl(this);
    }


    @Override
    public Healths health() {
        return new HealthsImpl(this);
    }

    public <T> List<T> list(String path, Class<T> object) throws HarborClientException {
        HttpGet httpGet = new HttpGet(requestPath(path));
        try {
            HarborResponse response = httpClient.execute(httpGet, handler);
            if (!response.success()) {
                return Collections.emptyList();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, object);
            List<T> list = objectMapper.readValue(response.getBody(), collectionType);
            return list == null ? Collections.<T>emptyList() : list;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public <T> T get(String path, Class<T> object) throws HarborClientException {
        HttpGet httpGet = new HttpGet(requestPath(path));
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
        HttpPut httpPut = new HttpPut(requestPath(path));
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
        HttpPost httpPost = new HttpPost(requestPath(path));
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
        HttpHead httpHead = new HttpHead(requestPath(path));
        try {
            HarborResponse execute = httpClient.execute(httpHead, handler);
            return execute;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public HarborResponse delete(String path) throws HarborClientException {
        HttpDelete httpDelete = new HttpDelete(requestPath(path));
        try {
            HarborResponse execute = httpClient.execute(httpDelete, handler);
            return execute;
        } catch (IOException e) {
            throw new HarborClientException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }


    private String requestPath(String path) {
        return this.url + API_BASE + path;
    }
}
