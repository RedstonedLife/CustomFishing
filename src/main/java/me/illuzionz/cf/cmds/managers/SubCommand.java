package me.illuzionz.cf.cmds.managers;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {

    /**
     * The name of the subcommand to be passed as an argument
     */
    public String name;

    /**
     * The permission required to execute this command
     */
    public String permission;

    public SubCommand() {
    }

    /**
     * Implemented then called when name matches first arg
     *
     * @param player The sender doing the command
     * @param args   The args passed to the command
     */
    public abstract void onCommand(CommandSender player, String[] args);

    /**
     * @return The name of the subcommand
     */
    public String getName() {
        return this.name;
    }

}
