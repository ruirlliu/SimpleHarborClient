package org.harbor.client.client;


import org.harbor.client.client.op.GeneralSystemInfos;
import org.harbor.client.client.op.Healths;
import org.harbor.client.client.op.ProjectHandler;
import org.harbor.client.client.op.Projects;
import org.harbor.client.client.op.impl.SearchesImpl;
import org.harbor.client.client.op.Users;

/**
 * @author lr
 * @date 2021/2/5
 */
public interface HarborClientV1 {

    String API_BASE = "/api/v2.0";

    Projects projects();

    ProjectHandler project(String projectName);

    SearchesImpl search();

    GeneralSystemInfos systemInfo();

    Users user();

    Healths health();

}
