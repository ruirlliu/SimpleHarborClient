package org.harbor.client.op.impl;

import org.harbor.client.model.OverallHealthStatus;
import org.harbor.client.op.Healths;

/**
 * @author lr
 * @date 2021/2/25
 */
class HealthsImpl implements Healths {

    private final DefaultHarborClientV1 client;

    private final String baseApi;

    HealthsImpl(DefaultHarborClientV1 client, String baseApi) {
        this.client = client;
        this.baseApi = baseApi;
    }

    @Override
    public OverallHealthStatus get() {
        return client.get(getItemBaseApi(), OverallHealthStatus.class);
    }

    public String getItemBaseApi() {
        return baseApi + "/health";
    }


}
