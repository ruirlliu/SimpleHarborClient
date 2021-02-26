package org.harbor.client.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author lr
 * @date 2021/2/23
 */
@Data
@SuperBuilder
public class ListFilter {

    public static final ListFilter DEFAULT = ListFilter.builder().page(1).pageSize(15).build();

    private int page;

    private int pageSize;

    private String query;

}
