package me.illuzionz.cf.util;

import me.illuzionz.cf.CustomFishing;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public enum Msg {

    /**
     * Messages related with players
     */
    MSG_PLAYER_NOREWARDS("&cYou do not have any permissions for any rewards"),

    /**
     * Messages relates to commands
     */
    MSG_CMD_NORARGS("&cPlease specify some arguments or use /cf help"),
    MSG_CMD_NOCMDFOUND("&cThat is not a valid command. Use /cf help for a list of valid commands"),
    MSG_CMD_NOPERMS("&cYou don't have permission for this command"),

    /**
     * Messages used with the editor
     */
    MSG_EDITOR_LIST("&eList of current rewards:");

    public String path;
    public String def;
    public FileConfiguration LANG = CustomFishing.cf.getManager().getLang();


    /**
     * @param path  The path for the message in the lang file
     * @param start The default message if none are found
     */
    Msg(String path, String start) {
        this.path = path;
        this.def = start;
    }

    /**
     * Use the enum name as the path
     *
     * @param start The default message
     */
    Msg(String start) {
        this.path = this.name().replace('_', '.');
        this.def = start;
    }


    /**
     * Converts the message to a coloured string
     *
     * @return
     */
    @Override
    public String toString() {
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def));
    }

}
