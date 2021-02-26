package org.harbor.client.op.base;

import org.harbor.client.HarborResponse;

/**
 * @author lr
 * @date 2021/2/25
 */
public interface UpdateOp<T> {

    HarborResponse update(T t);

}
