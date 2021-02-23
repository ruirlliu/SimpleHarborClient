package org.harbor.client.client.v2.operation.exec;

import com.harbor.client.data.Project;

import java.util.List;

/**
 * @author liurui
 * @date 2021/2/5
 */
public class ListProjectExecCmd implements ExecCmd<List<Project>>{

    private String name;

    public ListProjectExecCmd(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



}
