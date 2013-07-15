package org.fedorahosted.beaker.cli.commands;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.fedorahosted.beaker.client.BeakerClient;
import org.fedorahosted.beaker.client.BeakerClientFactory;
import org.fedorahosted.beaker.xmlrpc.client.BeakerXmlRpcClientFactory;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.reflections.Reflections;

public class CliCommandInvoker {
    
    public static final Set<Class<?>> cliCommands = getCommands(CliCommand.class);
    private final BeakerClient beakerClient;
    
    public CliCommandInvoker(String beakerServer) {
        BeakerClientFactory factory = new BeakerXmlRpcClientFactory(beakerServer);
        beakerClient = factory.getClient();
    }
    
    public void execute(Command c) {
        
        c.execute();
    }
    
    public void parseAndExcute(String cmd) {
        Command command = getCommandInstance(cmd);
        if(command == null) {
            System.out.println("Command \"" + cmd.trim().split(" ")[0] + "\" doesn't seem to exist, type \"help\" or \"list\" to list available commands.");
            return;
        }
        command.setBeakerClient(beakerClient);
        execute(command);
    }
    
    public Command getCommandInstance(String cmd) {
        Command command = null;
        String c = cmd.trim().split(" ")[0];
        for(Class<?> clazz : cliCommands) {
            if(clazz.getAnnotation(CliCommand.class).name().equalsIgnoreCase(c)) {
                try {
                    command = (Command)clazz.newInstance();
                } catch(InstantiationException e) {
                    e.printStackTrace();
                } catch(IllegalAccessException e) {
                    e.printStackTrace();
                }
                parseArgs(command, cmd);
                break;
            }
        }
        return command;
    }
    
    private void parseArgs(Command c, String cmd) {
        CmdLineParser parser = new CmdLineParser(c);
        try {
            parser.parseArgument(cmd.split(" "));
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }

    public static Set<Class<?>> getCommands(Class<? extends Annotation> annotation) {
        System.out.println("Searching for annotations ...");
        //Reflections reflections = new Reflections("org.fedorahosted.beaker.cli");
        Reflections reflections = new Reflections("org.fedorahosted.beaker");
        return reflections.getTypesAnnotatedWith(annotation);
    }
    
}
