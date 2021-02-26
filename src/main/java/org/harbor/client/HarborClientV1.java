package org.harbor.client;


import org.harbor.client.op.GeneralSystemInfos;
import org.harbor.client.op.Healths;
import org.harbor.client.op.ProjectHandler;
import org.harbor.client.op.Projects;
import org.harbor.client.op.Searches;
import org.harbor.client.op.Users;

/**
 * @author lr
 * @date 2021/2/5
 */
public interface HarborClientV1 {

    String API_BASE = "/api/v2.0";

    Projects projects();

    ProjectHandler project(String projectName);

    Searches search();

    GeneralSystemInfos systemInfo();

    Users user();

    Healths health();

}
