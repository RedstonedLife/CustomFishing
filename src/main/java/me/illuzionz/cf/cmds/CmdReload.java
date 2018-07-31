package me.illuzionz.cf.cmds;

import me.illuzionz.cf.CustomFishing;
import me.illuzionz.cf.cmds.managers.SubCommand;
import me.illuzionz.cf.util.MessageManager;
import me.illuzionz.cf.util.Msg;
import org.bukkit.command.CommandSender;

public class CmdReload extends SubCommand {

    public CmdReload() {
        name = "reload";
        permission = "customfishing.reload";
    }

    public void onCommand(CommandSender player, String[] args) {
        CustomFishing.cf.getManager().saveFiles();
        CustomFishing.cf.getManager().reloadFiles();
        MessageManager.getInstance().sendMsg(player, Msg.MSG_CMD_RELOAD);
    }
}
