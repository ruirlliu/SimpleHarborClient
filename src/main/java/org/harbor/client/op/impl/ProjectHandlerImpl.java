package org.harbor.client.op.impl;

import cn.hutool.core.collection.CollUtil;
import org.harbor.client.HarborResponse;
import org.harbor.client.model.ListFilter;
import org.harbor.client.model.Project;
import org.harbor.client.model.ProjectReq;
import org.harbor.client.model.ScannerRegistration;
import org.harbor.client.exception.HarborClientException;
import org.harbor.client.op.ProjectHandler;
import org.harbor.client.op.Projects;
import org.harbor.client.op.Repositories;
import org.harbor.client.op.RepositoryHandler;

import java.util.List;
import java.util.Objects;

/**
 * @author lr
 * @date 2021/2/23
 */
class ProjectHandlerImpl implements ProjectHandler {

    private final String projectBaseApi;
    private final String projectName;
    private final Projects projects;
    private final DefaultHarborClientV1 client;

    ProjectHandlerImpl(String projectBaseApi, String projectName, Projects projects, DefaultHarborClientV1 client) {
        this.projectBaseApi = projectBaseApi;
        this.projectName = projectName;
        this.projects = projects;
        this.client = client;
    }

    @Override
    public Project get() {
        List<Project> list = projects.list(ListFilter.builder().query(projectName).page(1).pageSize(1000).build());
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

    @Override
    public HarborResponse delete()  {
        Project project = this.get();
        if (project == null) {
            throw new HarborClientException(-1, "project " + projectName + " not exist");
        }
        return client.delete(projectBaseApi + "/" + project.getProjectId());
    }

    @Override
    public HarborResponse update(ProjectReq req)  {
        Project project = this.get();
        if (project == null) {
            throw new HarborClientException(-1, "project " + projectName + " not exist");
        }
        return client.put(projectBaseApi + "/" + project.getProjectId(), req);
    }

    @Override
    public boolean exist() {
        return client.head(projectBaseApi + "?project_name=" + projectName).success();
    }

    @Override
    public ScannerRegistration scanner() {
        Project project = this.get();
        if (project == null) {
            throw new HarborClientException(-1, "project " + projectName + " not exist");
        }
        return client.get(projectBaseApi + "/" + project.getProjectId() + "/scanner", ScannerRegistration.class);
    }

    @Override
    public Repositories repositories() {
        return new RepositoriesImpl(client, projectBaseApi, projectName);
    }

    @Override
    public RepositoryHandler repository(String repositoryName) {
        Objects.requireNonNull(repositoryName, "repoKye can not be null");
        return repositories().repository(repositoryName);
    }

}
