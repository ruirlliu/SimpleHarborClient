package org.harbor.client.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lr
 * @date 2021/2/24
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Artifact {

    @JsonProperty("push_time")
    private Date pushTime;

    @JsonProperty("scan_overview")
    private Map<String, NativeReportSummary> scanOverview;

    @JsonProperty("tags")
    private List<Tag> tags;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("media_type")
    private String mediaType;

    @JsonProperty("type")
    private String type;

}
