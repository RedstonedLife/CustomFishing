package me.illuzionz.cf;

import me.illuzionz.cf.cmds.managers.CommandManager;
import me.illuzionz.cf.config.ConfigManager;
import me.illuzionz.cf.events.FishingHandler;
import me.illuzionz.cf.util.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomFishing extends JavaPlugin {

    public static CustomFishing cf;
    private ConfigManager cfgm;

    @Override
    public void onEnable(){
        cf = this;
        loadConfigManager();
        registerListeners();
        initInstances();
    }

    /**
     * @return The instance of this class
     */
    public static CustomFishing getInstance(){
        return cf;
    }

    /**
     * Register all the listeners
     */
    public void registerListeners(){
        PluginManager pm = Bukkit.getServer().getPluginManager();

        pm.registerEvents(new FishingHandler(), this);
    }

    /**
     * Starts the config manager and creates all files
     */
    private void loadConfigManager() {
        cfgm = new ConfigManager();
        cfgm.setup();
    }

    /**
     * @return the config manager to access the files
     */
    public ConfigManager getManager() {
        return this.cfgm;
    }

    /**
     * Initiate all instances of classes we need
     */
    private void initInstances(){
        new CommandManager();
    }

}
