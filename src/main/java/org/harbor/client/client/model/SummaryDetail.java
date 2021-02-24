package org.harbor.client.client.model;

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
public class SummaryDetail {

    @JsonProperty("Critical")
    private int critical;

    @JsonProperty("High")
    private int high;

    @JsonProperty("Medium")
    private int medium;

    @JsonProperty("Low")
    private int low;

    @JsonProperty("Unknown")
    private int unknown;

    @JsonProperty("Negligible")
    private int negligible;

}
