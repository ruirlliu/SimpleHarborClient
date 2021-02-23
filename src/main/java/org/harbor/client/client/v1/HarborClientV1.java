package org.harbor.client.client.v1;

import com.harbor.client.v1.op.Projects;

/**
 * @author liurui
 * @date 2021/2/5
 */
public interface HarborClientV1 {

    String API_BASE = "/api/v2.0";

    Projects projects();


}
