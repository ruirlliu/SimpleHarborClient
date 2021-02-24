package org.harbor.client.client.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author liurui
 * @date 2021/2/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ArtifactListFilter extends ListFilter{

    private boolean withTag;

    private boolean withLabel;

    private boolean withScanOverview;

}
