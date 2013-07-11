package org.fedorahosted.beaker.cli;

import java.io.Console;

import org.fedorahosted.beaker.cli.commands.CliCommandInvoker;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class BkrCli {
    
    
    private final CliCommandInvoker cliInvoker;

    public BkrCli(String[] args) {
        CliArgs cliArgs = getCliArgs(args);
        cliInvoker = new CliCommandInvoker(cliArgs.getServer());
    }
    
    public static void main(String args[]) {
        new BkrCli(args).run3();
        //new BkrCli().run(args);
    }

    /*private void run(String args[]) {
        CliArgs cliArgs = getCliArgs(args);
        BeakerClientFactory factory = new BeakerXmlRpcClientFactory(cliArgs.getServer());
        BeakerClient client = factory.getClient();
        // client.authenticate(cliArgs.getUserName(), cliArgs.getPassword());
        // Object o = client.taskInfo("T:5145024");
        //Command cmd = new Command(cliArgs.getArguments());
        //Object o = client.executeCmd(cmd.getCmd(), cmd.getParams());
        client.authenticate("hudson", "*********");
        //System.out.println("auth OK");
        //Object o = client.taskInfo("T:5145024");
        //System.out.println("Vysledek, task info: " + o);
        String param = "<job retention_tag=\"scratch\"><whiteboard></whiteboard><recipeSet><recipe kernel_options=\"\" kernel_options_post=\"\" ks_meta=\"\" role=\"RECIPE_MEMBERS\" whiteboard=\"\"><autopick random=\"false\"/><watchdog panic=\"ignore\"/><packages/><ks_appends/><repos/><distroRequires><and><distro_variant op=\"=\" value=\"Server\"/>" +
        "<distro_arch op=\"=\" value=\"x86_64\"/><distro_virt op=\"=\" value=\"\"/><distro_family op=\"=\" value=\"RedHatEnterpriseLinux6\"/><distro_method op=\"=\" value=\"nfs\"/></and></distroRequires><hostRequires><system_type value=\"Machine\"/></hostRequires><partitions/><task name=\"/distribution/install\" role=\"STANDALONE\"><params/></task><task name=\"/tmp/vjuranek/testHudsonSlaveTest2\" role=\"STANDALONE\"><params/></task><task name=\"/distribution/reservesys\" role=\"STANDALONE\"><params/></task></recipe></recipeSet></job>";
        Object[] params = {param};
        Object o = client.executeCmd("jobs.upload", params);
        System.out.println("Vysledek: " + o);
        System.out.println("Who am I: " + client.whoAmI());
    }*/
    
    /*private void run2(String args[]){
        CliArgs cliArgs = getCliArgs(args);
        BeakerClientFactory factory = new BeakerXmlRpcClientFactory(cliArgs.getServer());
        BeakerClient client = factory.getClient();
        
        Console c = System.console();
        while(true){
            String l = c.readLine("%s","[Bkr] cmd: ");
            if("exit".equals(l.trim()))
                break;
            Command cmd = new Command(l.split(" "));
            Object o = client.executeCmd(cmd.getCmd(), cmd.getParams());
            System.out.println("[Bkr] res: " + o);
        }
    }*/
    
    private void run3() {
        Console c = System.console();
        while(true){
            String cmd = c.readLine("%s","[Bkr] cmd: ").trim();
            if("exit".equals(cmd)) 
                break;
            cliInvoker.parseAndExcute(cmd);
        }
    }

    private CliArgs getCliArgs(String args[]) {
        CliArgs cliArgs = new CliArgs();
        CmdLineParser parser = new CmdLineParser(cliArgs);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            System.exit(1);
        }
        return cliArgs;
    }
    

}

