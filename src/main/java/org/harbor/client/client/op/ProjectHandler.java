package org.harbor.client.client.op;

import org.harbor.client.client.model.Project;
import org.harbor.client.client.model.ProjectReq;
import org.harbor.client.client.model.ScannerRegistration;
import org.harbor.client.client.op.base.DeleteOp;
import org.harbor.client.client.op.base.GetOp;
import org.harbor.client.client.op.base.UpdateOp;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface ProjectHandler extends GetOp<Project> , DeleteOp, UpdateOp<ProjectReq> {

    boolean exist();

    ScannerRegistration scanner();

    Repositories repositories();

    RepositoryHandler repository(String repositoryName);

}
