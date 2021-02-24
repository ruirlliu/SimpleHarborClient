package org.harbor.client.client.v1.op;

import org.harbor.client.client.model.Artifact;
import org.harbor.client.client.v1.HarborResponse;

/**
 * @author lr
 * @date 2021/2/24
 */
public class ArtifactHandler {

    private final String artifactBaseApi;
    private final String reference;
    private final Artifacts artifacts;

    public ArtifactHandler(String artifactBaseApi, String reference, Artifacts repositories) {
        this.artifactBaseApi = artifactBaseApi;
        this.reference = reference;
        this.artifacts = repositories;
    }

    public HarborResponse delete() {
        HarborResponse response = artifacts.getClient().delete(getTargetArtifactApi());
        return response;
    }

    public Artifact get() {
        String targetArtifactApi = getTargetArtifactApi() + "?with_scan_overview=true";
        Artifact artifact = artifacts.getClient().get(targetArtifactApi, Artifact.class);
        return artifact;
    }

    public HarborResponse scan() {
        return artifacts.getClient().post(getTargetArtifactApi() + "/scan", null);
    }
    public Tags tags() {
        return new Tags(artifactBaseApi, reference, artifacts.getClient());
    }


    public String getTargetArtifactApi() {
        return artifactBaseApi + "/" + reference;
    }

}
