package org.fedorahosted.beaker.cli.commands;

import org.apache.xmlrpc.XmlRpcException;
import org.fedorahosted.beaker.client.Command;
import org.fedorahosted.beaker.remote_model.BeakerJob;
import org.kohsuke.args4j.Option;

@CliCommand(name="cancelJob", requireAuth=true)
public class CancelJobCommand extends Command {

    @Option(name="-id", metaVar="<id>", usage="Job ID", required=true)
    //@Argument(index=1,required=true)
    public String jobId;
    
    @Option(name="-c", metaVar="<cause>", usage="Cancel cause", required=false)
    public String cause;

    public void execute() {
        try {
            String c = cause != null ? cause : "";
            new BeakerJob(jobId,beakerClient).cancel(c);
        } catch(XmlRpcException e) {
            System.out.println("Cannot stop job " + jobId + ", cuase: " + e.getMessage());
        }
    }
    
}
