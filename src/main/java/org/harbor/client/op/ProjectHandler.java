package org.harbor.client.op;

import org.harbor.client.model.Project;
import org.harbor.client.model.ProjectReq;
import org.harbor.client.model.ScannerRegistration;
import org.harbor.client.op.base.DeleteOp;
import org.harbor.client.op.base.GetOp;
import org.harbor.client.op.base.UpdateOp;

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
