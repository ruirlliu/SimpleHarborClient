package org.harbor.client.client.v2.operation.exec;

/**
 * @author liurui
 * @date 2021/2/4
 */
public interface Exec<CMD extends ExecCmd<RES>, RES> {

    RES exec(CMD cmd);
    
}
