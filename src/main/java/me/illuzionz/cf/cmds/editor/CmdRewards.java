package me.illuzionz.cf.cmds.editor;

import me.illuzionz.cf.CustomFishing;
import me.illuzionz.cf.cmds.managers.SubCommand;
import me.illuzionz.cf.util.MessageManager;
import me.illuzionz.cf.util.Msg;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class CmdRewards extends SubCommand {

    MessageManager msg = MessageManager.getInstance();
    FileConfiguration config = CustomFishing.getInstance().getManager().getRewards();

    public CmdRewards() {
        name = "rewards";
        permission = "customfishing.rewards";
    }

    public void onCommand(CommandSender player, String[] args) {

        if (args.length == 0) {
            msg.sendMsg(player, "&c/cf rewards <create|delete|list|edit> <args>");
            return;
        }

        if (args[0].equalsIgnoreCase("list")) {
            msg.sendMsg(player, Msg.MSG_EDITOR_LIST);
            for (String key : config.getConfigurationSection("Rewards").getKeys(false)) {
                msg.sendMsg(player, "&e- " + key);
            }
        } else if (args[0].equalsIgnoreCase("delete")) {

            if (args.length == 1 || args.length >= 3) {
                msg.sendMsg(player, "&c/cf rewards delete <rewardname>");
                return;
            }

            for (String key : config.getConfigurationSection("Rewards").getKeys(false)) {
                if (key.equalsIgnoreCase(args[1])) {
                    config.set("Rewards." + key, null);
                    CustomFishing.getInstance().getManager().saveFiles();
                }
            }
        }

    }
}
