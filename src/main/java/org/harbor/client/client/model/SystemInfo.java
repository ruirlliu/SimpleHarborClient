package org.harbor.client.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author lr
 * @date 2021/2/24
 */
@Data
public class SystemInfo {

    private Storage storage;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Storage {

        private Long total;

        private Long free;

    }
}
