package org.harbor.client.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author lr
 * @date 2021/2/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ArtifactListFilter extends ListFilter{

    public static final ArtifactListFilter DEFAULT = ArtifactListFilter.builder()
            .page(1).pageSize(15).withLabel(true).withTag(true).withScanOverview(true).build();

    private boolean withTag;

    private boolean withLabel;

    private boolean withScanOverview;

}
