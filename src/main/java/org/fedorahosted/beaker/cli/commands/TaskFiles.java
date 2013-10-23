package org.fedorahosted.beaker.cli.commands;

import java.util.ArrayList;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.fedorahosted.beaker4j.remote_model.BeakerJob;
import org.kohsuke.args4j.Option;

@CliCommand(name="taskFiles", requireAuth=false)
public class TaskFiles extends Command {
    
    @Option(name="-id", metaVar="<id>", usage="Job ID", required=true)
    public String jobId;
    
    public void execute() {
        if(jobId == null || jobId.trim().equals("")) {
            System.out.println("Invalid job ID");
            return;
        }
            
        try {
            BeakerJob job = new BeakerJob(jobId, beakerClient);
            ArrayList<Map<String, String>> results = job.getFiles();
            System.out.println("File for job " + jobId + "\n ============================== \n");
            for(Map<String, String> res : results)
                System.out.println("Filename: " + res.get("filename") + ", URL: " + res.get("url"));
            
        } catch(XmlRpcException e) {
            System.out.println("Cannot get info about job " + jobId + " from Beaker (" + e.getCause() + ")");
        } 
    }

}
