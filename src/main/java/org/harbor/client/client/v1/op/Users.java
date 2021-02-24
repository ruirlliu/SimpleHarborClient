package org.harbor.client.client.v1.op;

import org.harbor.client.client.model.User;
import org.harbor.client.client.v1.DefaultHarborClientV1;

import java.util.List;

/**
 * @author lr
 * @date 2021/2/24
 */
public class Users {

    private final DefaultHarborClientV1 client;

    private final String baseApi;

    public Users(DefaultHarborClientV1 client, String baseApi) {
        this.client = client;
        this.baseApi = baseApi;
    }

    public List<User> list() {
        return client.list(getUserBaseApi(), User.class);
    }

    public User current() {
        return client.get(getUserBaseApi() + "/current", User.class);
    }

    public String getUserBaseApi() {
        return baseApi + "/users";
    }


}
