package org.harbor.client.op.impl;

import cn.hutool.core.util.StrUtil;
import org.harbor.client.HarborResponse;
import org.harbor.client.exception.HarborClientException;
import org.harbor.client.model.ListFilter;
import org.harbor.client.model.Project;
import org.harbor.client.model.ProjectReq;
import org.harbor.client.op.ProjectHandler;
import org.harbor.client.op.Projects;

import java.util.List;
import java.util.Objects;

/**
 * @author lr
 * @date 2021/2/5
 */
class ProjectsImpl implements Projects {

    private final String baseApi;

    private final DefaultHarborClientV1 client;

    ProjectsImpl(DefaultHarborClientV1 client, String baseApi) {
        this.baseApi = baseApi;
        this.client = client;
    }


    @Override
    public List<Project> list(ListFilter filter) throws HarborClientException {
        String projectApi = getProjectBaseApi();
        if (filter == null) {
            filter = ListFilter.DEFAULT;
        }
        String query = filter.getQuery();
        int page = filter.getPage();
        int pageSize = filter.getPageSize();
        projectApi += "?page=" + page + "&page_size=" + pageSize;
        if (StrUtil.isNotEmpty(query)) {
            projectApi += "&name=" + query;
        }
        return client.list(projectApi, Project.class);
    }

    @Override
    public ProjectHandler withExactName(String name) throws HarborClientException {
        Objects.requireNonNull(name, "name can not be null");
        return new ProjectHandlerImpl(getProjectBaseApi(), name, this, client);
    }

    @Override
    public HarborResponse create(ProjectReq req) {
        Objects.requireNonNull(req, "req can not be null");
        return client.post(getProjectBaseApi(), req);
    }

    private String getProjectBaseApi() {
        return baseApi + "/projects";
    }
}
