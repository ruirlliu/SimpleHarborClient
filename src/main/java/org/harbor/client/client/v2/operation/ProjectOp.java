package org.harbor.client.client.v2.operation;

import cn.hutool.core.collection.CollUtil;
import org.harbor.client.client.model.Project;
import org.harbor.client.client.v2.operation.base.GetOp;
import org.harbor.client.client.v2.operation.base.ListOp;
import org.harbor.client.client.v2.operation.exec.Exec;
import org.harbor.client.client.v2.operation.exec.ExecCmd;

import java.util.List;

/**
 * @author liurui
 * @date 2021/2/4
 */
public class ProjectOp implements ListOp<Project> {

    private Exec<ExecCmd<List<Project>>, List<Project>> exec;

    private ExecCmd<List<Project>> cmd;
//    public ProjectOp(Exec<ExecCmd<List<Project>>, List<Project>> exec, ExecCmd<List<Project>> cmd) {
//        this.exec = exec;
//        this.cmd = cmd;
//    }

    private String filter;

    @Override
    public List<Project> get() {
        return exec.exec(cmd);
    }

    public ListOp<Project> withName(String name) {
        this.filter = name;
        return this;
    }

    public GetOp<Project> withExactName(String name) {
        return new ListOpAdapter(this, name);
    }

    static class ListOpAdapter implements GetOp<Project> {

        private final ProjectOp op;

        private final String name;

        ListOpAdapter(ProjectOp op, String name) {
           this.op = op;
           this.name = name;
        }

        @Override
        public Project get() {
            List<Project> projects = op.withName(name).get();
            if (CollUtil.isEmpty(projects)) {
                return null;
            }
            for (Project project : projects) {
                if (name.equals(project.getName())) {
                    return project;
                }
            }
            return null;
        }
    }


}
