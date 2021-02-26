package org.harbor.client.client.op;

import org.harbor.client.client.HarborResponse;
import org.harbor.client.client.exception.HarborClientException;
import org.harbor.client.client.model.ListFilter;
import org.harbor.client.client.model.Project;
import org.harbor.client.client.model.ProjectReq;

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
