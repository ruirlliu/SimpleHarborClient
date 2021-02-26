package org.harbor.client.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author lr
 * @date 2021/2/5
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectMetadata {

    @JsonProperty("public")
    private Boolean isPublic;

    public ProjectMetadata() {
    }

    public ProjectMetadata(Boolean isPublic) {
        this.isPublic = isPublic;
    }


    @JsonGetter("public")
    public String getPublic() {
        return Boolean.toString(isPublic);
    }
}
