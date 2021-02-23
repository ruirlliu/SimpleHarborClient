package org.harbor.client.client.v1.op;

import cn.hutool.core.collection.CollUtil;
import com.harbor.client.data.Project;
import com.harbor.client.v1.HarborResponse;
import com.harbor.client.v1.exception.HarborClientException;

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
        List<Project> list = projects.withName(projectName).list();
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

    public HarborResponse<String> delete() throws HarborClientException {
        Project project = this.get();
        if (project == null) {
            throw new HarborClientException(-1, "project " + projectName + " not exist");
        }
        return projects.getClient().delete(projectBaseApi, project.getProjectId());
    }

    public HarborResponse<String> update() {
        return null;
    }

    public boolean exist() {
        return projects.getClient().head(projectBaseApi + "?project_name=" + projectName).success();
    }

}
