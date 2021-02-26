package org.harbor.client.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author lr
 * @date 2021/2/26
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Storage {

    private Long total;

    private Long free;

}
