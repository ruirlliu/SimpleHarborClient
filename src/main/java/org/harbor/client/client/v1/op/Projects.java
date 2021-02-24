package org.harbor.client.client.v1.op;

import cn.hutool.core.util.StrUtil;
import org.harbor.client.client.model.ListFilter;
import org.harbor.client.client.model.Project;
import org.harbor.client.client.model.ProjectReq;
import org.harbor.client.client.v1.DefaultHarborClientV1;
import org.harbor.client.client.v1.HarborResponse;
import org.harbor.client.client.v1.exception.HarborClientException;

import java.util.List;
import java.util.Objects;

/**
 * @author lr
 * @date 2021/2/5
 */
public class Projects {

    private final String baseApi;

    private final DefaultHarborClientV1 client;

    public Projects(DefaultHarborClientV1 client, String baseApi) {
        this.baseApi = baseApi;
        this.client = client;
    }


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

    public ProjectHandler withExactName(String name) throws HarborClientException {
        Objects.requireNonNull(name, "name can not be null");
        return new ProjectHandler(getProjectBaseApi(), name, this);
    }

    public HarborResponse create(ProjectReq req) {
        Objects.requireNonNull(req, "req can not be null");
        return client.post(getProjectBaseApi(), req);
    }


    protected DefaultHarborClientV1 getClient() {
        return client;
    }

    private String getProjectBaseApi() {
        return baseApi + "/projects";
    }
}
