package org.harbor.client.client.v1.op;

import org.harbor.client.client.model.GeneralInfo;
import org.harbor.client.client.model.SystemInfo;
import org.harbor.client.client.v1.DefaultHarborClientV1;

/**
 * @author lr
 * @date 2021/2/24
 */
public class GeneralSystemInfo {

    private final DefaultHarborClientV1 client;

    private final String baseApi;

    public GeneralSystemInfo(DefaultHarborClientV1 client, String baseApi) {
        this.client = client;
        this.baseApi = baseApi;
    }

    // api可以不验证权限
    public GeneralInfo get() {
        return client.get(getSystemInfoApi(), GeneralInfo.class);
    }

    public SystemInfo volumes() {
        return client.get(getSystemInfoApi() + "/volumes", SystemInfo.class);
    }

    public String getSystemInfoApi() {
        return baseApi + "/systeminfo";
    }
}
