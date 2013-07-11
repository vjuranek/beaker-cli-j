package org.fedorahosted.beaker.model;

import org.apache.xmlrpc.XmlRpcException;
import org.fedorahosted.beaker.client.BeakerClient;
import org.fedorahosted.beaker.xmlrpc.client.XmlRpcApi;

public class RemoteBeakerObject {

    protected BeakerClient beakerClient;
    
    public RemoteBeakerObject() {
    }
    
    public RemoteBeakerObject(BeakerClient beakerClient) {
        this.beakerClient = beakerClient;
    }
    
    protected Object callOnBeaker(XmlRpcApi cmd, Object[] params) throws XmlRpcException, NoBeakerClientException {
        if(beakerClient == null)
            throw new NoBeakerClientException();
        return beakerClient.execute(cmd, params);
    }
    
    public BeakerClient getBeakerClient() {
        return beakerClient;
    }
    
    public void setBeakerClient(BeakerClient beakerClient) {
        this.beakerClient = beakerClient;
    }
    
}
