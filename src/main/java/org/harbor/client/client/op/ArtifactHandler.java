package org.harbor.client.client.op;

import org.harbor.client.client.model.Artifact;
import org.harbor.client.client.HarborResponse;
import org.harbor.client.client.op.base.DeleteOp;
import org.harbor.client.client.op.base.GetOp;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface ArtifactHandler extends DeleteOp, GetOp<Artifact> {

    HarborResponse scan();

    Tags tags();
}
