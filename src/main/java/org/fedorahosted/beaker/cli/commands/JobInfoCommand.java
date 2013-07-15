package org.fedorahosted.beaker.cli.commands;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBException;

import org.apache.xmlrpc.XmlRpcException;
import org.fedorahosted.beaker.client.Command;
import org.fedorahosted.beaker.model.Job;
import org.fedorahosted.beaker.model.Task;
import org.fedorahosted.beaker.remote_model.BeakerJob;
import org.fedorahosted.beaker.util.BeakerModelUtils;
import org.kohsuke.args4j.Option;

@CliCommand(name="jobInfo", requireAuth=true)
public class JobInfoCommand extends Command {
    
    @Option(name="-id", metaVar="<id>", usage="Job ID", required=true)
    //@Argument(index=1,required=true)
    public String jobId;
    
    public void execute() {
        if(jobId == null || jobId.trim().equals("")) {
            System.out.println("Invalid job ID");
            return;
        }
            
        try {
            Job job = (new BeakerJob(jobId, beakerClient)).getJob();
            System.out.println("Job info for " + jobId + "\n ============================== \n");
            for(Task task : BeakerModelUtils.getJobTasks(job)) {
                System.out.println("Task " + task.getName() + " results with " + task.getResult());
            }
        } catch(XmlRpcException e) {
            System.out.println("Cannot get info about job " + jobId + " from Beaker (" + e.getCause() + ")");
        } catch (JAXBException e) {
            System.out.println("Cannot convert job XML to class: "  + e.getCause() + ")");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Problem with encoding while converting XML to class: "  + e.getCause() + ")");
        }
    }

}
