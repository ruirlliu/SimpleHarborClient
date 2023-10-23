package org.harbor.client.op.impl;

import cn.hutool.core.util.URLUtil;
import org.harbor.client.HarborResponse;
import org.harbor.client.model.Repository;
import org.harbor.client.op.handler.ArtifactHandler;
import org.harbor.client.op.Artifacts;
import org.harbor.client.op.handler.RepositoryHandler;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author lr
 * @date 2021/2/23
 */
class RepositoryHandlerImpl implements RepositoryHandler {

    private final String repositoryBaseApi;
    private final String repositoryName;
    private final DefaultHarborClientV2 client;

    RepositoryHandlerImpl(String repositoryBaseApi, String repositoryName, DefaultHarborClientV2 client) {
        this.repositoryBaseApi = repositoryBaseApi;
        this.repositoryName = repositoryName;
        this.client = client;
    }

    @Override
    public Repository get() {
        Repository repository = client.get(getRepositoryApi(), Repository.class);
        return repository;
    }

    @Override
    public HarborResponse delete() {
        HarborResponse response = client.delete(getRepositoryApi());
        return response;
    }

    @Override
    public HarborResponse update(Repository repository) {
        HarborResponse response = client.put(getRepositoryApi(), repository);
        return response;
    }

    @Override
    public Artifacts artifacts() {
        return new ArtifactsImpl(client, repositoryBaseApi, repositoryName);
    }

    @Override
    public ArtifactHandler artifact(String reference) {
        Objects.requireNonNull(repositoryName, "reference can not be null");
        return artifacts().artifact(reference);
    }

    private String getRepositoryApi() {
        return repositoryBaseApi + "/" + URLUtil.encodeQuery(repositoryName, StandardCharsets.UTF_8);
    }

}
