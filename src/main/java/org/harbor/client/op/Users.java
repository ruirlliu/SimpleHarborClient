package org.harbor.client.op;

import org.harbor.client.model.User;
import org.harbor.client.op.base.GetOp;
import org.harbor.client.op.base.ListOp;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface Users extends ListOp<User>, GetOp<User> {

}
