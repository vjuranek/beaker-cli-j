package org.fedorahosted.beaker.cli;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

public class CliArgs {
    
    @Option(name="-s", aliases="--server", required=true, metaVar="<server_url>", usage="Beaker server URL")
    private String server;
    
    @Option(name="-u", aliases="--username", metaVar="<login>", usage="User name")
    private String userName;
    
    @Option(name="-p", aliases="--password", metaVar="<password>", usage="Password")
    private String password;

    @Argument
    private List<String> arguments = new ArrayList<String>();
    
    public String getServer() {
        return server;
    }
    
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
    public List<String> getArguments() {
        return arguments;
    }

}
