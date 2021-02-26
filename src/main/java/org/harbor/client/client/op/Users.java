package org.harbor.client.client.op;

import org.harbor.client.client.model.User;

import java.util.List;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface Users {
    List<User> list();

    User get();
}
