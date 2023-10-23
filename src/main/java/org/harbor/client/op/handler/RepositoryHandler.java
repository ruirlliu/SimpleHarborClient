package org.harbor.client.op.handler;

import org.harbor.client.model.Repository;
import org.harbor.client.op.Artifacts;
import org.harbor.client.op.base.DeleteOp;
import org.harbor.client.op.base.GetOp;
import org.harbor.client.op.base.UpdateOp;
import org.harbor.client.op.handler.ArtifactHandler;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface RepositoryHandler extends GetOp<Repository>, DeleteOp, UpdateOp<Repository> {

    Artifacts artifacts();

    ArtifactHandler artifact(String reference);
}
