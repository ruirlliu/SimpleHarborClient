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
public class NativeReportSummary {

    private String severity;

    @JsonProperty("complete_percent")
    private Integer completePercent;

    @JsonProperty("scan_status")
    private String scanStatus;

    private VulnerabilitySummary summary;

}
