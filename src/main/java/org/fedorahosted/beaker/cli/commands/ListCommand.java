package org.fedorahosted.beaker.cli.commands;


@CliCommand(name="list")
public class ListCommand extends Command {
    
    public void execute() {
        System.out.println("Cli commands:");
        for(Class<?> c : CliCommandInvoker.cliCommands)
            System.out.println("\t* " + c.getAnnotation(CliCommand.class).name());
        System.out.println("\t* " + "exit");
    }
    
}
