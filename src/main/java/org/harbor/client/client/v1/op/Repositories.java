package org.harbor.client.client.v1.op;

import cn.hutool.core.util.StrUtil;
import org.harbor.client.client.model.ListFilter;
import org.harbor.client.client.model.Repository;
import org.harbor.client.client.v1.DefaultHarborClientV1;

import java.util.List;

/**
 * @author lr
 * @date 2021/2/23
 */
public class Repositories {

    private final String projectBaseApi;
    private final String projectName;
    private final DefaultHarborClientV1 client;

    public Repositories(DefaultHarborClientV1 client, String projectBaseApi, String projectName) {
        this.client = client;
        this.projectBaseApi = projectBaseApi;
        this.projectName = projectName;
    }

    // todo: 耗时
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

    public RepositoryHandler repository(String repositoryName) {
        return new RepositoryHandler(getRepositoryBaseApi(), repositoryName, this);
    }


    public String getRepositoryBaseApi() {
        return projectBaseApi + "/" + projectName + "/repositories";
    }

    protected DefaultHarborClientV1 getClient() {
        return client;
    }


}
