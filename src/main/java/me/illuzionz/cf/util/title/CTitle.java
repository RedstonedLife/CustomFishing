package me.illuzionz.cf.util.title;

import me.illuzionz.cf.CustomFishing;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class CTitle {

    private CustomFishing plugin = CustomFishing.cf;
    private FileConfiguration config = plugin.getManager().getRewards();

    int fadeIn = config.getInt("Title.fadeIn");
    int displayTime = config.getInt("Title.displayTime");
    int fadeOut = config.getInt("Title.fadeOut");

    public void sendTitle(Player player, String titleText, String subtitleText) {
        try {

            Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
            Object titleChat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, titleText);

            Object enumSubtitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
            Object subtitleChat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, subtitleText);

            Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object titlePacket = titleConstructor.newInstance(enumTitle, titleChat, fadeIn, displayTime, fadeOut);
            Object subtitlePacket = titleConstructor.newInstance(enumSubtitle, subtitleChat, fadeIn, displayTime, fadeOut);

            sendPacket(player, titlePacket);
            sendPacket(player, subtitlePacket);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Class<?> getNMSClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

}
