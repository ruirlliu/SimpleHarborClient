package org.harbor.client.op;

import org.harbor.client.model.Artifact;
import org.harbor.client.HarborResponse;
import org.harbor.client.op.base.DeleteOp;
import org.harbor.client.op.base.GetOp;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface ArtifactHandler extends DeleteOp, GetOp<Artifact> {

    HarborResponse scan();

    Tags tags();
}
