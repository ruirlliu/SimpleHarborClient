package org.harbor.client.client.op.impl;

import org.harbor.client.client.model.GeneralInfo;
import org.harbor.client.client.model.SystemInfo;

/**
 * @author lr
 * @date 2021/2/24
 */
class GeneralSystemInfoImpl implements org.harbor.client.client.op.GeneralSystemInfos {

    private final DefaultHarborClientV1 client;

    private final String baseApi;

    GeneralSystemInfoImpl(DefaultHarborClientV1 client, String baseApi) {
        this.client = client;
        this.baseApi = baseApi;
    }

    // api可以不验证权限
    @Override
    public GeneralInfo get() {
        return client.get(getSystemInfoApi(), GeneralInfo.class);
    }

    @Override
    public SystemInfo volumes() {
        return client.get(getSystemInfoApi() + "/volumes", SystemInfo.class);
    }

    private String getSystemInfoApi() {
        return baseApi + "/systeminfo";
    }
}
