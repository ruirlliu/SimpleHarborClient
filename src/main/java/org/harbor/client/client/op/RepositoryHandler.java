package org.harbor.client.client.op;

import org.harbor.client.client.model.Repository;
import org.harbor.client.client.op.base.DeleteOp;
import org.harbor.client.client.op.base.GetOp;
import org.harbor.client.client.op.base.UpdateOp;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface RepositoryHandler extends GetOp<Repository>, DeleteOp, UpdateOp<Repository> {

    Artifacts artifacts();

    ArtifactHandler artifact(String reference);
}
