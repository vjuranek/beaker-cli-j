package org.fedorahosted.beaker.model;

import org.apache.xmlrpc.XmlRpcException;
import org.fedorahosted.beaker.client.BeakerClient;
import org.fedorahosted.beaker.xmlrpc.client.XmlRpcApi;

public class Watchdog extends RemoteBeakerObject {
    
    public Watchdog(BeakerClient beakerClient) {
        this.beakerClient = beakerClient;
    }
    
    public int getRemaingTime(int taskId) throws XmlRpcException {
        System.out.println("Getting time for " + taskId);
        //TODO need a check if job is running
        return (Integer)callOnBeaker(XmlRpcApi.RECIPES_TASKS_WATCHDOG, new Object[] {taskId});
    }

}
