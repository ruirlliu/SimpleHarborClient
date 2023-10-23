package org.harbor.client.op.impl;

import org.harbor.client.model.OverallHealthStatus;
import org.harbor.client.op.Healths;

/**
 * @author lr
 * @date 2021/2/25
 */
class HealthsImpl implements Healths {

    private final DefaultHarborClientV2 client;


    HealthsImpl(DefaultHarborClientV2 client) {
        this.client = client;
    }

    @Override
    public OverallHealthStatus get() {
        return client.get(getItemBaseApi(), OverallHealthStatus.class);
    }

    public String getItemBaseApi() {
        return "/health";
    }


}
