package org.harbor.client.model;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author lr
 * @date 2021/2/23
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Repository {

    private Integer id;

    @JsonProperty("update_time")
    private Date updateTime;

    @JsonProperty("creation_time")
    private Date createTime;

    @JsonProperty("name")
    private String fullName;

    @JsonProperty("project_id")
    private Integer projectId;

    @JsonProperty("artifact_count")
    private Integer artifactCount;

    private String description;

    @JsonProperty("pull_count")
    private Integer pullCount;

    @JsonIgnore
    public String getName() {
        return StrUtil.isBlank(fullName) ? null : fullName.substring(fullName.indexOf("/") + 1) ;
    }

}
