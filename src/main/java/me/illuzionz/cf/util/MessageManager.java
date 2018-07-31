package me.illuzionz.cf.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageManager {

    private static MessageManager msg = new MessageManager();

    private MessageManager(){
    }

    public static MessageManager getInstance(){
        return msg;
    }

    /**
     * Used to send a coloured message to a CommandSender
     *
     * @param sender Whoever is receiving the message
     * @param msg The message being sent
     */
    public void sendMsg(CommandSender sender, String msg){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    /**
     * Use for sending a message from Msg
     *
     * @param sender Whoever is receiving the message
     * @param msg The enum to be used as the message
     */
    public void sendMsg(CommandSender sender, Msg msg){
        String str = msg.toString();
        sender.sendMessage(str);
    }

    /**
     * Used to color a string
     *
     * @param msg The string to be coloured
     * @return the coloured string
     */
    public String colorMsg(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
