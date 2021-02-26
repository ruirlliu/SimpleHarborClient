package org.harbor.client.op.base;

import org.harbor.client.HarborResponse;

/**
 * @author lr
 * @date 2021/2/25
 */
public interface CreateOp<T> {

    HarborResponse create(T t);

}
