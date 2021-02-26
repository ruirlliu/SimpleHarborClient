package org.harbor.client.op;

import org.harbor.client.model.Artifact;
import org.harbor.client.model.ArtifactListFilter;

import java.util.List;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface Artifacts {
    List<Artifact> list(ArtifactListFilter filter);

    ArtifactHandler artifact(String reference);
}
