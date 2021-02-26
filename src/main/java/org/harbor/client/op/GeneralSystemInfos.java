package org.harbor.client.op;

import org.harbor.client.model.GeneralInfo;
import org.harbor.client.model.SystemInfo;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface GeneralSystemInfos {
    // api可以不验证权限
    GeneralInfo get();

    SystemInfo volumes();
}
