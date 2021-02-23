package org.harbor.client.client.v1.op;

import cn.hutool.core.util.StrUtil;
import com.harbor.client.data.Project;
import com.harbor.client.v1.DefaultHarborClientV1;
import com.harbor.client.v1.exception.HarborClientException;

import java.util.List;
import java.util.Objects;

/**
 * @author liurui
 * @date 2021/2/5
 */
public class Projects {

    private final String baseApi;

    private final DefaultHarborClientV1 client;

    private String filterName;

    public Projects(DefaultHarborClientV1 client, String baseApi) {
        this.baseApi = baseApi;
        this.client = client;
    }

    public Projects withName(String name) {
        this.filterName = name;
        return this;
    }

    public List<Project> list() throws HarborClientException {
        String projectApi = getProjectApi();
        if (StrUtil.isNotEmpty(filterName)) {
            projectApi += "?name=" + filterName;
        }
        return client.list(projectApi, Project.class);
    }

    public ProjectHandler withExactName(String name) throws HarborClientException {
        Objects.requireNonNull(name, "name can not be null");
        return new ProjectHandler(getProjectApi(), name, this);
    }

    protected DefaultHarborClientV1 getClient() {
        return client;
    }

    private String getProjectApi() {
        return baseApi + "/projects";
    }
}
