package org.harbor.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author lr
 * @date 2021/2/24
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchRepository {

    @JsonProperty("repository_name")
    private String repositoryName;

    @JsonProperty("project_name")
    private String projectName;

    @JsonProperty("artifact_count")
    private Integer artifactCount;

    @JsonProperty("project_public")
    private Boolean projectPublic;

}
