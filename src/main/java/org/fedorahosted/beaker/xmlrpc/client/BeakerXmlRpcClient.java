package org.fedorahosted.beaker.xmlrpc.client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.fedorahosted.beaker.client.BeakerClient;
import org.fedorahosted.beaker.client.Command;
import org.fedorahosted.beaker.xmlrpc.commands.XmlRpcCommand;


public class BeakerXmlRpcClient implements BeakerClient {
    
    private final XmlRpcClient client;

    public BeakerXmlRpcClient(XmlRpcClient client) {
        this.client = client;
    }

    @Override
    public Object execute(String cmd, Object[] params) throws XmlRpcException {
        return doRPCall(cmd, params);
    }
    
    @Override
    public Object execute(XmlRpcApi rpcApi, Object[] params) throws XmlRpcException {
        return doRPCall(rpcApi, params);
    }

    @Override
    public Object execute(Command cmd) throws XmlRpcException {
        return doRPCall(cmd.getClass().getAnnotation(XmlRpcCommand.class).xmlRpcCommand(), cmd.getArguments().toArray());
    }

    private Object doRPCall(XmlRpcApi rpcApi, Object[] params) throws XmlRpcException {
        return doRPCall(rpcApi.getRpc(), params);
    }
    
    private Object doRPCall(String rpc, Object[] params) throws XmlRpcException {
        Object result = null;
        //try {
            synchronized (client) { // only one thread and/or method is using this connection manager at a time
                result = client.execute(rpc, params);
            }
        /*} catch (XmlRpcException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }*/
        return result;
    }
}
