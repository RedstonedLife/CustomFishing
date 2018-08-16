package me.illuzionz.cf.cmds;

import me.illuzionz.cf.cmds.managers.SubCommand;
import me.illuzionz.cf.util.MessageManager;
import org.bukkit.command.CommandSender;

public class CmdHelp extends SubCommand {

    public CmdHelp() {
        name = "help";
        permission = "customfishing.help";
    }

    public void onCommand(CommandSender player, String[] args) {
        MessageManager msg = MessageManager.getInstance();

        msg.sendMsg(player, "&e/cf help : Display this page");
    }

}
