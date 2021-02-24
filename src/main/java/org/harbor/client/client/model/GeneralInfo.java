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
public class GeneralInfo {

    @JsonProperty("harbor_version")
    private String harborVersion;

    @JsonProperty("auth_mode")
    private String authMode;

    @JsonProperty("registry_url")
    private String registryUrl;

    @JsonProperty("external_url")
    private String externalUrl;

}
