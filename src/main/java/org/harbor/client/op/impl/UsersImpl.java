package org.harbor.client.op.impl;

import org.harbor.client.model.User;
import org.harbor.client.op.Users;

import java.util.List;

/**
 * @author lr
 * @date 2021/2/24
 */
class UsersImpl implements Users {

    private final DefaultHarborClientV2 client;


    UsersImpl(DefaultHarborClientV2 client) {
        this.client = client;
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
        return "/users";
    }

}
