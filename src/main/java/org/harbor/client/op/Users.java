package org.harbor.client.op;

import org.harbor.client.model.User;

import java.util.List;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface Users {
    List<User> list();

    User get();
}
