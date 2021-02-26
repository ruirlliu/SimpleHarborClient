package org.harbor.client.client.op.impl;

import org.harbor.client.client.model.User;

import java.util.List;

/**
 * @author lr
 * @date 2021/2/24
 */
class UsersImpl implements org.harbor.client.client.op.Users {

    private final DefaultHarborClientV1 client;

    private final String baseApi;

    UsersImpl(DefaultHarborClientV1 client, String baseApi) {
        this.client = client;
        this.baseApi = baseApi;
    }

    @Override
    public List<User> list() {
        return client.list(getItemBaseApi(), User.class);
    }


    @Override
    public User get() {
        return client.get(getItemBaseApi() + "/current", User.class);
    }


    private String getItemBaseApi() {
        return baseApi + "/users";
    }

}
