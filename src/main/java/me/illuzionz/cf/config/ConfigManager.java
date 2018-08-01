package me.illuzionz.cf.config;

import me.illuzionz.cf.CustomFishing;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class ConfigManager {

    private CustomFishing plugin = CustomFishing.cf;

    /**
     * Instance Variables for files
     */
    public FileConfiguration langcfg;
    public File langfile;

    public FileConfiguration rewardscfg;
    public File rewardsfile;


    /**
     * Method called to create files and load defaults
     */
    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        langfile = new File(plugin.getDataFolder(), "lang.yml");
        rewardsfile = new File(plugin.getDataFolder(), "rewards.yml");

        if (!langfile.exists()) {
            try {
                langfile.createNewFile();
                Bukkit.getConsoleSender().sendMessage("lang.yml has been created");
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage("lang.yml could not be created!");
            }
        }

        if (!rewardsfile.exists()) {
            try {
                rewardsfile.createNewFile();
                Bukkit.getConsoleSender().sendMessage("rewards.yml has been created");
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage("rewards.yml could not be created!");
            }
        }

        langcfg = YamlConfiguration.loadConfiguration(langfile);
        rewardscfg = YamlConfiguration.loadConfiguration(rewardsfile);

        // Loads default options
        Reader defConfigStream;
        try {
            defConfigStream = new InputStreamReader(plugin.getResource("lang.yml"), "UTF-8");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                langcfg.setDefaults(defConfig);
                langcfg.options().copyDefaults(true);
                saveFiles();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Reader defConfigStream1;
        try {
            defConfigStream1 = new InputStreamReader(plugin.getResource("rewards.yml"), "UTF-8");
            if (defConfigStream1 != null) {
                YamlConfiguration defConfig1 = YamlConfiguration.loadConfiguration(defConfigStream1);
                rewardscfg.setDefaults(defConfig1);
                rewardscfg.options().copyDefaults(true);
                saveFiles();
            }
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }


    /**
     * @return the lang file
     */
    public FileConfiguration getLang() {
        return langcfg;
    }

    /**
     * @return the rewards file
     */
    public FileConfiguration getRewards() {
        return rewardscfg;
    }

    /**
     * Save all of the files
     */
    public void saveFiles() {

        langfile = new File(plugin.getDataFolder(), "lang.yml");
        rewardsfile = new File(plugin.getDataFolder(), "rewards.yml");

        try {
            langcfg.save(langfile);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "lang.yml could not be saved");
        }

        try {
            rewardscfg.save(rewardsfile);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "rewards.yml could not be saved");
        }

    }

    /**
     * Reload all the files
     */
    public void reloadFiles() {
        langfile = new File(plugin.getDataFolder(), "lang.yml");
        rewardsfile = new File(plugin.getDataFolder(), "rewards.yml");

        langcfg = YamlConfiguration.loadConfiguration(langfile);
        rewardscfg = YamlConfiguration.loadConfiguration(rewardsfile);
    }

}
