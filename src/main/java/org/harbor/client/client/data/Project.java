package org.harbor.client.client.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author liurui
 * @date 2021/2/4
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project {

    @JsonProperty("project_id")
    private Integer projectId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("chart_count")
    private Integer chartCount;

    @JsonProperty("repo_count")
    private Integer repoCount;

    @JsonProperty("deleted")
    private Boolean deleted;

    @JsonProperty("metadata")
    private ProjectMetadata metadata;


}
