package org.harbor.client.client.v1.op;

import org.harbor.client.client.model.Repository;
import org.harbor.client.client.v1.HarborResponse;

/**
 * @author liurui
 * @date 2021/2/23
 */
public class RepositoryHandler {

    private final String repositoryBaseApi;
    private final String repositoryName;
    private final Repositories repositories;

    public RepositoryHandler(String repositoryBaseApi, String repositoryName, Repositories repositories) {
        this.repositoryBaseApi = repositoryBaseApi;
        this.repositoryName = repositoryName;
        this.repositories = repositories;
    }

    public Repository get() {
        Repository repository = repositories.getClient().get(getRepositoryApi(), Repository.class);
        return repository;
    }

    public HarborResponse delete() {
        HarborResponse response = repositories.getClient().delete(getRepositoryApi());
        return response;
    }

    public HarborResponse update(Repository repository) {
        HarborResponse response = repositories.getClient().put(getRepositoryApi(), repository);
        return response;
    }

    public Artifacts artifacts() {
        return new Artifacts(repositories.getClient(), getRepositoryApi(), repositoryName);
    }

    public String getRepositoryApi() {
        return repositoryBaseApi + "/" + repositoryName;
    }

}
