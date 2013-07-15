package org.fedorahosted.beaker.cli.commands;

import org.fedorahosted.beaker4j.remote_model.Identity;
import org.kohsuke.args4j.Option;

@CliCommand(name="auth")
public class AuthenticateCommand extends Command {
    
    @Option(name="-u", aliases="--username", metaVar="<login>", usage="User name", required=true)
    public String login;
    
    @Option(name="-p", aliases="--password", metaVar="<password>", usage="Password", required=true)
    private String password;
    
    public void execute() {
        Identity identity = new Identity(login, password, beakerClient);
        identity.authenticate();
        System.out.println("Authneticated as " + identity.whoAmI());
    }

}
