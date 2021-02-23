package org.harbor.client.client.v2.operation.base;

import java.util.List;

/**
 * @author liurui
 * @date 2021/2/4
 */
public interface ListOp<E> extends BaseOp<List<E>>{

    List<E> get();

}
