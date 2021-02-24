package org.harbor.client.client.v1.op;

import cn.hutool.core.collection.CollUtil;
import org.harbor.client.client.model.ListFilter;
import org.harbor.client.client.model.Project;
import org.harbor.client.client.model.ProjectReq;
import org.harbor.client.client.v1.HarborResponse;
import org.harbor.client.client.v1.exception.HarborClientException;

import java.util.List;

/**
 * @author liurui
 * @date 2021/2/23
 */
public class ProjectHandler {

    private final String projectBaseApi;
    private final String projectName;
    private final Projects projects;

    public ProjectHandler(String projectBaseApi, String projectName, Projects projects) {
        this.projectBaseApi = projectBaseApi;
        this.projectName = projectName;
        this.projects = projects;
    }

    public Project get() {
        List<Project> list = projects.list(ListFilter.builder().query(projectName).build());
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        for (Project project : list) {
            if (projectName.equals(project.getName())) {
                return project;
            }
        }
        return null;
    }

    public HarborResponse delete() throws HarborClientException {
        Project project = this.get();
        if (project == null) {
            throw new HarborClientException(-1, "project " + projectName + " not exist");
        }
        return projects.getClient().delete(projectBaseApi + "/" + project.getProjectId());
    }

    public HarborResponse update(ProjectReq req) throws HarborClientException {
        Project project = this.get();
        if (project == null) {
            throw new HarborClientException(-1, "project " + projectName + " not exist");
        }
        return projects.getClient().put(projectBaseApi + "/" + project.getProjectId(), req);
    }

    public boolean exist() {
        return projects.getClient().head(projectBaseApi + "?project_name=" + projectName).success();
    }

    public String getProjectName() {
        return projectName;
    }

    public Repositories repositories() {
        return new Repositories(projects.getClient(), projectBaseApi, projectName);
    }

}
