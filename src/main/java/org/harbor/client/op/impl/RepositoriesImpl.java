package org.harbor.client.op.impl;

import cn.hutool.core.util.StrUtil;
import org.harbor.client.model.ListFilter;
import org.harbor.client.model.Repository;
import org.harbor.client.op.RepositoryHandler;
import org.harbor.client.op.Repositories;

import java.util.List;

/**
 * @author lr
 * @date 2021/2/23
 */
class RepositoriesImpl implements Repositories {

    private final String projectBaseApi;
    private final String projectName;
    private final DefaultHarborClientV1 client;

    RepositoriesImpl(DefaultHarborClientV1 client, String projectBaseApi, String projectName) {
        this.client = client;
        this.projectBaseApi = projectBaseApi;
        this.projectName = projectName;
    }

    // todo: 耗时
    @Override
    public List<Repository> list(ListFilter filter) {
        String repositoryApi = getRepositoryBaseApi();
        if (filter == null) {
            filter = ListFilter.DEFAULT;
        }
        String query = filter.getQuery();
        int page = filter.getPage();
        int pageSize = filter.getPageSize();
        repositoryApi += "?page=" + page + "&page_size=" + pageSize;
        if (StrUtil.isNotEmpty(query)) {
            repositoryApi += "&q=" + query;
        }
        return client.list(repositoryApi, Repository.class);
    }

    @Override
    public RepositoryHandler repository(String repositoryName) {
        return new RepositoryHandlerImpl(getRepositoryBaseApi(), repositoryName, client);
    }


    private String getRepositoryBaseApi() {
        return projectBaseApi + "/" + projectName + "/repositories";
    }




}
