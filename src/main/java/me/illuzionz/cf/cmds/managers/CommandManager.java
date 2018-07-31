package me.illuzionz.cf.cmds.managers;

import me.illuzionz.cf.CustomFishing;
import me.illuzionz.cf.cmds.CmdHelp;
import me.illuzionz.cf.cmds.CmdReload;
import me.illuzionz.cf.util.MessageManager;
import me.illuzionz.cf.util.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager implements CommandExecutor {

    /**
     * A holder for all our subcommands
     */
    private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();

    /**
     * Instance of the main class
     */
    private CustomFishing plugin = CustomFishing.getInstance();

    /**
     * The main command to be used in execution
     */
    private String main = "cf";

    public CommandManager(){
        setup();
    }

    /**
     * Add all the commands
     */
    public void setup() {
        plugin.getCommand(main).setExecutor(this);
        this.commands.add(new CmdHelp());
        this.commands.add(new CmdReload());
    }

    /**
     * Runs when the main command is executed and checks if the first arg
     * is a subcommand and if the have the required permission,
     * if so it executes it's implementation.
     */
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase(main)) {
                if (args.length == 0) {
                    MessageManager.getInstance().sendMsg(player, Msg.MSG_CMD_NORARGS);
                    return true;
                }

                SubCommand target = this.get(args[0]);
                if (target == null) {
                    MessageManager.getInstance().sendMsg(player, Msg.MSG_CMD_NOCMDFOUND);
                    return true;
                }

                if(!player.hasPermission(target.permission)){
                    MessageManager.getInstance().sendMsg(player, Msg.MSG_CMD_NOPERMS);
                    return true;
                }

                ArrayList<String> a = new ArrayList<String>();
                a.addAll(Arrays.asList(args));
                a.remove(0);
                args = a.toArray(new String[a.size()]);

                try {
                    target.onCommand(player, args);
                } catch (Exception var9) {
                    MessageManager.getInstance().sendMsg(player, "&cAn error has occured: " + var9.getCause());
                    var9.printStackTrace();
                }
            }

        }
        return true;

    }

    /**
     * @param name What to fetch the subcommand from
     * @return The arg as a subcommand
     */
    private SubCommand get(String name) {

        for (SubCommand cmd : this.commands) {
            if (cmd.getName().equalsIgnoreCase(name)) {
                return cmd;
            }
        }

        return null;
    }

}
