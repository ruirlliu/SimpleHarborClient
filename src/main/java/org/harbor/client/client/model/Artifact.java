package org.harbor.client.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author liurui
 * @date 2021/2/24
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Artifact {

    @JsonProperty("push_time")
    private Date pushTime;

    @JsonProperty("scan_overview")
    private ScanOverview scanOverview;

    private List<Tag> tags;

    private Integer size;

    @JsonProperty("media_type")
    private String mediaType;

    private String type;

}
