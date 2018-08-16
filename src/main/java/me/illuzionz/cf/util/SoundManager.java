package me.illuzionz.cf.util;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundManager {
    private static SoundManager ourInstance = new SoundManager();

    public static SoundManager getInstance() {
        return ourInstance;
    }

    private SoundManager() {
    }

    public static void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 10, 1);
    }

}
