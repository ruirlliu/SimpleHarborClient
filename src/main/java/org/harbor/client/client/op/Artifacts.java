package org.harbor.client.client.op;

import org.harbor.client.client.model.Artifact;
import org.harbor.client.client.model.ArtifactListFilter;

import java.util.List;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface Artifacts {
    List<Artifact> list(ArtifactListFilter filter);

    ArtifactHandler artifact(String reference);
}
