package org.harbor.client.op.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import org.harbor.client.model.Artifact;
import org.harbor.client.model.ArtifactListFilter;
import org.harbor.client.op.handler.ArtifactHandler;
import org.harbor.client.op.Artifacts;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author lr
 * @date 2021/2/24
 */
class ArtifactsImpl implements Artifacts {

    private final String repositoryBaseApi;
    private final String repositoryName;
    private final DefaultHarborClientV2 client;

    ArtifactsImpl(DefaultHarborClientV2 client, String repositoryBaseApi, String repositoryName) {
        this.client = client;
        this.repositoryBaseApi = repositoryBaseApi;
        this.repositoryName = repositoryName;
    }

    @Override
    public List<Artifact> list(ArtifactListFilter filter) {
        String artifactBaseApi = getArtifactBaseApi();
        if (filter == null) {
            filter = ArtifactListFilter.DEFAULT;
        }
        int page = filter.getPage();
        int pageSize = filter.getPageSize();
        String query = filter.getQuery();
        boolean withTag = filter.isWithTag();
        boolean withLabel = filter.isWithLabel();
        boolean withScanOverview = filter.isWithScanOverview();
        artifactBaseApi += "?page=" + page + "&page_size=" + pageSize;
        if (StrUtil.isNotEmpty(query)) {
            artifactBaseApi += "&q=" + query;
        }
        if (withTag) {
            artifactBaseApi += "&with_tag=true";
        }
        if (withLabel) {
            artifactBaseApi += "&with_label=true";
        }
        if (withScanOverview) {
            artifactBaseApi += "&with_scan_overview=true";
        }
        List<Artifact> list = client.list(artifactBaseApi, Artifact.class);
        return list;
    }

    @Override
    public ArtifactHandler artifact(String reference) {
        return new ArtifactHandlerImpl(getArtifactBaseApi(), reference, client);
    }

    private String getArtifactBaseApi() {
        return repositoryBaseApi + "/" + URLUtil.encodeQuery(repositoryName, StandardCharsets.UTF_8) + "/artifacts";
    }

}
