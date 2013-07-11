package org.fedorahosted.beaker.client;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;


public abstract class Command {
    
    @Argument
    private List<String> arguments = new ArrayList<String>();
    
    protected BeakerClient beakerClient;
       
    public abstract void execute();
    
    public List<String> getArguments() {
        return arguments;
    }
    
    public void setBeakerClient(BeakerClient beakerClient) {
        this.beakerClient = beakerClient;
    }

}
