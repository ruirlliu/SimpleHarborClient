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
public class ScannerRegistration {

    private String uuid;

    private String description;

    private Boolean disabled;

    private String vendor;

    @JsonProperty("is_default")
    private Boolean isDefault;

    private String health;

    private String name;

}
