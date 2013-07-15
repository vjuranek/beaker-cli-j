package org.fedorahosted.beaker.cli.commands;

import org.apache.xmlrpc.XmlRpcException;
import org.fedorahosted.beaker.remote_model.BeakerTask;
import org.kohsuke.args4j.Option;

@CliCommand(name="taskInfo", requireAuth=true)
public class TaskInfoCommand extends Command {

    @Option(name="-id", metaVar="<id>", usage="Task ID", required=true)
    //@Argument(index=1,required=true)
    public String taskId;
    
    public void execute() {
        if(taskId == null || taskId.trim().equals("")) {
            System.out.println("Invalid task ID");
            return;
        }
            
        try {
            BeakerTask.TaskInfo taskInfo =  new BeakerTask(taskId,beakerClient).getInfo();
            System.out.println("Task info for " + taskId + "\n ============================== \n" + taskInfo.toString());
        } catch(XmlRpcException e) {
            System.out.println("Cannot get info about job " + taskId + " from Beaker (" + e.getCause() + ")");
        }
    }
}
