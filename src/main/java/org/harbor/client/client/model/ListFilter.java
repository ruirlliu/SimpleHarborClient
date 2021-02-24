package org.harbor.client.client.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author liurui
 * @date 2021/2/23
 */
@Data
@SuperBuilder
public class ListFilter {

    private int page;

    private int pageSize;

    private String query;

}
