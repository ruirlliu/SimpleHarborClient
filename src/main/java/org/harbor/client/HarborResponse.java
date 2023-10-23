package org.harbor.client;

import lombok.Data;

/**
 * @author lr
 * @date 2021/2/22
 */
@Data
public class HarborResponse {

    private int status;

    private String body;

    public HarborResponse(int status) {
        this.status = status;
    }

    public HarborResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    public boolean success() {
        return !(status < 200 || status >= 300);
    }

}
