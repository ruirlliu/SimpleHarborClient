package org.harbor.client.client.op.base;

import org.harbor.client.client.HarborResponse;

/**
 * @author lr
 * @date 2021/2/25
 */
public interface CreateOp<T> {

    HarborResponse create(T t);

}
