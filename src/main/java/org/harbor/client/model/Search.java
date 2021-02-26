package org.harbor.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author lr
 * @date 2021/2/24
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Search {

    private List<Project> project;

    private List<SearchRepository> repository;

    private List<SearchResult> chart;

}
