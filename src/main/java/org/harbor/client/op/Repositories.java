package org.harbor.client.op;

import org.harbor.client.model.ListFilter;
import org.harbor.client.model.Repository;

import java.util.List;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface Repositories {

    List<Repository> list(ListFilter filter);

    RepositoryHandler repository(String repositoryName);
}
