package org.fedorahosted.beaker.xmlrpc.commands;

import org.fedorahosted.beaker.client.Command;

@XmlRpcCommand(name="WhoAmI", xmlRpcCommand="auth.who_am_i")
public class WhoAmICommand extends Command {
    
    public void execute() {
        
    }
    
}
