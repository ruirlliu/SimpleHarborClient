package org.harbor.client.op.impl;

import org.harbor.client.HarborResponse;
import org.harbor.client.model.Artifact;
import org.harbor.client.op.Tags;
import org.harbor.client.op.ArtifactHandler;

/**
 * @author lr
 * @date 2021/2/24
 */
class ArtifactHandlerImpl implements ArtifactHandler {

    private final String artifactBaseApi;
    private final String reference;
    private final DefaultHarborClientV1 client;

    ArtifactHandlerImpl(String artifactBaseApi, String reference, DefaultHarborClientV1 client) {
        this.artifactBaseApi = artifactBaseApi;
        this.reference = reference;
        this.client = client;
    }

    @Override
    public HarborResponse delete() {
        HarborResponse response = client.delete(getTargetArtifactApi());
        return response;
    }

    @Override
    public Artifact get() {
        String targetArtifactApi = getTargetArtifactApi() + "?with_scan_overview=true";
        Artifact artifact = client.get(targetArtifactApi, Artifact.class);
        return artifact;
    }

    @Override
    public HarborResponse scan() {
        return client.post(getTargetArtifactApi() + "/scan", null);
    }
    @Override
    public Tags tags() {
        return new TagsImpl(artifactBaseApi, reference, client);
    }


    private String getTargetArtifactApi() {
        return artifactBaseApi + "/" + reference;
    }

}
