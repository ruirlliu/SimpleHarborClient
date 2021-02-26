package org.harbor.client.op;

import org.harbor.client.HarborResponse;
import org.harbor.client.exception.HarborClientException;
import org.harbor.client.model.ListFilter;
import org.harbor.client.model.Project;
import org.harbor.client.model.ProjectReq;

import java.util.List;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface Projects {

    List<Project> list(ListFilter filter) throws HarborClientException;

    ProjectHandler withExactName(String name) throws HarborClientException;

    HarborResponse create(ProjectReq req);

}
