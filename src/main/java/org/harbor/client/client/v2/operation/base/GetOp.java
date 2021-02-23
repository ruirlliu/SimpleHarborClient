package org.harbor.client.client.v2.operation.base;

/**
 * @author liurui
 * @date 2021/2/4
 */
public interface GetOp<E> extends BaseOp<E>{

    E get();

}
